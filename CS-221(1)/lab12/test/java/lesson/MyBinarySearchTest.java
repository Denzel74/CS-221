package lesson;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.List;

/**
 * Tests MyBinarySearch
 * 
 * @author Denzel Balbosa
 */

public class MyBinarySearchTest {

   // Lists and variables
   List<Integer> listOfNums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
   List<Integer> listOfRandomSorted = Arrays.asList(3, 5, 9, 11, 18, 24, 43, 68, 72);
   List<Integer> listWithRepeat = Arrays.asList(1, 2, 2, 3, 4, 5, 6, 7, 8);
   List<Integer> emptyList = Arrays.asList();
   List<Integer> oneList = Arrays.asList(1);
   

   // Test Methods

   // Testing sort method working correctly
   @Test
   public void testSort() {
      BinarySearchInterface bin = new MyBinarySearch();
      List<Integer> unsortedNums = Arrays.asList(5, 4, 3, 2, 1);
      bin.sort(unsortedNums);
      int actual = bin.search(unsortedNums, 1, 0, unsortedNums.size() - 1);
      assertEquals(actual, 0);
      actual = bin.search(unsortedNums, 5, 0, unsortedNums.size() - 1);
      assertEquals(actual, 4);
      actual = bin.search(unsortedNums, 3, 0, unsortedNums.size() - 1);
      assertEquals(actual, 2);
   }

   // Testing swap method working correctly
   @Test
   public void testSwaps() {
      BinarySearchInterface bin = new MyBinarySearch();
      List<Integer> unsortedNums = Arrays.asList(5, 4, 3, 2, 1);
      bin.sort(unsortedNums);
      assertEquals(bin.getNumSwaps(), 2);
   }

   // Testing comparisons working correctly
   @Test
   public void testComparisons() {
      BinarySearchInterface bin = new MyBinarySearch();
      bin.search(listOfNums, 3, 0, listOfNums.size() - 1);
      assertEquals(bin.getNumComparisons(), 3);
   }

   // Testing recursions working correctly
   @Test
   public void testRecursions() {
      BinarySearchInterface bin = new MyBinarySearch();
      bin.search(listOfNums, 3, 0, listOfNums.size() - 1);
      assertEquals(bin.getNumRecursions(), 2);
   }
   
   // Testing search number at random list
   @Test
   public void testSearchRandom() {
      BinarySearchInterface bin = new MyBinarySearch();
      int actual = bin.search(listOfRandomSorted, 24, 0, listOfRandomSorted.size() - 1);
      assertEquals(actual, 5);
      assertEquals(bin.getNumComparisons(), 3);
      assertEquals(bin.getNumRecursions(), 2);
   }

   // Testing search at the lower
   @Test
   public void testSearchLower() {
      BinarySearchInterface bin = new MyBinarySearch();
      int actual = bin.search(listOfNums, 2, 0, listOfNums.size() - 1);
      assertEquals(actual, 1);
      assertEquals(bin.getNumComparisons(), 2);
      assertEquals(bin.getNumRecursions(), 1);

      bin = new MyBinarySearch();
      actual = bin.search(listOfRandomSorted, 5, 0, listOfRandomSorted.size() - 1);
      assertEquals(actual, 1);
      assertEquals(bin.getNumComparisons(), 2);
      assertEquals(bin.getNumRecursions(), 1);

   }

   // Testing search the first value
   @Test
   public void testSearchFirst() {
      BinarySearchInterface bin = new MyBinarySearch();
      int actual = bin.search(listOfNums, 1, 0, listOfNums.size() - 1);
      assertEquals(actual, 0);
      assertEquals(bin.getNumComparisons(), 3);
      assertEquals(bin.getNumRecursions(), 2);

      bin = new MyBinarySearch();
      actual = bin.search(listOfRandomSorted, 3, 0, listOfRandomSorted.size() - 1);
      assertEquals(actual, 0);
      assertEquals(bin.getNumComparisons(), 3);
      assertEquals(bin.getNumRecursions(), 2);

   }

   // Testing the last value
   @Test
   public void testSearchLast() {
      BinarySearchInterface bin = new MyBinarySearch();
      int actual = bin.search(listOfNums, 10, 0, listOfNums.size() - 1);
      assertEquals(actual, 9);
      assertEquals(bin.getNumComparisons(), 4);
      assertEquals(bin.getNumRecursions(), 3);

      bin = new MyBinarySearch();
      actual = bin.search(listOfRandomSorted, 72, 0, listOfRandomSorted.size() - 1);
      assertEquals(actual, 8);
      assertEquals(bin.getNumComparisons(), 4);
      assertEquals(bin.getNumRecursions(), 3);
   }

   // Testing the middle value
   @Test
   public void testSearchMiddle() {
      BinarySearchInterface bin = new MyBinarySearch();
      int actual = bin.search(listOfRandomSorted, 18, 0, listOfRandomSorted.size() - 1);
      assertEquals(actual, 4);
      assertEquals(bin.getNumComparisons(), 1);
      assertEquals(bin.getNumRecursions(), 0);
   }

   // Testing an empty list
   @Test
   public void testEmptyList() {
      BinarySearchInterface bin = new MyBinarySearch();
      int actual = bin.search(emptyList, 21, 0, emptyList.size() - 1);
      assertEquals(actual, -1);
      assertEquals(bin.getNumComparisons(), 0);
      assertEquals(bin.getNumRecursions(), 0);
   }

   // Testing one value list
   @Test
   public void testOneList() {
      BinarySearchInterface bin = new MyBinarySearch();
      int actual = bin.search(oneList, 1, 0, oneList.size() - 1);
      assertEquals(actual, 0);
      assertEquals(bin.getNumComparisons(), 1);
      assertEquals(bin.getNumRecursions(), 0);
   }

   // Testing a missing list
   @Test
   public void testMissingList() {
      BinarySearchInterface bin = new MyBinarySearch();
      int actual = bin.search(listOfRandomSorted, 75, 0, listOfRandomSorted.size() - 1);
      assertEquals(actual, -1);
      assertEquals(bin.getNumComparisons(), 4);
      assertEquals(bin.getNumRecursions(), 3);
   }

   // Testing a repeated value in a list
   @Test
   public void testRepeatList() {
      BinarySearchInterface bin = new MyBinarySearch();
      int actual = bin.search(listWithRepeat, 2, 0, listWithRepeat.size() - 1);
      assertEquals(actual, 1);
      assertEquals(bin.getNumComparisons(), 2);
      assertEquals(bin.getNumRecursions(), 1);
   }
   

}
