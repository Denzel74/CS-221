package lesson;

/**
 * Defines an interface for a stack that tracks history, such as
 * browser history or actions that can be undone via an undo button
 *
 * DO NOT MODIFY
 * @author krodgers
 */

public interface HistoryInterface<T>{
    /**
     * Adds elem to the top of the stack
     *
     * @param elem - the element to add to the stack
     */
    public void push(T elem);

    /**
     * Removes an element from the top of the stack
     * 
     * @throws NoSuchElementException if the stack is empty
     * @return the element
     */
    public T pop();

    /**
     * Returns the very first element that was visited
     * i.e. the element at the bottom of the stack
     *
     * @throws NoSuchElementException if the stack is empty
     * @return element from the bottom of the stack
     */
    public T firstVisited();

    /**
     * Returns the most recently element that was visited
     * i.e. the element at the top of the stack
     *
     * @throws NoSuchElementException if the stack is empty
     * @return element from the top of the stack
     */
    public T lastVisited();

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
    public String getHistory(boolean oldestFirst);

    /**
     * Removes all instances of elem that are equal using .equals()
     * from the stack
     * 
     * @param elem the element to remove from the stack
     * @return true if there was at least one equivalent element
     *         removed; false otherwise
     */
    public boolean removeFromHistory(T elem);

    /**
     * Returns number of elements current on the stack
     * 
     * @return number of items in the history
     */
    public int size();
    
    /**
     * Returns a String containing summary information about the stack
     * in the form
     * 
     * [History(<number of items>): <top item>]
     *
     * @return String of summary information
     */
    public String toString();

}
