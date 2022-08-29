package datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

public class NullNode<E> implements BTreeNode<E> {
	public BTreeNode<E> getLeftChild() {
		return null;

	}

	public BTreeNode<E> getMiddleChild() {
		return null;

	}

	public BTreeNode<E> getRightChild() {
		return null;

	}

	public E getLeftKey() {
		return null;

	}

	public E getRightKey() {
		return null;

	}

	public void setLeftKey(E leftKey) {

	}

	public void setRightKey(E rightKey) {

	}

	public void setLeftChild(BTreeNode<E> leftChild) {

	}

	public void setMiddleChild(BTreeNode<E> middleChild) {

	}

	public void setRightChild(BTreeNode<E> rightChild) {

	}

	public int keySize() {
		return 0;
	}

	public boolean isNode() {
		return false;
	}

	public boolean isLeaf() {
		return true;
	}

	public String toString(StringBuilder str) {
		return " ";
	}

	public BTreeNode<E> addValue(E element, Comparator<E> comparator) {
		return null;
	}

	public void forEach(Consumer<? super E> action) {

	}

	public E getKey(int index) {
		return null;
	}

	public BTreeNode<E> getChild(int index) {
		return null;
	}

	public Object[] toArray(ArrayList<E> bTreeElements) {
		return null;
	}

}
