package datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Structure of Node for BTree and NullNode
 * 
 * @param <E>
 */
public interface BTreeNode<E> {

	public BTreeNode<E> getLeftChild();

	public BTreeNode<E> getMiddleChild();

	public BTreeNode<E> getRightChild();

	public E getLeftKey();

	public E getRightKey();

	public void setLeftKey(E leftKey);

	public void setRightKey(E rightKey);

	public void setLeftChild(BTreeNode<E> leftChild);

	public void setMiddleChild(BTreeNode<E> middleChild);

	public void setRightChild(BTreeNode<E> rightChild);

	public int keySize();

	public boolean isNode();

	public boolean isLeaf();

	public String toString(StringBuilder str);

	public BTreeNode<E> addValue(E element, Comparator<E> comparator);

	public void forEach(Consumer<? super E> action);

	public E getKey(int index);

	public BTreeNode<E> getChild(int index);

	public Object[] toArray(ArrayList<E> bTreeElements);

}
