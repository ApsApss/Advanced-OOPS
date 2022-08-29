package datastructure;

import java.util.Iterator;
import java.util.Stack;

/**
 * This is the external iterator for BTreeSet, which overrides hasNext(), next()
 * methods in the Iterator. Implementation uses a stack to store visited Nodes
 * and count of visited nodes
 *
 * @param <E>
 */
public class BTreeIterator<E> implements Iterator<E> {
	/**
	 * This is the structure of each entry to store inside the stack, currentNode,
	 * visitedNodeCount are used to store node and visited count that needs to be
	 * pushed to stack
	 * 
	 * @param <E>
	 */
	@SuppressWarnings("hiding")
	class NodeEntryStack<E> {
		private BTreeNode<E> currentNode;
		private int visitedNodeCount;
	}

	private Stack<NodeEntryStack<E>> nodeEntryStack;

	public BTreeIterator(BTreeNode<E> root) {
		nodeEntryStack = new Stack<>();
		if (root.keySize() > 0)
			pushLeftChild(root);
	}

	/**
	 * @return boolean:{@code-true} if node entry stack is not empty else
	 *         {@code-false}
	 */
	@Override
	public boolean hasNext() {
		return !nodeEntryStack.isEmpty();
	}

	/**
	 * @return E: return element based on in-Order traversal
	 */
	@Override
	public E next() {
		E element = null;
		if (hasNext()) {
			NodeEntryStack<E> currentEntry = nodeEntryStack.peek();
			BTreeNode<E> currentNode = currentEntry.currentNode;
			int index = currentEntry.visitedNodeCount;
			element = (E) currentNode.getKey(index);
			index++;
			if (index < currentNode.keySize())
				currentEntry.visitedNodeCount = index;
			else
				nodeEntryStack.pop();
			if (!currentNode.isLeaf())
				pushLeftChild(currentNode.getChild(index));
		}
		return element;
	}

	/**
	 * this method pushes left most node to the Stack
	 * 
	 * @param currentNode
	 */
	private void pushLeftChild(BTreeNode<E> currentNode) {
		while (true) {
			NodeEntryStack<E> entry = new NodeEntryStack<E>();
			entry.currentNode = currentNode;
			entry.visitedNodeCount = 0;
			nodeEntryStack.push(entry);
			if (currentNode.isLeaf())
				break;
			currentNode = currentNode.getLeftChild();
		}
	}
}
