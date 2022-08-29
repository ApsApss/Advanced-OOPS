package datastructure;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.function.Consumer;

/**
 * The elements are ordered by a {@link Comparator} provided at tree creation
 * time.
 * 
 * @param <E> the type of elements maintained by this set
 */
public class BTreeSet<E> extends AbstractSet<E> implements SortedSet<E> {
	private BTreeNode<E> root;
	private int size;
	private final Comparator<E> comparator;

	public BTreeSet(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	/**
	 * @return size: returns size of the BTree
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * @param element object is checked to be in this BTree
	 * @return boolean: {@code true} if this tree contains the specified element
	 *         else {@code false}
	 * @throws NullPointerException if the specified element is null
	 */
	@Override
	public boolean contains(Object element) {
		Iterator<E> iter = iterator();
		while (iter.hasNext()) {
			if (element.equals(iter.next())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * this method adds elements to the BTree and orders it based on comparator
	 * 
	 * @param element:object that is added to the BTree
	 * @return boolean:{@code true} if this BTree did not already contain the
	 *         element or element is not null
	 */
	@Override
	public boolean add(E element) {
		if (element != null) {
			if (root == null) {
				root = new Node<E>();
			}
			if (root.getLeftKey() == null) {
				root.setLeftKey(element);
			} else {
				BTreeNode<E> newRoot = root.addValue(element, comparator);
				if (newRoot.isNode()) {
					root = newRoot;
				}
			}
			this.size++;
			return true;
		}
		return false;
	}

	/**
	 * @return an internal iterator over the elements in this BTree in reverse of
	 *         in-Order sequence
	 */
	@Override
	public void forEach(Consumer<? super E> action) {
		root.forEach(action);
	}

	/**
	 * @return boolean: {@code true} if the tree is empty else {@code false}
	 */
	@Override
	public boolean isEmpty() {
		if (root == null || root.getLeftKey() == null)
			return true;
		else
			return false;
	}

	/**
	 * @return an external iterator over the elements in this BTree in in-Order
	 *         sequence
	 */
	@Override
	public Iterator<E> iterator() {
		return new BTreeIterator<E>(root);
	}

	@Override
	public Object[] toArray() {
		return root.toArray(new ArrayList<E>());
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Comparator<? super E> comparator() {
		return this.comparator;
	}

	@Override
	public SortedSet<E> subSet(E fromElement, E toElement) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SortedSet<E> headSet(E toElement) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SortedSet<E> tailSet(E fromElement) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E first() {
		throw new UnsupportedOperationException();
	}

	@Override
	public E last() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return a string representation of the objects in the BTree
	 */
	@Override
	public String toString() {
		return root.toString(new StringBuilder());
	}
}