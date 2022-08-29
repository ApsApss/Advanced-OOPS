package datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Node class represents structure of BTree node with order 3. Node with no
 * right key contains 2 children and node with right key contains 3 children.
 */
public class Node<E> implements BTreeNode<E> {
	private BTreeNode<E> leftChild, middleChild, rightChild;
	private E leftKey, rightKey;

	public Node() {
		leftChild = middleChild = rightChild = new NullNode<E>();
		leftKey = rightKey = null;
	}

	public Node(E leftKey, E rightKey) {
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		leftChild = middleChild = rightChild = new NullNode<E>();
	}

	public Node(E leftKey, E rightKey, BTreeNode<E> leftChild, BTreeNode<E> midChild) {
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		this.leftChild = leftChild;
		this.middleChild = midChild;
		rightChild = new NullNode<E>();
	}

	public BTreeNode<E> getLeftChild() {
		return leftChild;
	}

	public BTreeNode<E> getMiddleChild() {
		return middleChild;
	}

	public BTreeNode<E> getRightChild() {
		return rightChild;
	}

	public E getLeftKey() {
		return leftKey;
	}

	public E getRightKey() {
		return rightKey;
	}

	public void setLeftChild(BTreeNode<E> leftChild) {
		this.leftChild = leftChild;
	}

	public void setMiddleChild(BTreeNode<E> middleChild) {
		this.middleChild = middleChild;
	}

	public void setRightChild(BTreeNode<E> rightChild) {
		this.rightChild = rightChild;
	}

	public void setLeftKey(E leftKey) {
		this.leftKey = leftKey;
	}

	public void setRightKey(E rightKey) {
		this.rightKey = rightKey;
	}

	public boolean isLeaf() {
		return !(leftChild.isNode() || middleChild.isNode() || rightChild.isNode());
	}

	public int keySize() {
		int keySize = 0;
		if (leftKey != null) {
			keySize = 1;
		}
		if (leftKey != null && rightKey != null) {
			keySize = 2;
		}
		return keySize;
	}

	/**
	 * @param index
	 * @return- returns node's key of index provided
	 */
	public E getKey(int index) {
		switch (index) {
		case 0:
			return leftKey;
		case 1:
			return rightKey;
		default:
			return null;
		}
	}

	/**
	 * @param index
	 * @return- returns node's child of index provided
	 */
	public BTreeNode<E> getChild(int index) {
		switch (index) {
		case 0:
			return leftChild;
		case 1:
			return middleChild;
		case 2:
			return rightChild;
		default:
			return null;
		}
	}

	public boolean isNode() {
		return true;
	}

	/**
	 * This is a recursive method and which adds the elements in the appropriate
	 * position in the node by changing existing elements according to the BTree
	 * order
	 * 
	 * @param element:    Objects that are inserted in to the Node
	 * @param comparator: based on the comparator each element in BTree is ordered
	 * @return this method returns new root
	 */
	public BTreeNode<E> addValue(E element, Comparator<E> comparator) {
		BTreeNode<E> newParent = new NullNode<E>();
		if (!isLeaf()) {
			BTreeNode<E> upChild = new NullNode<E>();
			if (comparator.compare(leftKey, element) > 0) {
				// the element has been inserted on a 3-node where there were 2 elements
				upChild = leftChild.addValue(element, comparator);
				// A new node comes from the left branch is not equals to null
				if (upChild.isNode()) {
					// The new element, in this case, is always less than the current node's leftKey
					// so shift the current left element to the right
					if (rightKey == null) {
						rightKey = leftKey;
						leftKey = upChild.getLeftKey();
						rightChild = middleChild;
						middleChild = upChild.getMiddleChild();
						leftChild = upChild.getLeftChild();
						// in this case, there is a new split, so the current element in the left will
						// go up
					} else {
						BTreeNode<E> rightSubTree = new Node<E>(rightKey, null, middleChild, rightChild);
						// Now we create the new parent with the right sub tree part
						newParent = new Node<E>(leftKey, null, upChild, rightSubTree);
					}
				}
				// the pushed up element is greater than the left element and less than the
				// right element
			} else if (rightKey == null || (rightKey != null && comparator.compare(rightKey, element) > 0)) {
				upChild = middleChild.addValue(element, comparator);
				// A new split
				if (upChild.isNode()) {
					// if the right element is empty then set the pushed up child in the left and
					// the existing left element into the right
					if (rightKey == null) {
						rightKey = upChild.getLeftKey();
						rightChild = upChild.getMiddleChild();
						middleChild = upChild.getLeftChild();
					} else {
						Node<E> left = new Node<E>(leftKey, null, leftChild, upChild.getLeftChild());
						Node<E> middle = new Node<E>(rightKey, null, upChild.getMiddleChild(), rightChild);
						newParent = new Node<E>(upChild.getLeftKey(), null, left, middle);
					}
				}
			} else if (rightKey != null && comparator.compare(rightKey, element) < 0) {
				upChild = rightChild.addValue(element, comparator);
				if (upChild.isNode()) {
					Node<E> leftSubTree = new Node<E>(leftKey, null, leftChild, middleChild);
					newParent = new Node<E>(rightKey, null, leftSubTree, upChild);
				}
			}
		} else {
			// there is no right element if the current left element is bigger than the new
			// element then shift the left element to the right
			if (rightKey == null) {
				if (comparator.compare(leftKey, element) > 0) {
					rightKey = leftKey;
					leftKey = element;
					// if the new element is greater then insert it in the right key directly
				} else if (comparator.compare(leftKey, element) < 0)
					rightKey = element;
				// there are 2 values in the node, if we have to insert another one then we have
				// to split the node
			} else
				newParent = split(element, comparator);
		}
		return newParent;
	}

	/**
	 * This method creates a new node by splitting existing node based on the new
	 * insertion element value
	 * 
	 * @param element:    Objects that are inserted in to the Node
	 * @param comparator: based on the comparator each element in BTree is ordered
	 * @return new parent after performing split
	 */
	private BTreeNode<E> split(E element, Comparator<E> comparator) {
		BTreeNode<E> newParent = new NullNode<E>();
		// if the left key of the current node is greater than the new element
		if (comparator.compare(leftKey, element) > 0) {
			Node<E> left = new Node<E>(element, null);
			Node<E> right = new Node<E>(rightKey, null);
			newParent = new Node<E>(leftKey, null, left, right);
			// if the left key of the current node is lesser than the new element
		} else if (comparator.compare(leftKey, element) < 0) {
			// The new element is greater than the current node's left element only
			if (comparator.compare(rightKey, element) > 0) {
				Node<E> left = new Node<E>(leftKey, null);
				Node<E> right = new Node<E>(rightKey, null);
				newParent = new Node<E>(element, null, left, right);
				// when the element is the greater than both the right and left keys
			} else {
				Node<E> left = new Node<E>(leftKey, null);
				Node<E> right = new Node<E>(element, null);
				newParent = new Node<E>(rightKey, null, left, right);
			}
		}
		return newParent;
	}

	/**
	 * This method recursively calls BTree nodes(right->root->left children) and
	 * sends elements to accept method, which is called by the internal iterator
	 * (forEach) in BTree
	 * 
	 * @param action
	 */
	public void forEach(Consumer<? super E> action) {
		if (rightKey != null) {
			action.accept(rightKey);
			rightChild.forEach(action);
		}
		middleChild.forEach(action);
		action.accept(leftKey);
		leftChild.forEach(action);
	}

	/**
	 * @param bTreeElementsString
	 * @return a string representation of the objects in the left, middle and right
	 *         children
	 */
	public String toString(StringBuilder bTreeElementsString) {
		leftChild.toString(bTreeElementsString);
		bTreeElementsString.append(leftKey);
		middleChild.toString(bTreeElementsString);
		if (rightKey != null) {
			bTreeElementsString.append(rightKey);
			rightChild.toString(bTreeElementsString);
		}
		return bTreeElementsString.toString();
	}

	/**
	 * @param bTreeElements
	 * @return an array of objects in the left, middle and right children
	 */
	public Object[] toArray(ArrayList<E> bTreeElements) {
		leftChild.toArray(bTreeElements);
		bTreeElements.add(leftKey);
		middleChild.toArray(bTreeElements);
		if (rightKey != null) {
			bTreeElements.add(rightKey);
			rightChild.toArray(bTreeElements);
		}
		return bTreeElements.toArray();
	}
}