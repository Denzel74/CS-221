package lesson;

/**
 * Node represents a node in a doubly linked list.
 *
 * DO NOT MODIFY THIS FILE
 * 
 * @author Java Foundations, mvail, krodgers
 * @version 4.1
 */

public class DLLNode<E> {
    private DLLNode<E> next;
    private DLLNode<E> prev;
    private E element;

    /**
     * Creates an empty node.
     */
    public DLLNode() {
        next = null;
        prev = null;
        element = null;
    }

    /**
     * Creates a node storing the specified element.
     *
     * @param elem
     *             the element to be stored within the new node
     */
    public DLLNode(E elem) {
        next = null;
        prev = null;
        element = elem;
    }

    /**
     * Creates a node storing the specified element.
     *
     * @param elem
     *             the element to be stored within the new node
     */
    public DLLNode(E elem, DLLNode<E> node) {
        this.next = node;
        element = elem;
    }

    /**
     * Returns the node that follows this one.
     *
     * @return the node that follows the current one
     */
    public DLLNode<E> getNext() {
        return next;
    }

    /**
     * Sets the node that follows this one.
     *
     * @param node
     *             the node to be set to follow the current one
     */
    public void setNext(DLLNode<E> node) {
        next = node;
    }

    /**
     * Returns the node that comes before this node
     *
     * @return the node that comes before the current one
     */
    public DLLNode<E> getPrev() {
        return prev;
    }

    /**
     * Sets the node that comes before this one
     *
     * @param node
     *             the node to be set to come before this one
     */
    public void setPrev(DLLNode<E> node) {
        prev = node;
    }

    /**
     * Returns the element stored in this node.
     *
     * @return the element stored in this node
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets the element stored in this node.
     *
     * @param elem
     *             the element to be stored in this node
     */
    public void setElement(E elem) {
        element = elem;
    }

    @Override
    public String toString() {
        String repr = "Element: " + element.toString() + " Has next: " + (next != null);
        repr += " Has prev: " + (prev != null);
        return repr;
    }
}
