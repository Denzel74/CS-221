package lesson;

import java.util.List;

/**
 * Represents sorting and searching using binary search and recursion
 * 
 * @author Denzel Balbosa
 */

public class MyBinarySearch implements BinarySearchInterface {
    // Variables
    int swaps = 0;
    int recur = 0;
    int compar = 0;

    /**
     * Sorting the list using selection sort
     * 
     * @param numbers - list passed in to sort
     */
    @Override
    public void sort(List<Integer> numbers) {
        int minIndex; // Minimun index
        Integer tempCopy; // To copy value

        // Loops through the list then swaps the lower values
        // to higher values in order
        for (int i = 0; i < numbers.size() - 1; i++) {
            minIndex = i;
            tempCopy = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(j) < numbers.get(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swaps++;
                numbers.set(i, numbers.get(minIndex));
                numbers.set(minIndex, tempCopy);
            }
        }
    }

    /**
     * Return the number of times numbers are swapped using the sort method
     */
    @Override
    public int getNumSwaps() {
        return swaps;
    }

    /**
     * Returns the index of the target number in a sorted list using binary search
     * @param numbers, target, lower, upper - list to search, value to search,
     * lower index of the list, upper value of the list
     */
    @Override
    public int search(List<Integer> numbers, int target, int lower, int upper) {
        int middle = (lower + upper) / 2; // The middle value
        int index = -1; // The index needed to find

        // If the list is empty return -1
        if (numbers.isEmpty()) {
            return index;
        }

        // If the target is in the middle of the list
        // or middle of the half splitted list
        if (target == numbers.get(middle)) {
            index = middle;
            compar++;
            return index;
        }
        // If the list only has one value
        // or the list is splitted down to one value
        if (lower == upper) {
            compar++;
            if (target == numbers.get(lower)) {
                index = lower;
                return index;
            } else {
                return index;
            }
            
        }
        compar++;
        // Do recursion if target is not found
        if (target < numbers.get(middle)) {
            recur++;
            index = search(numbers, target, lower, middle -1);
        } else {
            recur++;
            index = search(numbers, target, middle + 1, upper);
        }
        
        return index;
    }

    /**
     * Return the number of times recursion has been done in search
     */
    @Override
    public int getNumRecursions() {
        return recur;
    }

    /**
     * Return the number of times comparison has been done in search
     */
    @Override
    public int getNumComparisons() {
        return compar;
    }
    
}
