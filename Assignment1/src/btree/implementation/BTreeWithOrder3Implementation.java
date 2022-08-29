package implementation;

import datastructure.BTreeWithOrder3;
import datastructure.Node;
import datastructure.Student;
import utility.Utility;

/**
 * This class is the implementation of BTreeWithOrder3 interface
 */
public class BTreeWithOrder3Implementation implements BTreeWithOrder3 {
	private Node root;
	private int size;

	public BTreeWithOrder3Implementation() {
		root = new Node();
		size = 0;
	}

	/**
	 * @return size: returns size of the BTree
	 */
	@Override
	public int getSize() {
		return this.size;
	}

	/**
	 * this method inserts student element in the BTree
	 * 
	 * @param element: student object is stored in element
	 */
	@Override
	public void insert(Student element) {
		/*
		 * base case situation->if root doesn't exists then create a root node and then
		 * insert the element to the left key of the node or if node exists but the left
		 * key is empty then insert element to left key of the root.
		 */
		if (element != null) {
			if (root == null || root.getLeftKey() == null) {
				if (root == null)
					root = new Node();
				root.setLeftKey(element);
			} else {
				// when root is not null or the Left Key is not null, insertions are
				// done in insertion helper class
				Node newRoot = insertHelper(root, element);
				if (newRoot != null)
					// root is assigned to the new root
					root = newRoot;
			}
			this.size++;
		}
	}

	/**
	 * if the tree is not empty or k is not greater than tree size then this method
	 * prints details of Kth element from lexicographical order of students in
	 * BTree, if k is greater than tree size then it throws
	 * IndexOutOfBoundsException
	 * 
	 * @param k: Kth element in the tree
	 */
	public void getKthElementInTree(int k) {
		if (k - 1 > getSize()) {
			throw new IndexOutOfBoundsException("Given K is larger than size of the students list");
		} else if (isEmpty()) {
			System.out.println("The tree is empty.");
		} else
			Utility.getKtheElementHelper(root, k);
	}

	/** prints names of students with GPA 4.0 */
	@Override
	public void printStudentsWithGPA4() {
		System.out.println("List of students with GPA 4.0: ");
		Utility.printStudentsWithGPA4Helper(root);
	}

	/** this prints redIds of the students with GPA less than 2.85 */
	@Override
	public void printProbationStudents() {
		System.out.println("RedIds of Probation Students:"); 
		Utility.printProbationStudentsHelper(root);
	}

	/**
	 * checks if the tree is empty or not by checking if root or root's left key is
	 * empty
	 * 
	 * @return boolean value, true in case if the tree is empty else false
	 */
	private boolean isEmpty() {
		if (root == null)
			return true;
		if (root.getLeftKey() == null)
			return true;
		return false;
	}

	/**
	 * This is a recursive method and is a helper method which inserts the elements
	 * in the appropriate position by changing existing keys according to the
	 * lexicographical order
	 * 
	 * @param current: when first time recursion starts the current is tree root,
	 *                 and then in the next recursions it is subtree of either left
	 *                 or middle or right child
	 * @param element: student objects
	 * @return this method returns new root
	 */
	private Node insertHelper(Node current, Student element) {
		Node newParent = null;
		// if the current node is not equal to leaf
		if (!current.isLeaf()) {
			Node upChild = null;
			// The new element is smaller than the left element of the current node
			if (current.getLeftKey().name.compareTo(element.name) > 0) {
				// the element has been inserted on a 3-node where there were 2 elements
				upChild = insertHelper(current.getLeftChild(), element);
				// A new node comes from the left branch is not equals to null
				if (upChild != null) {
					// The new element, in this case, is always less than the current node's leftKey
					// so shift the current left element to the right
					if (current.isTwoNode()) {
						current.setRightKey(current.getLeftKey());
						current.setLeftKey(upChild.getLeftKey());
						current.setRightChild(current.getMiddleChild());
						current.setMiddleChild(upChild.getMiddleChild());
						current.setLeftChild(upChild.getLeftChild());
						// in this case, there is a new split, so the current element in the left will
						// go up
					} else {
						// copy the right part of the subtree
						Node rightSubTree = new Node(current.getRightKey(), null, current.getMiddleChild(),
								current.getRightChild());
						// Now we create the new parent with the right sub tree part
						newParent = new Node(current.getLeftKey(), null, upChild, rightSubTree);
					}
				}
				// the pushed up element is greater than the left element and less than the
				// right element
			} else if (current.isTwoNode()
					|| (current.isThreeNode() && current.getRightKey().name.compareTo(element.name) > 0)) {
				upChild = insertHelper(current.getMiddleChild(), element);
				// A new split
				if (upChild != null) {
					// if the right element is empty then set the pushed up child in the left and
					// the existing left element into the right
					if (current.isTwoNode()) {
						current.setRightKey(upChild.getLeftKey());
						current.setRightChild(upChild.getMiddleChild());
						current.setMiddleChild(upChild.getLeftChild());
						// A new split
					} else {
						Node left = new Node(current.getLeftKey(), null, current.getLeftChild(),
								upChild.getLeftChild());
						Node middle = new Node(current.getRightKey(), null, upChild.getMiddleChild(),
								current.getRightChild());
						newParent = new Node(upChild.getLeftKey(), null, left, middle);
					}
				}
				// The new element is greater than the right element
			} else if (current.isThreeNode() && current.getRightKey().name.compareTo(element.name) < 0) {
				upChild = insertHelper(current.getRightChild(), element);
				if (upChild != null) {
					Node leftSubTree = new Node(current.getLeftKey(), null, current.getLeftChild(),
							current.getMiddleChild());
					newParent = new Node(current.getRightKey(), null, leftSubTree, upChild);
				}
			}
		} else {
			// there is no right element if the current left element is bigger than the new
			// element then shift the left element to the right
			if (current.isTwoNode()) {
				if (current.getLeftKey().name.compareTo(element.name) > 0) {
					current.setRightKey(current.getLeftKey());
					current.setLeftKey(element);
					// if the new element is greater then insert it in the right key directly
				} else if (current.getLeftKey().name.compareTo(element.name) < 0)
					current.setRightKey(element);
				// there are 2 values in the node, if we have to insert another one then we have
				// to use split the node
			} else
				newParent = split(current, element);
		}
		return newParent;
	}

	/**
	 * This method creates a new node by splitting existing node based on the new
	 * insertion element value
	 * 
	 * @param currentNode
	 * @param element
	 * @return new parent after performing split
	 */
	private Node split(Node currentNode, Student element) {
		Node newParent = null;
		// if the left key of the current node is greater than the new element
		if (currentNode.getLeftKey().name.compareTo(element.name) > 0) {
			Node left = new Node(element, null);
			Node right = new Node(currentNode.getRightKey(), null);
			newParent = new Node(currentNode.getLeftKey(), null, left, right);
			// if the left key of the current node is lesser than the new element
		} else if (currentNode.getLeftKey().name.compareTo(element.name) < 0) {
			// The new element is greater than the current node's left element only
			if (currentNode.getRightKey().name.compareTo(element.name) > 0) {
				Node left = new Node(currentNode.getLeftKey(), null);
				Node right = new Node(currentNode.getRightKey(), null);
				newParent = new Node(element, null, left, right);
				// when the element is the greater than both the right and left keys
			} else {
				Node left = new Node(currentNode.getLeftKey(), null);
				Node right = new Node(element, null);
				newParent = new Node(currentNode.getRightKey(), null, left, right);
			}
		}
		return newParent;
	}

}