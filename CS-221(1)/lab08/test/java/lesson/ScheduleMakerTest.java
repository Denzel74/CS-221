package lesson;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


/**
 * Tests ScheduleMaker class
 *
 * DO NOT MODIFY THIS FILE
 * @author krodgers
 */


public class ScheduleMakerTest {
    // All test data is parallel data, e.g. the item at element x
    // refers to the same test data in each test data ArrayList
    
    // Strings in <courseNo> <credit> <dept> format
    private ArrayList<String> testStringData;
    private ArrayList<CreditCourse> testCourseData;
    // Expected strings from getScheduleStr
    private ArrayList<String> expectedPrintString;

    private final String PRINT_HEADER = "Full Schedule\n-------------\n";
    
    // Runs before each test in case the data got corrupted
    @BeforeTest
    private void generateData(){
	// initialize/clear data structures
        testStringData = new ArrayList<String>();
        testCourseData = new ArrayList<CreditCourse>();
        expectedPrintString = new ArrayList<String>();
        
        String[] depts = {"A", "B", "C"};
        int credits = 3;

	// generate data
        for(int i = 1; i <= 10; i++){
            int courseNo = i + i * 100;
            String dept = depts[i % 3];
            testStringData.add(courseNo + " " + credits + " " + dept);
            testCourseData.add(new CreditCourse(dept, courseNo, credits));
            expectedPrintString.add(dept + " " + courseNo);
        }
    }


    /**
     * Determines if first contains the same elements as second in the same order
     * 
     * @param first - list to compare
     * @param second - list to compare
     * @return true if a.equals(b) for every pair of elements in first, second; false otherwise
     */ 
    private <T extends Comparable<T>> boolean deepEquals(List<T> first, List<T> second){
        if(first.size() != second.size())
            return false;

        Iterator<T> firstIter = first.iterator();
        Iterator<T> secondIter = second.iterator();

        while(firstIter.hasNext() && secondIter.hasNext()) {
            T elem = firstIter.next();
            T item = secondIter.next();
            if(elem.compareTo(item) != 0)
                return false;
        }
        return true;
    }
    
    
    /**
       Test parseSchedule
       Test Cases:
       - parseSchedule returns empty ArrayList when iterator has nothing 
       - parseSchedule returns empty ArrayList when iterator is already at the end
       - parseSchedule does not throw an exception when iterator is already at the end
       - parseSchedule returns ArrayList with one CreditCourse object when iterator has one element
       - parseSchedule returns ArrayList with multiple CreditCourse objects when iterator has multiple elements
       - parseSchedule returns ArrayList with remaining CreditCourse objects when iterator starts in the middle 
       - parseSchedule returns ArrayList with CreditCourse objects it was able to parse when some course data has incorrect datatypes [should test courseNo and credits being incorrect separately]
       - [should test this, but we'll ignore it for now] parseSchedule returns ArrayList with CreditCourse objects it was able to parse when some course data is missing data
       
    */

    
    //       - parseSchedule returns empty ArrayList when iterator has nothing 
    @Test
    public void testParseScheduleEmptyIterator(){
	ArrayList<String> testData = new ArrayList<String>();
	Iterator<String> iter = testData.iterator();
	ArrayList<CreditCourse> actual = ScheduleMaker.parseSchedule(iter);
	ArrayList<CreditCourse> expected = new ArrayList<CreditCourse>();

	assertEquals(actual, expected);

    }

    // - parseSchedule returns empty ArrayList when iterator is already at the end
    // - parseSchedule does not throw an exception when iterator is already at the end
    @Test
    public void testParseScheduleUsedIterator(){
	ArrayList<String> testData = new ArrayList<String>(testStringData);

	// use up the iterator
	Iterator<String> iter = testData.iterator();
	while(iter.hasNext())
	    iter.next();

	
	ArrayList<CreditCourse> actual = ScheduleMaker.parseSchedule(iter);
	ArrayList<CreditCourse> expected = new ArrayList<CreditCourse>();

	assertEquals(actual, expected);

    }

    // - parseSchedule returns ArrayList with one CreditCourse object when iterator has one element
    @Test
    public void testParseScheduleIteratorSingleElem(){
        List<String> testData = testStringData.subList(0, 1);
        Iterator<String> iter = testData.iterator();
        
        List<CreditCourse> actual = ScheduleMaker.parseSchedule(iter);
        List<CreditCourse> expected = testCourseData.subList(0, 1);

	assertTrue(deepEquals(actual, expected));
    }

    // - parseSchedule returns ArrayList with multiple CreditCourse objects when iterator has multiple elements
    @Test
    public void testParseScheduleIteratorMultElem(){
        List<String> testData = testStringData.subList(0, 5);
        Iterator<String> iter = testData.iterator();
        
        List<CreditCourse> actual = ScheduleMaker.parseSchedule(iter);
        List<CreditCourse> expected = testCourseData.subList(0, 5);

	assertTrue(deepEquals(actual, expected));
    }

    // - parseSchedule returns ArrayList with remaining CreditCourse objects when iterator starts in the middle 
    @Test
    public void testParseScheduleIteratorStartsMid(){
        // testData = testStringData
        Iterator<String> iter = testStringData.listIterator(3);

        
        List<CreditCourse> actual = ScheduleMaker.parseSchedule(iter);
        List<CreditCourse> expected = testCourseData.subList(3, testCourseData.size());

	assertTrue(deepEquals(actual, expected));
    }

    // - parseSchedule returns ArrayList with CreditCourse objects it was able to parse when some course data has incorrect datatypes
    @Test
    public void testParseScheduleNonNumericData(){
        List<String> testData = testStringData.subList(0, 3);

        // corrupt first data
        testData.set(0, "not legit number");
        Iterator<String> iter = testData.listIterator();

        
        List<CreditCourse> actual = ScheduleMaker.parseSchedule(iter);
        List<CreditCourse> expected = testCourseData.subList(1, 3);

	assertTrue(deepEquals(actual, expected));
    }


    /**
       Test getScheduleStr
       Test Cases:
       - getScheduleStr returns only header when iterator has nothing 
       - getScheduleStr returns only header when iterator is already at the end
       - getScheduleStr does not throw an exception when iterator is already at the end
       - getScheduleStr returns header plus one course's data when iterator has one element
       - getScheduleStr returns header plus all course's data when iterator has multiple elements
       - getScheduleStr returns header plus remaining courses' data when iterator starts in the middle 
    */


    //    - getScheduleStr returns only header when iterator has nothing 
    @Test
    public void testScheduleStrEmptyIterator(){
	ArrayList<CreditCourse> testData = new ArrayList<CreditCourse>();
	Iterator<CreditCourse> iter = testData.iterator();

	String actual = ScheduleMaker.getScheduleStr(iter);
	String expected = PRINT_HEADER;

	assertEquals(actual, expected);
    }

    //    - getScheduleStr returns only header when iterator is already at the end
    //    - getScheduleStr does not throw an exception when iterator is already at the end
    @Test
    public void testScheduleStrUsedIterator(){
	ArrayList<CreditCourse> testData = new ArrayList<CreditCourse>();
	Iterator<CreditCourse> iter = testData.iterator();

	// use up the iterator
	while(iter.hasNext())
	    iter.next();
	       
	String actual = ScheduleMaker.getScheduleStr(iter);
	String expected = PRINT_HEADER;

	assertEquals(actual, expected);
    }
    
    //    - getScheduleStr returns header plus one course's data when iterator has one element
    @Test
    public void testScheduleStrOneElem(){
	ArrayList<CreditCourse> testData = new ArrayList<CreditCourse>();
	Iterator<CreditCourse> iter = testData.iterator();

	// use up the iterator
	while(iter.hasNext())
	    iter.next();
	       
	String actual = ScheduleMaker.getScheduleStr(iter);
	String expected = PRINT_HEADER;

	assertEquals(actual, expected);
    }

    //    - getScheduleStr returns header plus all course's data when iterator has multiple elements
    @Test
    public void testScheduleStrMultElem(){
	//testData is testCourseData
	Iterator<CreditCourse> iter = testCourseData.iterator();
	
	String expected = PRINT_HEADER;
	for(String course : expectedPrintString)
	    expected += course + "\n";
	
	String actual = ScheduleMaker.getScheduleStr(iter);
		
	assertEquals(actual, expected);
    }

    //    - getScheduleStr returns header plus remaining courses' data when iterator starts in the middle 
    @Test
    public void testScheduleStrIterStartMiddle(){
	//testData is testCourseData
	Iterator<CreditCourse> iter = testCourseData.listIterator(4);
	
	String expected = PRINT_HEADER;
	Iterator<String> strIter = expectedPrintString.listIterator(4);
	while(strIter.hasNext())
	    expected += strIter.next() + "\n";
	
	String actual = ScheduleMaker.getScheduleStr(iter);
		
	assertEquals(actual, expected);
    }

}
