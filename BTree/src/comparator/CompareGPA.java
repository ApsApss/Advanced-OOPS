package comparator;

import java.util.Comparator;

import driver.Student;

/**
 * This is a comparator for Student elements based on GPA
 * 
 * @param <E>
 */
public class CompareGPA<E> implements Comparator<E> {

	@Override
	public int compare(E element1, E element2) {
		if (((Student) element1).getGpa() > ((Student) element2).getGpa()) {
			return 1;
		} else {
			return -1;
		}
	}
}
