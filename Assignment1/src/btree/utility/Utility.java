package utility;

import datastructure.Node;
import datastructure.Student;

/**
 * Utility class is a helper class for BTreeWithOrder3Implementation class, this
 * helper class have methods to print some operations like printing Kth element
 * in the students list, printing probation students and printing students GPA
 * with 4.0
 */
public class Utility {
	private int kCounter; // counter variable used to read Kth element in the list
	private static Student kthElement; // Kth element in the list is assigned to this variable

	/**
	 * this method prints name, redId, GPA of Kth element from the studentsList
	 * 
	 * @param root: BTree root or starting node of the tree
	 * @param k:    kth element in the list of students
	 */
	public static void getKtheElementHelper(Node root, int k) {
		Utility utility = new Utility();
		utility.readKthElementInTree(root, k);
		if (kthElement != null) {
			System.out.println(k + " element in the list details are:\nname: " + kthElement.name + "\nredId: "
					+ kthElement.redId + "\ngpa: " + kthElement.gpa);
		}
	}

	/**
	 * this method prints redIds of students with GPA less than 2.85 using in-order
	 * traversal(left->root->right) of the tree to print the list of students from
	 * front to back
	 * 
	 * @param current: when first time recursion starts then current is root of the
	 *                 tree, then in the next recursions it is subtree of left,
	 *                 middle and right child
	 */
	public static void printProbationStudentsHelper(Node current) {
		if (current != null) {
			if (current.isLeaf()) {
				if (current.getLeftKey().gpa < 2.85) {
					System.out.println(current.getLeftKey().redId);
				}
				if (current.getRightKey() != null) {
					if (current.getRightKey().gpa < 2.85) {
						System.out.println(current.getRightKey().redId);
					}
				}
			} else {
				printProbationStudentsHelper(current.getLeftChild());
				if (current.getLeftKey().gpa < 2.85) {
					System.out.println(current.getLeftKey().redId);
				}
				printProbationStudentsHelper(current.getMiddleChild());
				if (current.getRightKey() != null) {
					if (!current.isLeaf()) {
						if (current.getRightKey().gpa < 2.85) {
							System.out.println(current.getRightKey().redId);
						}
					}
					printProbationStudentsHelper(current.getRightChild());
				}
			}
		}

	}

	/**
	 * this method prints names of students with gpa 4 using reverse of in-order
	 * traversal(right->root>left) to print the list of student names from back to
	 * front.
	 * 
	 * @param current: when first time recursion starts then current is root of the
	 *                 tree, then in the next recursions it is subtree of left,
	 *                 middle and right child
	 */
	public static void printStudentsWithGPA4Helper(Node current) {
		if (current != null) {
			if (!current.isLeaf()) {
				if (current.getRightKey() != null) {
					if (!current.isLeaf()) {
						printStudentsWithGPA4Helper(current.getRightChild());
					}
					if (current.getRightKey().gpa == 4.0) {
						System.out.println(current.getRightKey().name);
					}
				}
				printStudentsWithGPA4Helper(current.getMiddleChild());
				if (current.getLeftKey().gpa == 4.0) {
					System.out.println(current.getLeftKey().name);
				}
				printStudentsWithGPA4Helper(current.getLeftChild());
			} else {
				if (current.getRightKey() != null) {
					if (current.getRightKey().gpa == 4.0) {
						System.out.println(current.getRightKey().name);
					}
				}
				if (current.getLeftKey().gpa == 4.0) {
					System.out.println(current.getLeftKey().name);
				}
			}

		}
	}

	/**
	 * This method reads list of students inserted in lexicographical order of names
	 * using in-order traversal, when kCounter==k then kthElement is assigned with
	 * the kth index of the inserted student elements list and it's a recursive
	 * method which takes root in the first recursion and for later recursions it
	 * takes left, middle, right children.
	 * 
	 * @param current: when first time recursion starts then current is root of the
	 *                 tree, then in the next recursions it is subtree of left,
	 *                 middle and right child
	 * @param k:       kth element in the list of students
	 */
	private void readKthElementInTree(Node current, int k) {
		if (current != null) {
			if (current.isLeaf()) {
				kCounter++;
				if (kCounter == k) {
					kthElement = current.getLeftKey();
				}
				if (current.getRightKey() != null) {
					kCounter++;
					if (kCounter == k) {
						kthElement = current.getRightKey();
					}
				}
			} else {
				readKthElementInTree(current.getLeftChild(), k);
				kCounter++;
				if (kCounter == k) {
					kthElement = current.getLeftKey();
				}
				readKthElementInTree(current.getMiddleChild(), k);
				if (current.getRightKey() != null) {
					if (!current.isLeaf()) {
						kCounter++;
					}
					if (kCounter == k) {
						kthElement = current.getRightKey();
					}
					readKthElementInTree(current.getRightChild(), k);
				}
			}
		}
	}
}
