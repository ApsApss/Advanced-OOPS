package com.test.btreewithorder3;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * This is a helper class for BTreeWithOrder3Test class to fetch the console
 * output of getKthElementInTree, printProbationStudents, printStudentsWithGPA4
 * methods
 * 
 *
 */
public class TestUtility {
	/**
	 * 
	 * @param printStream
	 * @return output stream (console) data is written in to byteArrayOutputStream
	 */
	public static ByteArrayOutputStream getByteArrayOutputStream() {
		PrintStream newOutputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		newOutputStream = new PrintStream(byteArrayOutputStream);
		System.setOut(newOutputStream);
		newOutputStream.flush();
		return byteArrayOutputStream;
	}
}
