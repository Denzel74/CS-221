package lesson;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.*;

import java.lang.IllegalArgumentException;
import java.lang.reflect.Executable;
import java.util.NoSuchElementException;

/**
 * Tests TheList Objects
 * 
 * @author krodgers, Denzel Balbosa
 */


public class TheListTest {

    // Determine type of list being used
    private final String LIST_TYPE = "ArrayList";
    // private final String LIST_TYPE = "SinglyLinkedList";

    // List values
    private final Integer ELEM_A = 1;
    private final Integer ELEM_B = 2;
    private final Integer ELEM_C = 3;
    private final Integer ELEM_D = 4;
    private final Integer INVALID_ELEM = -3;
    

    // Define more arrays as needed
    private final Integer[] EMPTY = new Integer[0];
    private final Integer[] A = new Integer[]{ELEM_A}; // [A]
    private final Integer[] B = new Integer[]{ELEM_B};
    private final Integer[] C = new Integer[]{ELEM_C};
    private final Integer[] D = new Integer[]{ELEM_D};

    private final Integer[] AB = new Integer[]{ELEM_A, ELEM_B}; // [A, B]
    private final Integer[] ABC = new Integer[]{ELEM_A, ELEM_B, ELEM_C};
    private final Integer[] ACD = new Integer[]{ELEM_A, ELEM_C, ELEM_D};
    private final Integer[] DBA = new Integer[]{ELEM_D, ELEM_B, ELEM_A};
    private final Integer[] BA = new Integer[]{ELEM_B, ELEM_A};
    private final Integer[] BACD = new Integer[]{ELEM_B, ELEM_A, ELEM_C, ELEM_D};
    private final Integer[] BD = new Integer[]{ELEM_B, ELEM_D};
    private final Integer[] BCD = new Integer[]{ELEM_B, ELEM_C, ELEM_D};

    private final Integer[] DA = new Integer[]{ELEM_D, ELEM_A};
    private final Integer[] DAB = new Integer[]{ELEM_D, ELEM_A, ELEM_B};
    private final Integer[] DABC = new Integer[]{ELEM_D, ELEM_A, ELEM_B, ELEM_C};

    private final Integer[] AD = new Integer[]{ELEM_A, ELEM_D};
    private final Integer[] ABD = new Integer[]{ELEM_A, ELEM_B, ELEM_D};
    private final Integer[] ABCD = new Integer[]{ELEM_A, ELEM_B, ELEM_C, ELEM_D};


    

    /**
     * Returns TheList object containing the elements contained in elems.
     *
     * This method depends on add(elem) working correctly.
     *
     * @param elems - the elements to add to the list
     *                should be one of the array constants defined above
     *
     * @return a TheList object as determined by LIST_TYPE containing the
     *           elements in elems in the same order
     */
    private TheList<Integer> getList(Integer[] elems){
        TheList<Integer> list = null;
        switch(LIST_TYPE){
        case "ArrayList":
            list = new TheArrayList();
            break;
        case "SinglyLinkedList":
            // list = new TheSinglyLinkedList();
            break;
        default:
            throw new IllegalArgumentException("List type must be ArrayList or SinglyLinkedList");
        }
        
        for(Integer i : elems)
            list.add(i);

        return list;	
    }


    /**
     * Verifies actual and expected are the same size and contain the same
     * elements in the same order.
     *
     * Depends on size() and get() working correctly to accurately determine
     * if the lists are equal
     *
     * @param 
     * @return true iff actual and expected have the same elements in the same order
     *
     */
    private boolean areEqual(TheList<Integer> actual, Integer[] expected){
        if(actual.size() != expected.length)
            return false;

        int size = expected.length;
        for(int i = 0; i < size; i++){
            if(!actual.get(i).equals(expected[i]))
            return false;
        }
        return true;	    
    }





    //////////////////////////////////////////////
    // Data Provider that simply provides lists //
    // Can be used in all invalid tests and some//
    // utility tests                            //
    //////////////////////////////////////////////
	
    /**
       Creates an empty list, 1 element list, 2 element list, and 3
       element list
    */
    @DataProvider(name = "sizedLists")
    public Object[][] getLists() {
	// Creates a 2D array - an array of arrays
	// the first object is the starting list,
	// the second object is the expected list
	return new Object[][] {{getList(EMPTY)},// test data 1
			       {getList(A)}, //test data 2
			       {getList(AB)}, // test data 3
			       {getList(ABC)}, // test data 4
	};
    }


    

    ///////////////////
    // Test addFront //
    ///////////////////
    /**
       Creates lists and expected values for addToFrontTest
    */
    @DataProvider(name = "addFrontProvider")
    public Object[][] getAddFrontList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(EMPTY), D}, // test data 1 [] add D to Front [D]
                    {getList(A), DA}, // test data 2  [A] add D to Front [D, A]
                    {getList(AB), DAB}, // test data 3 [A, B] add D front [D, A, B]
                    {getList(ABC),DABC} // test data 4 [A, B, C] add D front [D , A, B, C]
        };

    }
	
    /** 
	Test addFront

	This method is called once for every set of data
	contained in the array generated from 'addFrontProvider'.
    */ 
    @Test(dataProvider = "addFrontProvider")
    public void testAddFront(TheList<Integer> startList, Integer[] expected){
        // call addFront on startList
        startList.addFront(ELEM_D);
        // check that startList is now the same as the expected list
        assertTrue(areEqual(startList, expected));
    }



    
    //////////////////////////
    // Test addBack and add //
    //////////////////////////
    
    /**
       Creates lists and expected values for TestAddBack and TestAdd
    */
    @DataProvider(name = "addBackProvider")
    public Object[][] getAddBackList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(EMPTY), D}, // test data 1
                    {getList(A), AD}, // test data 2 [A] add or addBack D [A, D]
                    {getList(AB), ABD}, // test data 3
                    {getList(ABC), ABCD} // test data 4
        };
    }
	
    /** 
	Test addBack

	This method is called once for every set of data
	contained in the array generated from 'addBackProvider'.
    */ 
    @Test(dataProvider = "addBackProvider")
    public void testAddBack(TheList<Integer> startList, Integer[] expected){
        // call addBack on startList
        startList.addBack(ELEM_D);
        // check that startList is now the same as the expected list
        assertTrue(areEqual(startList, expected));
    }

    /** 
	Test add
	
	This method is called once for every set of data
	contained in the array generated from 'addBackProvider'.

	Since the expected values for both addBack and add are the
	same, use the same dataProvider
    */ 
    @Test(dataProvider = "addBackProvider")
    public void testAdd(TheList<Integer> startList, Integer[] expected){
	// call addBack on startList
	startList.add(ELEM_D);
	// check that startList is now the same as the expected list
	assertTrue(areEqual(startList, expected));
    }


    ///////////////////
    // Test addAfter //
    ///////////////////

    /**
       Creates lists and expected values for TestAddAfter
    */
    @DataProvider(name = "addAfterProvider")
    public Object[][] getAddAfterList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(A), AB}, // test data 1
                    {getList(DA), DAB}, // test data 2 
                    {getList(ACD), ABCD} // test data 3
        };
    }
    
    /** 
	Test addAfter

	This method is called once for every set of data
	contained in the array generated from 'addAfterProvider'.
    */ 
    @Test(dataProvider = "addAfterProvider")
    public void testAddAfter(TheList<Integer> startList, Integer[] expected){
        // Add the element B after element A
        startList.addAfter(ELEM_B, ELEM_A);
        // Check that startlist is now the same as the expected list
        assertTrue(areEqual(startList, expected));
    }

    ///////////////////////////
    // Test addBefore	     //
    ///////////////////////////

    /**
       Creates lists and expected values for TestAddBefore
    */
    @DataProvider(name = "addBeforeProvider")
    public Object[][] getAddBeforeList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(A), BA}, // test data 1
                    {getList(DA), DBA},// test data 2 
                    {getList(ACD), BACD} // test data 3
                    
        };
    }

    /** 
	Test addBefore

	This method is called once for every set of data
	contained in the array generated from 'addBeforeProvider'.
    */ 
    @Test(dataProvider = "addBeforeProvider")
    public void testAddBefore(TheList<Integer> startList, Integer[] expected){
        // Add the element B before element A
        startList.addBefore(ELEM_B, ELEM_A);
        // Check that startlist is now the same as the expected list
        assertTrue(areEqual(startList, expected));
    }

    //////////////////////////////
    // Test add at index	    //
    //////////////////////////////
    
    /**
       Creates lists and expected values for TestAtIndex
    */
    @DataProvider(name = "addAtIndexProvider")
    public Object[][] getAddAtIndexList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(EMPTY), A}, // test data 1
                    {getList(B), AB}, // test data 2 
                    {getList(BD), ABD}, // test data 3
                    {getList(BCD), ABCD} // test data 4
        };
    }
    
    /** 
	Test addAtIndex

	This method is called once for every set of data
	contained in the array generated from 'addIndexProvider'.
    */ 
    @Test(dataProvider = "addAtIndexProvider")
    public void testAddIndex(TheList<Integer> startList, Integer[] expected){
        // Add the element A at index 0
        startList.add(0, ELEM_A);
        // Check that startlist is now the same as the expected list
        assertTrue(areEqual(startList, expected));
    }

    /////////////////////////////
    // Test removeFront	   //
    /////////////////////////////

    /**
       Creates lists and expected values for TestRemoveFront
    */
    @DataProvider(name = "removeFrontProvider")
    public Object[][] getRemoveFrontList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(A), EMPTY}, // test data 1
                    {getList(DA), A},// test data 2 
                    {getList(DAB), AB} // test data 3
                    
        };
    }

    /** 
	Test removeFront
	
	This method is called once for every set of data
	contained in the array generated from 'removeFrontProvider'.
    */ 
    @Test(dataProvider = "removeFrontProvider")
    public void testRemoveFront(TheList<Integer> startList, Integer[] expected){
	// call removeFront on startList
	startList.removeFront();
	// check that startList is now the same as the expected list
	assertTrue(areEqual(startList, expected));
    }


    ////////////////////////////
    // Test removeBack	      //
    ////////////////////////////

    /**
       Creates lists and expected values for TestRemoveBack
    */
    @DataProvider(name = "removeBackProvider")
    public Object[][] getRemoveBackList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(A), EMPTY}, // test data 1
                    {getList(DA), D},// test data 2 
                    {getList(DAB), DA} // test data 3
                    
        };
    }

    /** 
	Test removeBack
	
	This method is called once for every set of data
	contained in the array generated from 'removeBackProvider'.
    */ 
    @Test(dataProvider = "removeBackProvider")
    public void testRemoveBack(TheList<Integer> startList, Integer[] expected){
	// call removeBack on startList
	startList.removeBack();
	// check that startList is now the same as the expected list
	assertTrue(areEqual(startList, expected));
    }

    /////////////////////////////////////////
    // Test remove specific element	       //
    /////////////////////////////////////////

    /**
       Creates lists and expected values for TestRemoveElement
    */
    @DataProvider(name = "removeElementProvider")
    public Object[][] getRemoveElementList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(A), EMPTY}, // test data 1
                    {getList(DA), D},// test data 2 
                    {getList(ABD), BD} // test data 3
                    
        };
    }

    /** 
	Test removeElement
	
	This method is called once for every set of data
	contained in the array generated from 'removeElementProvider'.
    */ 
    @Test(dataProvider = "removeElementProvider")
    public void testRemoveElement(TheList<Integer> startList, Integer[] expected){
	// call removeElement on startList
	startList.remove(ELEM_A);
	// check that startList is now the same as the expected list
	assertTrue(areEqual(startList, expected));
    }

    ///////////////////////////////////////
    // Test remove specific index	     //
    ///////////////////////////////////////

    /**
       Creates lists and expected values for TestRemoveIndex
    */
    @DataProvider(name = "removeIndexProvider")
    public Object[][] getRemoveIndexList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(A), EMPTY}, // test data 1
                    {getList(DA), A},// test data 2 
                    {getList(ABD), BD} // test data 3
                    
        };
    }

    /** 
	Test removeIndex
	
	This method is called once for every set of data
	contained in the array generated from 'removeIndexProvider'.
    */ 
    @Test(dataProvider = "removeIndexProvider")
    public void testRemoveIndex(TheList<Integer> startList, Integer[] expected){
	// call removeIndex on startList
	startList.remove(0);
	// check that startList is now the same as the expected list
	assertTrue(areEqual(startList, expected));
    }

    ////////////////////////
    // Test update	      //
    ////////////////////////

    /**
       Creates lists and expected values for TestUpdate
    */
    @DataProvider(name = "updateProvider")
    public Object[][] getUpdateList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(B), A}, // test data 1
                    {getList(BD), AD},// test data 2 
                    {getList(BCD), ACD} // test data 3
                    
        };
    }

    /** 
	Test update
	
	This method is called once for every set of data
	contained in the array generated from 'updateProvider'.
    */ 
    @Test(dataProvider = "updateProvider")
    public void testUpdate(TheList<Integer> startList, Integer[] expected){
	// call update on startList
	startList.update(0, ELEM_A);
	// check that startList is now the same as the expected list
	assertTrue(areEqual(startList, expected));
    }

    //////////////////////
    // Test find	    //
    //////////////////////

    /**
       Creates lists and expected values for TestFind
    */
    @DataProvider(name = "findIndexProvider")
    public Object[][] getFindIndexList() {
        // Creates a 2D array - an array of arrays
        // the first object is the starting list,
        // the second object is the expected list
        return new Object[][] {{getList(B), -1}, // test data 1
                    {getList(AD), 0},// test data 2 
                    {getList(DAB), 1} // test data 3
                    
        };
    }

    /** 
	Test find
	
	This method is called once for every set of data
	contained in the array generated from 'findIndexProvider'.
    */ 
    @Test(dataProvider = "findIndexProvider")
    public void testFindIndex(TheList<Integer> startList, int expectedIndex){
	// call find on startList
	int actual = startList.find(ELEM_A);
	// check that startList is now the same as the expected list
	assertEquals(actual, expectedIndex);
    }


    ///////////////////////
    // Test first	     //
    ///////////////////////
    
    /**
       Creates lists and expected values for first
    */
    @DataProvider(name = "singleElementProvider")
    public Object[][] getElementList() {
        // Only testing valid data -- the invalid call is tested separately
        return new Object[][] {{getList(A), ELEM_A}, // test data 1
                    {getList(AB), ELEM_A}, // test data 2
                    {getList(ABC), ELEM_A} // test data 3
        };
    }
	
    /** 
	Test first

	This method is called once for every set of data
	contained in the array generated from 'firstProvider'.
    */ 
    @Test(dataProvider = "singleElementProvider")
    public void testFirst(TheList<Integer> startList, Integer expectedElement){
        // call first on startList
        Integer actual = startList.first();
        // check that startList is now the same as the expected list
        assertEquals(actual, expectedElement);
    }

    //////////////////////
    // Test last	    //
    //////////////////////

    /**
       Creates lists and expected values for last
    */
    @DataProvider(name = "singleLastElementProvider")
    public Object[][] getLastElementList() {
        // Only testing valid data -- the invalid call is tested separately
        return new Object[][] {{getList(A), ELEM_A}, // test data 1
                    {getList(AB), ELEM_B}, // test data 2
                    {getList(ABC), ELEM_C} // test data 3
        };
    }
	
    /** 
	Test last

	This method is called once for every set of data
	contained in the array generated from 'lastProvider'.
    */ 
    @Test(dataProvider = "singleLastElementProvider")
    public void testLast(TheList<Integer> startList, Integer expectedElement){
        // call last on startList
        Integer actual = startList.last();
        // check that startList is now the same as the expected list
        assertEquals(actual, expectedElement);
    }

    ///////////////////////
    // Test clear	     //
    ///////////////////////
    /** 
	Test clear
	
	This method is called once for every set of data
	contained in the array generated from 'sizedLists'.
    */ 
    @Test(dataProvider = "sizedLists")
    public void testClear(TheList<Integer> startList){
	// call clear on startList
	startList.clear();

	// check that startList is now empty
	assertEquals(startList, getList(EMPTY));
    }

    
    //////////////////////
    // Test size	    //
    //////////////////////

    /**
       Creates lists and expected values for size
    */
    @DataProvider(name = "sizeProvider")
    public Object[][] getSizeList() {
        // Only testing valid data -- the invalid call is tested separately
        return new Object[][] {{getList(EMPTY), 0},
                    {getList(A), 1}, // test data 1
                    {getList(AB), 2}, // test data 2
                    {getList(ABC), 3} // test data 3
        };
    }
	
    /** 
	Test size

	This method is called once for every set of data
	contained in the array generated from 'sizeProvider'.
    */ 
    @Test(dataProvider = "sizeProvider")
    public void testSize(TheList<Integer> startList, int expected){
        // call size on startList
        int actual = startList.size();
        // check that startList is now the same as the expected list
        assertEquals(actual, expected);
    }

    //////////////////////////
    // Test toString        //
    //////////////////////////
	 /**
       Creates lists and expected values for toString
    */
    @DataProvider(name = "toStringProvider")
    public Object[][] getStringList() {
        // Only testing valid data -- the invalid call is tested separately
        return new Object[][] {
            {getList(EMPTY), "[ ]"}, // [] toString "[ ]"
            {getList(A), "[A, ]"}, // [A] toString "[A, ]"
            {getList(AB), "[A, B, ]"},
            {getList(ABC), "[A, B, C]"}
        };
        
    }
	
    /** 
	Test toString

	Verifies that the correct string is returned from toString
    */ 
    @Test(dataProvider = "toStringProvider")
    public void testToString(TheList<Integer> startList, String expectedElement){
        // call toString on startList
        String result = startList.toString();
        
        // verify the return String matches the expected
        assertEquals(result, expectedElement);
    }





    
    //////////////////////////////////////////////////////////////////////
    // Tests invalid method calls that result in NoSuchElementException //
    //////////////////////////////////////////////////////////////////////
    
    /**
       Test invalid addAfter calls
       Expects all calls to throw NoSuchElementException
    */
    @Test(dataProvider = "sizedLists",
	  expectedExceptions = NoSuchElementException.class)
	public void testInvalidAddAfter(TheList<Integer> startList){
        // call addAfter on startList 
        startList.addAfter(ELEM_D, INVALID_ELEM);
    }

    /**
       Test invalid addBefore calls
       Expects all calls to throw NoSuchElementException
    */
    @Test(dataProvider = "sizedLists",
	  expectedExceptions = NoSuchElementException.class)
	public void testInvalidAddBefore(TheList<Integer> startList){
        // call addBefore on startList 
        startList.addBefore(ELEM_D, INVALID_ELEM);
    }

    /**
       Test invalid add calls
       Expects all calls to throw IndexOutOfBoundsException
    */
    @Test(dataProvider = "sizedLists",
	  expectedExceptions = IndexOutOfBoundsException.class)
	public void testInvalidAddElement(TheList<Integer> startList){
        // call add on startList at a specific index
        startList.add(4, ELEM_C);
    }

    //////////////////////////////////////////////
    // DataProvider that provides an Empty list //
    // that can be used for invalid test for    //
    // empty list                               //
    //////////////////////////////////////////////

    @DataProvider(name = "emptyList")
    public Object[][] getEmptyLists() {
	// Creates a 2D array - an array of arrays
	// the first object is the starting list,
	// the second object is the expected list
	return new Object[][] {{getList(EMPTY)}};
    }
    
    /**
       Test invalid removeFront calls with an empty list
       Expects all calls to throw NoSuchElementException
    */
    @Test(dataProvider = "emptyList",
	  expectedExceptions = NoSuchElementException.class)
	public void testInvalidRemoveFrontList(TheList<Integer> startList){
        // call removeFront on startList
        startList.removeFront();
    }

    /**
       Test invalid removeBack calls with an empty list
       Expects all calls to throw NoSuchElementException
    */
    @Test(dataProvider = "emptyList",
	  expectedExceptions = NoSuchElementException.class)
	public void testInvalidRemoveBackList(TheList<Integer> startList){
        // call removeBack on startList
        startList.removeBack();
    }

    /**
       Test invalid remove element calls that does not exist
       Expects all calls to throw NoSuchElementException
    */
    @Test(dataProvider = "sizedLists",
	  expectedExceptions = NoSuchElementException.class)
	public void testInvalidRemoveElement(TheList<Integer> startList){
        // call remove on startList at a specific index
        startList.remove(ELEM_D);
    }

    /**
       Test invalid remove index calls that does not exist
       Expects all calls to throw NoSuchElementException
    */
    @Test(dataProvider = "sizedLists",
	  expectedExceptions = NoSuchElementException.class)
	public void testInvalidRemoveIndex(TheList<Integer> startList){
        // call add on startList at a specific index
        startList.remove(3);
    }

    /**
       Test invalid update calls of index that has no element
       Expects all calls to throw NoSuchElementException
    */
    @Test(dataProvider = "sizedLists",
	  expectedExceptions = NoSuchElementException.class)
	public void testInvalidUpdateIndex(TheList<Integer> startList){
        // call add on startList at a specific index
        startList.update(3, ELEM_D);
    }

    /**
       Test invalid get calls of index that does not exist
       Expects all calls to throw NoSuchElementException
    */
    @Test(dataProvider = "sizedLists",
	  expectedExceptions = NoSuchElementException.class)
	public void testInvalidGetIndex(TheList<Integer> startList){
        // call get on startList at a specific index
        startList.get(3);
    }

    /**
       Test invalid first calls for an empty list
       Expects all calls to throw NoSuchElementException
    */
    @Test(dataProvider = "emptyList",
	  expectedExceptions = NoSuchElementException.class)
	public void testInvalidFirstList(TheList<Integer> startList){
        // call removeBack on startList
        startList.first();
    }

    /**
       Test invalid last calls for an empty list
       Expects all calls to throw NoSuchElementException
    */
    @Test(dataProvider = "emptyList",
	  expectedExceptions = NoSuchElementException.class)
	public void testInvalidLastList(TheList<Integer> startList){
        // call removeBack on startList
        startList.last();
    }

}
