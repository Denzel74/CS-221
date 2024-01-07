package lesson;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Array-based implementation of TheList 
 *
 * An Iterator with working * hasNext and next methods is implemented,
 * but ListIterator is * unsupported.
 * 
 * @author Denzel Balbosa
 *
 * @param <T> type to store
 */
public class TheArrayList<T> implements TheList<T> {
    
    /** Private variables and constants */
    private static final int DEFAULT_CAPACITY = 15;
    private static final int NOT_FOUND = -1;
        
    private T[] data;
    private int rear;

        
    /** Creates an empty list with default initial capacity */
    public TheArrayList() {
        this(DEFAULT_CAPACITY);
    }
        
    /** 
     * Creates an empty list with the given initial capacity
     * @param initialCapacity
     */
    @SuppressWarnings("unchecked")
    public TheArrayList(int initialCapacity) {
        data = (T[])(new Object[initialCapacity]);
        rear = 0;
    }
        
    /** Double the capacity of data */
    private void expandCapacity() {
        data = Arrays.copyOf(data, data.length*2);
    }

    /** Adds an element at the front of the data list
     * @param element
     */
    @Override
    public void addFront(T element) {

        for (int i = rear - 1; i >= 0; i--) {
           data[i + 1] = data[i];
        }
        data[0] = element;
        rear++;
        
    }

    /** Adds an element at the end of the data list
     * @param element
     */
    @Override
    public void addBack(T element) {
        data[rear] = element;
        rear++;
    }

    /** Adds an element at the end of the data list
     * @param element
     */
    @Override
    public void add(T element) {
        data[rear] = element;
        rear++;
    }

    /** Adds an element after the specified element
     * @param element, target
     */
    @Override
    public void addAfter(T element, T target) {
        // find the target
        int targetIndex = find(target);
        if (targetIndex < 0) {
            // throw exception if not found
            throw new NoSuchElementException();
        }

        // expand data capacity if needed
        expandCapacity();
        // shift all elements following the target index back by one
        for (int i = rear; i > targetIndex + 1; i--) {
            data[i] = data[i - 1];

        }
        // insert the element
        data[targetIndex + 1] = element;
        // update rear
        rear++;
    }

    /** Adds an element before the specified element
     * @param element, target
     */
    @Override
    public void addBefore(T element, T target) {
        // find the target
        int targetIndex = find(target);
        if (targetIndex < 0) {
            // throw exception if not found
            throw new NoSuchElementException();
        }

        // expand data capacity if needed
        expandCapacity();
        // shift all elements following the target index back by one
        for (int i = rear; i >= targetIndex; i--) {
            data[i + 1] = data[i];

        }
        // insert the element
        data[targetIndex] = element;
        // update rear
        rear++;
                
    }

    /** Adds an element to a specified index of the data list
     * @param index, element
     */
    @Override
    public void add(int index, T element) {
        if (index > 0 && data[index - 1] == null) {
            throw new IndexOutOfBoundsException();
        }
        
        for (int i = rear; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        rear++;
    }

    /** Removes the first element of the data list */
    @Override
    public T removeFront() {
        T element = remove(data[0]);
        return element;
    }

    /** Removes the last element of the data list */
    @Override
    public T removeBack() {
        if (rear == 0) {
            throw new NoSuchElementException();
        }
        T element = remove(data[rear - 1]);
        return element;
    }

    /** Removes the specified element in the data list 
     * @param element
     */
    @Override
    public T remove(T element) {
        int index = find(element);
        if (index == NOT_FOUND) {
            throw new NoSuchElementException();
        }
            
        T retVal = data[index];
            
        rear--;
        //shift elements
        for (int i = index; i < rear; i++) {
            data[i] = data[i+1];
        }
        data[rear] = null;
                
        return retVal;
    }

    /** Removes the specified index in the data list
     * @param index
     */
    @Override
    public T remove(int index) {
        if (data[index] == null) {
            throw new NoSuchElementException();
        }
        T element = remove(data[index]);
        return element;
    }

    /** Updates the specified index with a specified element 
     * @param index, element
     */
    @Override
    public void update(int index, T element) {
        if (data[index] == null) {
            throw new NoSuchElementException();
        }
        data[index] = element;
    }

    /** Returns the  element of the specified index in the data list
     * @param index
     */
    @Override
    public T get(int index) {
        if(rear == 0 || index >= rear || index < 0)
            throw new NoSuchElementException();
        return data[index];
    }

    /** Returns the index of a specified element in the data list
     * @param element
     */
    @Override
    public int find(T element) {
        int index = NOT_FOUND;
            
        if (rear > 0) {
            int i = 0;
            while (index == NOT_FOUND && i < rear) {
                if (element.equals(data[i])) {
                    index = i;
                } else {
                    i++;
                }
            }
        }
                
        return index;
    }

    /** Returns the first element of the data list */
    @Override
    public T first() {
        if (data[0] == null) {
            throw new NoSuchElementException();
        }
        T element = data[0]; 
        return element;
    }

    /** Returns the last element of the data list */
    @Override
    public T last() {
        if (data[0] == null) {
            throw new NoSuchElementException();
        }
        T element = data[rear - 1];
        return element;
    }

    /** Clears all the list of data */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        data = (T[])(new Object[data.length]);
        rear = 0;
    }

    /** Returns the size of the arraylist of data */
    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                size = i;
                break;
            }
        }
        return size;
    }

    /** Returns a calling to get a new iterator*/
    @Override
    public Iterator<T> iterator() {
        return new TheIterator();
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

    /** Iterator for TheArrayList */
    private class TheIterator implements Iterator<T> {
        private int nextIndex;
                
        public TheIterator() {
            nextIndex = 0;
        }

        /** Returns true if data has more elements inside the array */
        @Override
        public boolean hasNext() {
            if(data[nextIndex] != null) {
                return true;
            } else {
                return false;
            }
        }

        /** Returns the next element in the list */ 
        @Override
        public T next() {
            T next = data[nextIndex];
            nextIndex += 1;
            return next;
        }

        @Override
        public String toString() {
            String s = "[";
            
            for (int i = 0; i < rear; i++) {
                if (i < rear - 1) {
                    s += data[i] + ", ";
                } else {
                    s += data[i];
                }
            }
            
            s += "]";
            
            return s;
            
        }
                
    }
}
