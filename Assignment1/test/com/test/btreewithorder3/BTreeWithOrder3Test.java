package com.test.btreewithorder3;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import datastructure.BTreeWithOrder3;
import datastructure.Student;
import implementation.BTreeWithOrder3Implementation;

class BTreeWithOrder3Test {
	private BTreeWithOrder3 studentsRecordTree;

	/** inserting test data in to the tree */
	@BeforeEach
	void setUp() {
		studentsRecordTree = new BTreeWithOrder3Implementation();
		studentsRecordTree.insert(new Student("Jason Chen", 8462813, 2.0));
		studentsRecordTree.insert(new Student("Megan Nichole", 8462814, 4.0));
		studentsRecordTree.insert(new Student("Devi", 8462858, 4.0));
		studentsRecordTree.insert(new Student("Jason Derulo", 8462111, 2.84));
		studentsRecordTree.insert(new Student("Ariana", 8462890, 2.85));
		studentsRecordTree.insert(new Student("Patrick", 9673824, 4.0));
		studentsRecordTree.insert(new Student("Katrina", 9462813, 2.0));
	}

	/**
	 * TestCases using assertEquals method to verify the expected result with actual
	 * result
	 * 
	 * TestCase1: checking tree size , expected is 7 as we have inserted 7 elements
	 */
	@Test
	public void treeSizeTest() {
		int expectedResult = 7;
		int actualResult = studentsRecordTree.getSize();
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * TestCase 2: passing k value more than tree size=7 then expected result should
	 * throw an error to satisfy this test case
	 */
	@Test
	public void getKthElementInTreeTest() {
		assertThrows(IndexOutOfBoundsException.class, () -> studentsRecordTree.getKthElementInTree(9));
	}

	/**
	 * TestCase 3: actual result is 4th element in the list, so expected should
	 * print details of the 4th element in the list to satisfy this test case
	 */
	@Test
	public void getKthElementTest() {
		String expectedResult = "4 element in the list details are:\n" + "name: Jason Derulo\n" + "redId: 8462111\n"
				+ "gpa: 2.84\r\n" + "";
		PrintStream outputStream = System.out;
		ByteArrayOutputStream byteArrayOutputStream = TestUtility.getByteArrayOutputStream();
		studentsRecordTree.getKthElementInTree(4);
		System.setOut(outputStream);
		assertEquals(expectedResult, byteArrayOutputStream.toString());
	}

	/**
	 * TestCase 4: actual result prints redIDs of probation students and expected
	 * should match with actual result to satisfy this test case
	 */
	@Test
	public void printProbationStudentsTest() {
		String expectedResult = "RedIds of Probation Students:\r\n" + "8462813\r\n" + "8462111\r\n" + "9462813";
		PrintStream outputStream = System.out;
		ByteArrayOutputStream byteArrayOutputStream = TestUtility.getByteArrayOutputStream();
		studentsRecordTree.printProbationStudents();
		System.setOut(outputStream);
		assertEquals(expectedResult, byteArrayOutputStream.toString().trim());
	}

	/**
	 * TestCase 5:actual result prints names of students with GPA 4.0 and expected
	 * should match with actual result to pass this test case
	 */
	@Test
	public void printStudentsWithGPA4Test() {
		String expectedResult = "List of students with GPA 4.0: \r\n" + "Patrick\r\n" + "Megan Nichole\r\n" + "Devi";
		PrintStream outputStream = System.out;
		ByteArrayOutputStream byteArrayOutputStream = TestUtility.getByteArrayOutputStream();
		studentsRecordTree.printStudentsWithGPA4();
		System.setOut(outputStream);
		assertEquals(expectedResult, byteArrayOutputStream.toString().trim());
	}
}
