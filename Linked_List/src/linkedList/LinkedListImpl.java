/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package linkedList;

public class LinkedListImpl implements LIST_Interface {
	Node headCell; // this will be the entry point to your linked list (the head)
	Node lastCell; // this is the Node at the end of the list... the starting place
					// if you wanted to traverse the list backwards

	public LinkedListImpl() {// this constructor is needed for testing purposes. Please don't modify!
		headCell = null; // Note that the root's data is not a true part of your data set!
		lastCell = null;
	}

	private int size = 0;

	public Node getRoot() { // leave this method as is, used by the grader to grab your linkedList easily.
		return headCell;
	}

	public Node getLast() { // leave this method as is, used by the grader to grab your linkedList easily.
		return lastCell;
	}

	@Override
	public boolean insert(double elt, int index) {
		if( index < 0) return false;
		if (index > size)
			return false;
		Node insertNode = new Node(elt);
		if (size == 0) {
			headCell = insertNode;
			lastCell = insertNode;
		} else if (index == 0) {
			insertNode.next = headCell;
			headCell.prev = insertNode;
			headCell = insertNode;
		} else if (index == size) {
			lastCell.next = insertNode;
			insertNode.prev = lastCell;
			lastCell = insertNode;
		} else {
			Node targetNode = headCell;
			for (int i = 0; i < index; i++) {
				targetNode = targetNode.next;
			}
			targetNode.prev.next = insertNode;
			insertNode.prev = targetNode.prev;
			targetNode.prev = insertNode;
			insertNode.next = targetNode;

		}
		size++;

		return true;
	}

	@Override
	public boolean insort(double elt) {
		if (elt < get(0)) {
			insert(elt, 0);
			return true;
		}
		for (int i = 1; i < size; i++) {
			if (elt > get(i - 1) | elt == get(i - 1))
				if (elt < get(i)) {
					insert(elt, i);
					return true;
				}
		}			insert(elt, size);

		return true;
	}

	@Override
	public boolean remove(int index) {
if( index < 0) return false;
		if (index > size - 1)
			return false;

		if (index == 0) {
			if (size == 1) {
				headCell = null;
				lastCell = null;
			} else {
				headCell = headCell.next;
				headCell.prev.next = null;
				headCell.prev = null;
			}
		} else if (index == size - 1) {
			lastCell = lastCell.prev;
			lastCell.next.prev = null;
			lastCell.next = null;
		} else {
			Node targetNode = headCell.next;
			for (int i = 0; i < index; i++)
				targetNode = targetNode.next;
			Node mendNode = targetNode.prev.prev;
			mendNode.next = targetNode;
			targetNode.prev = mendNode;
		}
		size--;
		return true;
	}

	@Override
	public double get(int index) {
		if (index > size - 1)
			return Double.NaN;
		Node targetNode = headCell;
		for (int i = 0; i < index; i++)
			targetNode = targetNode.next;
		return targetNode.data;

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public void clear() {
		headCell = null;
		lastCell = null;
		size = 0;

	}
}