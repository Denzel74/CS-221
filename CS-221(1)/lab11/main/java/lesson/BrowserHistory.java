package lesson;

import java.util.NoSuchElementException;

/**
 * Class that represents a browser back button using a Doubly-Linked
 * List
 *
 * @author
 */

public class BrowserHistory<T> implements HistoryInterface<T> {

    // instance variables
    private DLLNode<T> head;
    private DLLNode<T> tail;
    private int size;

    /**
     * Constructor
     */
    public BrowserHistory() {
        head = tail = null;
        size = 0;
    }

    /**
     * Returns a reference to head
     * 
     * Used for testing purposes only!!
     */
    public DLLNode<T> getHead() {
        return head;
    }

    /**
     * Returns a reference to tail
     * 
     * Used for testing purposes only!!
     */
    public DLLNode<T> getTail() {
        return tail;
    }

    /**
     * Adds elem to the top of the stack
     *
     * @param elem
     */
    @Override
    public void push(T elem) {
      DLLNode<T> newNode = new DLLNode<T>(elem); // create a new node with elem

      // if size is 0 head and tail is the new node
      // else set head previous to new node
      // set new node next to head
      // set new node as head
      if (size == 0) {
        head = tail = newNode;
      } else {
        head.setPrev(newNode);
        newNode.setNext(head);
        head = newNode;
      }
      
      // increment size
      size ++;
    }

    /**
     * Removes an element from the top of the stack
     * 
     * @throws NoSuchElementException if the stack is empty
     * @return the element removed
     */
    @Override
    public T pop() {
        DLLNode<T> headCopy = head; // copy of head

        // if size is 0 throw exception
        // if next of head is null - head and tail are null
        // if there's more element - set head's next as head
        // and previous as null
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (head.getNext() == null) {
            head = tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }

        // decrement size
        size --;

        // return copy of what's removed
        return headCopy.getElement();
        
    }

    /**
     * Returns the very first element that was visited
     * i.e. the element at the bottom of the stack
     *
     * @throws NoSuchElementException if the stack is empty
     * @return element from the bottom of the stack
     */
    @Override
    public T firstVisited() {
        // throw exception if there's no elements
        // return the tail which is the first element added
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            return tail.getElement();
        }
    }

    /**
     * Returns the most recently element that was visited
     * i.e. the element at the top of the stack
     *
     * @throws NoSuchElementException if the stack is empty
     * @return element from the top of the stack
     */
    @Override
    public T lastVisited() {
        // throw exception if there's no elements
        // return the head which is the last element added
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            return head.getElement();
        }
    }

    /**
     * Returns a string containing each element in the stack 
     * separated by a colon
     * 
     * For example:
     * elem1:elem2:elem3:elem4:
     *
     * @param oldestFirst - if true, orders elements from the bottom to top of the stack
     *                      if false, orders elements from top to bottom of the stack
     */
    @Override
    public String getHistory(boolean oldestFirst) {
        DLLNode<T> node = new DLLNode<>(); // new empty node
        String historyList = ""; // empty list

        // if size is 0 return empty list
        // if false order list from top to bottom
        // if true order list from bottom to top
        if (size == 0) {
            return historyList;
        } else if (oldestFirst == false) {
            node = head;
            for (int i = 0; i < size; i++) {
                historyList += node.getElement() + ":";
                node = node.getNext();
            }
        } else if (oldestFirst == true) {
            node = tail;
            for (int i = 0; i < size; i++) {
                historyList += node.getElement() + ":";
                node = node.getPrev();
            }
        }
        // return the string created by order
        return historyList;
    }

     /**
     * Removes all instances of elem that are equal using .equals()
     * from the stack
     * 
     * @param elem the element to remove from the stack
     * @return true if there was at least one equivalent element
     *         removed; false otherwise
     */
    @Override
    public boolean removeFromHistory(T elem) {
       boolean hasElem = false; // Check if node exist
       DLLNode<T> current = head; // Starting current node

       // if there's no node return false
       if (size == 0) {
        return hasElem;
       }

       // if size is greater than 0 look for elem while not null
       if (size > 0) {

        while (current != null) {

            if (current.getElement().equals(elem) == true) {
                // create variables for changing nodes
                DLLNode<T> newHead = current.getNext();
                DLLNode<T> newTail = current.getPrev();
                DLLNode<T> beforeElem = current.getPrev();
                DLLNode<T> nextElem = current.getNext();
                hasElem = true;
                
                // if only 1 node exist
                // if elem is head
                // if elem is tail
                // if elem is somewhere in the middle
                if (size == 1) {
                    head = tail = null;
                } else if(current == head) {
                    newHead.setPrev(null);
                    head = newHead;

                } else if (current == tail) {
                    newTail.setNext(null);
                    tail = newTail;
                } else {
                    beforeElem.setNext(nextElem);
                    nextElem.setPrev(beforeElem);

                }
                // decrement size
                size--;
            }
            // get the next node
            current = current.getNext();

        }

       }
       // return true or false
       return hasElem;
    }

     /**
     * Returns number of elements current on the stack
     * 
     * @return number of items in the history
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a String containing summary information about the stack
     * in the form
     * 
     * [History(<number of items>): <top item>]
     *
     * @return String of summary information
     */
    @Override
    public String toString() {
        String historyString = "[History(" + size + "): ";

        if (size == 0) {
            historyString += "]";
        } else {
            historyString += head.getElement() + "]";
        }
                                
        
        return historyString;
    }

}
