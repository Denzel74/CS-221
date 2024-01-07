package lesson;

import java.util.NoSuchElementException;

import javax.lang.model.element.Element;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Single-linked node implementation of TheList.
 * An Iterator is implemented, but ListIterator is unsupported.
 * 
 * @author Denzel Balbosa
 * 
 * @param <T> type to store
 */

public class TheSinglyLinkedList<T> implements TheList<T> {

	/** Private variables */
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /** Creates an empty list */
    public TheSinglyLinkedList() {
	head = tail = null;
	size = 0;
    }

	/** Adds an element at the front of the data list
     * @param element
     */
    @Override
    public void addFront(T element) {
	// create new node
	Node<T> newHead = new Node<T>(element);

	// set node's next to point to current head
	newHead.setNext(head);

	// update head
	head = newHead;

	// if this is the first node in the list
	// head and tail point to same node
	if (size == 0)
	    tail = head;

	// update size
	size++;
    }

	/** Adds an element at the end of the data list
     * @param element
     */
    @Override
    public void addBack(T element) {
		// create a new node
		Node<T> newNode = new Node<T>(element);

		// if this is the first node in the list
		// head and tail point to same node
		// if not, set tail next to newNode 
		// and newNode into tail
		if (size == 0) {
			head = tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}

		// update size
		size++;

    }

	/** Adds an element at the end of the data list
     * @param element
     */
    @Override
    public void add(T element) {
		// create a new node
		Node<T> newNode = new Node<T>(element);

		// if this is the first node in the list
		// head and tail point to same node
		// if not, set tail next to newNode 
		// and newNode into tail
		if (size == 0) {
			head = tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}

		// update size
		size++;
    }

	/** Adds an element after the specified element
     * @param element, target
     */
    @Override
    public void addAfter(T element, T target) {
	// locate target Node
	Node<T> targetNode = head;
	boolean foundIt = false;
	while (targetNode != null && !foundIt) {
		if (targetNode.getElement().equals(target)) {
			foundIt = true;
		} else {
			targetNode = targetNode.getNext();
		}
	}
	// throw NoSuchElementException if not found
	if (!foundIt || targetNode == null) {
		throw new NoSuchElementException();
	}
	// create new Node with element
	Node<T> newNode = new Node<T>(element);
	// set new Node's next to target Node's next
	newNode.setNext(targetNode.getNext());
	// set target Node's next to new Node
	targetNode.setNext(newNode);
	// update tail if necessary
	if (targetNode == tail) {
		tail = newNode;
	}
	// increment size
	size++;
    }

	/** Adds an element before the specified element
     * @param element, target
     */
    @Override
    public void addBefore(T element, T target) {
	Node<T> targetNode = head;
	Node<T> newNode = new Node<T>(element);
	Node<T> previous = null;
	boolean foundIt = false;
	
	// locate target node
	while (targetNode != null && !foundIt) {
		if (targetNode.getElement().equals(target)) {
			foundIt = true;
		} else {
			previous = targetNode;
			targetNode = targetNode.getNext();
		}
	}

	// throw NoSuchElementException if not found
	if (!foundIt || targetNode == null) {
		throw new NoSuchElementException();
	}

	// set there's no previous means head is targetNode
	// or set newNode next to targetNode
	if (previous == null) {
		newNode.setNext(targetNode);
		head = newNode;
	} else {
		newNode.setNext(targetNode);
		previous.setNext(newNode);
	}

	// update size
	size++;
    }

	/** Adds an element to a specified index of the list
     * @param index, element
     */
    @Override
    public void add(int index, T element) {
	Node<T> previous = null;
	Node<T> targetIndex = head;
	Node<T> newNode = new Node<T>(element);
	int i;

	if (index >= (size + 1) || index < 0) {
		throw new IndexOutOfBoundsException();
	}

	if (index == 0) {
		newNode.setNext(head);
		head = newNode;
	} else {
		for (i = 0; i < index; i++) {
			previous = targetIndex;
			targetIndex = targetIndex.getNext();
		}
		previous.setNext(newNode);
		newNode.setNext(targetIndex);
	}
	

	size++;
    }

	/** Removes the first element of the list */
    @Override
    public T removeFront() {
	// check there are nodes to remove
	if (size == 0)
	    throw new NoSuchElementException();

	// store value to return
	T val = head.getElement();

	if (size == 1) {
	    // list only contains a single node
	    // head and tail point to the same node
	    // head.next() would be null
	    head = tail = null;
	} else
	    head = head.getNext(); // update head

	// update size
	size--;

	// return removed value
	return val;
    }

	/** Removes the last element of the list */
    @Override
    public T removeBack() {
	Node<T> current = head;
	Node<T> previous = null;
	int i;
	// check there are nodes to remove
	if (size == 0)
	    throw new NoSuchElementException();

	// store value to return
	T val = tail.getElement();

	if (size == 1) {
	    // list only contains a single node
	    // head and tail point to the same node
	    // head.next() would be null
	    head = tail = null;
	} else {
		// take the node before tail
		for (i = 0; i < size; i++) {
			previous = current; 
			current.getNext();
		}
		// update tail
		tail = previous;
	}

	// update size
	size--;

	// return value removed
	return val;
    }

	/** Removes the specified element in the list 
     * @param element
     */
    @Override
    public T remove(T element) {
	if (size == 0) {
	    throw new NoSuchElementException();
	}

	boolean found = false;
	Node<T> previous = null;
	Node<T> current = head;

	while (current != null && !found) {
	    if (element.equals(current.getElement())) {
		found = true;
	    } else {
		previous = current;
		current = current.getNext();
	    }
	}

	if (!found) {
	    throw new NoSuchElementException();
	}

	if (size() == 1) { // only node
	    head = tail = null;
	} else if (current == head) { // first node
	    head = current.getNext();
	} else if (current == tail) { // last node
	    tail = previous;
	    tail.setNext(null);
	} else { // somewhere in the middle
	    previous.setNext(current.getNext());
	}

	size--;

	return current.getElement();
    }

	/** Removes the specified index in the list
     * @param index
     */
    @Override
    public T remove(int index) {

	Node<T> previous = null;
	Node<T> current = head;
	int i;
	
	if (size == 0 || index >= size) {
		throw new NoSuchElementException();
	}

	if (index >= size || index < 0) {
		throw new IndexOutOfBoundsException();
	}

	for (i = 0; i < index; i++) {
		previous = current;
		current = current.getNext();
	}

	if (size() == 1) { // only node
	    head = tail = null;
	} else if (current == head) { // first node
	    head = current.getNext();
	} else if (current == tail) { // last node
	    tail = previous;
	    tail.setNext(null);
	} else { // somewhere in the middle
	    previous.setNext(current.getNext());
	}

	// decrement size
	size--;

	// return the value removed
	return current.getElement();
    }

	/** Updates the specified index with a specified element 
     * @param index, element
     */
    @Override
    public void update(int index, T element) {
	
	Node<T> targetIndex = head;
	Node<T> previous = null;
	Node<T> newNode = new Node<T>(element);
	int i;

	if (size == 0 || index >= size) {
		throw new NoSuchElementException();
	}

	if (index >= size || index < 0) {
		throw new IndexOutOfBoundsException();
	}

	if (index == 0) {
		newNode.setNext(head.getNext());
		head = newNode;
	} else {
		for (i = 0; i < index; i++) {
			previous = targetIndex;
			targetIndex = targetIndex.getNext();
		}
		previous.setNext(newNode);
		newNode.setNext(targetIndex.getNext());
		
	}

    }

	/** Returns the  element of the specified index in the list
     * @param index
     */
    @Override
    public T get(int index) {
		if (index < 0 || index >= size) {
			throw new NoSuchElementException();
		}

		if (index == 0) {
			return head.getElement();
		}

		Node<T> node = head;
		for (int i = 0; i < index; i++) {
			node = node.getNext();
		}

	return node.getElement();
    }

	/** Returns the index of a specified element in the list
     * @param element
     */
    @Override
    public int find(T element) {
	int index = -1;

	Node<T> curr = head;
	int currIndex = 0;
	while(curr != null && index < 0) {
		if(curr.getElement().equals(element)) {
			index = currIndex;
		}
		currIndex++;
		curr = curr.getNext();
	}
	return index;
    }

	/** Returns the first value of the list */
    @Override
    public T first() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
	return head.getElement();
    }

	/** Returns the last value of the list */
    @Override
    public T last() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
	return tail.getElement();
    }

	/** Clears the list */
    @Override
    public void clear() {
		head = tail = null;
		size = 0;
    }

	/** Returns the size of the list */
    @Override
    public int size() {
	return size;
    }

	/** Returns a list of the nodes into string */
    @Override
    public String toString() {
		// create a new node
		Node<T> current = head;

		// start of the string
		String s = "[";

		// if list is empty return space and closed bracket
		if (size == 0) {
			s += " ]";
			return s;
		}
		// when current is not null add element to list
		// with ", " and if it's the last value return
		// with no ", "
		while (current != null) {
			if (current.getNext() != null) {
				s += current.getElement() + ", ";
			} else {
				s += current.getElement();
			}
			current.getNext();
		}

		// close the bracket with list
		s += "]";
		
		// return the string
	return s;
    }

	/** Returns a calling to get a new SLLIterator */
    @Override
    public Iterator<T> iterator() {
	return new SLLIterator();
    }

	/** Exception for ListIterator with UnsupportedOperationException()*/
    @Override
    public ListIterator<T> listIterator() {
	throw new UnsupportedOperationException();
    }

	/** Exception for ListIterator with parameters
     *  with UnsupportedOperationException()
     */
    @Override
    public ListIterator<T> listIterator(int startingIndex) {
	throw new UnsupportedOperationException();
    }

    /** Iterator for TheSinglyLinkedList */
    private class SLLIterator implements Iterator<T> {
	private Node<T> nextNode;

	/** Creates a new iterator for the list */
	public SLLIterator() {
	    nextNode = head;
	}

	/** Returns true if there's a next value in the list */
	@Override
	public boolean hasNext() {

	    return nextNode.getNext() != null;
	    
	}
	/** Returns the next value */
	@Override
	public T next() {
		if (size == 0) {
			throw new NoSuchElementException();
		}

	    T next = nextNode.getElement();
		nextNode.getNext();

	    return next;
	}

    }
}
