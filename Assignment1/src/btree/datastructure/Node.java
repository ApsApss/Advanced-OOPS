package datastructure;

/**
 * Node class represents structure of each node in BTree with order 3. Node with
 * no right key contains 2 children and node with right key contains 3 children.
 */
public class Node {
	private Node leftChild, middleChild, rightChild;
	private Student leftKey, rightKey;

	public Node() {
		leftChild = middleChild = rightChild = null;
		leftKey = rightKey = null;
	}

	public Node(Student leftKey, Student rightKey) {
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		leftChild = middleChild = rightChild = null;
	}

	public Node(Student leftKey, Student rightKey, Node leftChild, Node midChild) {
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.leftChild = leftChild;
		this.middleChild = midChild;
	}

	public Node getLeftChild() {
		return this.leftChild;
	}

	public Node getRightChild() {
		return this.rightChild;
	}

	public Node getMiddleChild() {
		return this.middleChild;
	}

	public Student getLeftKey() {
		return this.leftKey;
	}

	public Student getRightKey() {
		return this.rightKey;
	}

	public void setLeftKey(Student leftKey) {
		this.leftKey = leftKey;
	}

	public void setRightKey(Student rightKey) {
		this.rightKey = rightKey;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public void setMiddleChild(Node middleChild) {
		this.middleChild = middleChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	// to check if the node is leaf
	public boolean isLeaf() {
		return leftChild == null && middleChild == null && rightChild == null;
	}

	// to check whether the node has 2 children or not
	public boolean isTwoNode() {
		return rightKey == null;
	}

	// to check whether the node has 3 children or not
	public boolean isThreeNode() {
		return rightKey != null;
	}
}