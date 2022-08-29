import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import comparator.CompareGPA;
import comparator.CompareNames;
import datastructure.BTreeSet;
import driver.Student;

class BTreeSetTest {
	private Set<Student> studentsOrderedByGPA;
	private Set<Student> studentsOrderedByName;
	private Set<Integer> integerTree;
	private Set<Integer> emptyTree;

	/**
	 * Creation and insertion of test data in to the trees
	 */
	@BeforeEach
	void setUp() {
		emptyTree = new BTreeSet<Integer>(null);
		studentsOrderedByName = new BTreeSet<Student>(new CompareNames<Student>());
		studentsOrderedByName.add(new Student("Jason Chen", 8462813, 2.0));
		studentsOrderedByName.add(new Student("Megan Nichole", 8462814, 3.0));
		studentsOrderedByName.add(new Student("Devi", 8462858, 3.5));
		studentsOrderedByName.add(new Student("Jason Derulo", 8462111, 2.84));
		studentsOrderedByName.add(new Student("Ariana", 8462890, 2.8));
		studentsOrderedByName.add(new Student("Patrick", 9673824, 4.0));
		studentsOrderedByName.add(new Student("Katrina", 9462813, 2.1));
		studentsOrderedByName.add(new Student("Bart", 9462813, 2.2));
		Comparator<Integer> integerComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return 1;
				} else {
					return -1;
				}
			}
		};
		integerTree = new BTreeSet<Integer>(integerComparator);
		integerTree.add(1);
		integerTree.add(2);
		integerTree.add(5);
		integerTree.add(8);
		integerTree.add(11);
		integerTree.add(9);
		integerTree.add(10);
		integerTree.add(3);
		integerTree.add(4);
		integerTree.add(7);
		integerTree.add(6);
		studentsOrderedByGPA = new BTreeSet<Student>(new CompareGPA<Student>());
		studentsOrderedByGPA.add(new Student("Jason Chen", 8462813, 2.0));
		studentsOrderedByGPA.add(new Student("Megan Nichole", 8462814, 4.1));
		studentsOrderedByGPA.add(new Student("Devi", 8462815, 3.5));
		studentsOrderedByGPA.add(new Student("Jason Derulo", 8462816, 2.84));
		studentsOrderedByGPA.add(new Student("Ariana", 8462817, 3.75));
		studentsOrderedByGPA.add(new Student("David", 8462818, 4.3));
		studentsOrderedByGPA.add(new Student("Patrick", 8462819, 4.0));
		studentsOrderedByGPA.add(new Student("Katrina", 8462820, 2.7));
		studentsOrderedByGPA.add(new Student("Bart", 8462821, 2.5));
	}

	/**
	 * TestCase1: checking tree size , expected is 9 as we have inserted 9 elements
	 */
	@Test
	public void sizeTest() {
		assertEquals(0, emptyTree.size());
		assertEquals(9, studentsOrderedByGPA.size());
	}

	@Test
	public void isEmptyTest() {
		assertEquals(true, emptyTree.isEmpty());
		assertEquals(false, studentsOrderedByName.isEmpty());
	}

	@Test
	public void toStringTest() {
		assertNotNull(studentsOrderedByName.toString());
		assertEquals("1234567891011", integerTree.toString());
	}

	@Test
	public void containsTest() {
		assertEquals(true, integerTree.contains(1));
		assertEquals(false, integerTree.contains(14));
	}

	/**
	 * next and hasNext test to verify externalIterator
	 */
	@Test
	public void nextAndHasNextTest() {
		String expectedResult = "Jason Chen";
		Iterator<Student> iterator = studentsOrderedByGPA.iterator();
		assertEquals(expectedResult, iterator.next().getName());
		assertEquals(true, iterator.hasNext());
		while (iterator.hasNext()) {
			iterator.next();
		}
		assertEquals(false, iterator.hasNext());
	}

	/**
	 * expected result is to display list of students names in in-order whose
	 * GPA<2.85
	 */
	@Test
	public void externalIteratorTest() {
		List<String> expectedProbationStudents = new ArrayList<String>(
				Arrays.asList("Ariana", "Bart", "Jason Chen", "Jason Derulo", "Katrina"));
		Iterator<Student> iterator = studentsOrderedByName.iterator();
		List<String> probationStudents = new ArrayList<String>();
		while (iterator.hasNext()) {
			Student element = (Student) iterator.next();
			if (element.getGpa() < 2.85) {
				probationStudents.add(element.getName());
			}
		}
		assertTrue(expectedProbationStudents.equals(probationStudents));
	}

	@Test
	public void internalIteratorTest() {
		List<String> expectedResult = new ArrayList<String>(Arrays.asList("David", "Megan Nichole", "Patrick", "Ariana",
				"Devi", "Jason Derulo", "Katrina", "Bart", "Jason Chen"));
		List<String> studentNamesInRevOrder = new ArrayList<String>();
		studentsOrderedByGPA.forEach(s -> studentNamesInRevOrder.add(s.getName()));
		assertTrue(expectedResult.equals(studentNamesInRevOrder));
	}

	/**
	 * expected result is to display list of students redIds in reverse order whose
	 * GPA>=4.0
	 */
	@Test
	public void internalIteratorTestWithFilter() {
		List<Integer> expectedResult = new ArrayList<Integer>(Arrays.asList(8462819, 8462814, 8462818));
		List<Integer> gpaMoreThanEqualsFour = new ArrayList<Integer>();
		studentsOrderedByGPA.stream().filter(s -> s.getGpa() >= 4.0)
				.forEach(s -> gpaMoreThanEqualsFour.add(s.getRedId()));
		assertTrue(expectedResult.equals(gpaMoreThanEqualsFour));
	}

	@Test
	public void toArrayTest() {
		Object[] studentsList = studentsOrderedByGPA.toArray();
		assertEquals(9, studentsList.length);
		assertEquals("Patrick", ((Student) studentsList[6]).getName());
	}

	@Test
	public void addTest() {
		assertEquals(true, studentsOrderedByGPA.add(new Student("Arya", 8462823, 3.2)));
	}
}