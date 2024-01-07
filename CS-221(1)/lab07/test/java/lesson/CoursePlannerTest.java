package lesson;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.Scanner;
import java.util.Random;
import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;


/**
 * Tests CoursePlanner class
 *
 * DO NOT MODIFY THIS FILE
 * @author krodgers
 */


public class CoursePlannerTest {
  
    /**
     * Generates a string from courses to use as input for CoursePlanner tests
     * @returns string in form "<dept> <courseNo> <credits>\n<dept> <courseNo> <credits>\n"
     */
    private String getFileString(CreditCourse[] courses){
	String courseStr = "";
	for(CreditCourse course : courses){
	    courseStr += course.getDepartment() + " " + course.getNumber();
	    courseStr += " " + course.getCredits() + "\n";
	}
	
	return courseStr;
    }

    /**
       Verifies actual, expected contain equivalent objects in the same order
       @return true if collections are equivalent, false otherwise
     */
    private boolean collectionEqual(Queue<CreditCourse> actual, Queue<CreditCourse> expected){
	while(actual.size() > 0 && expected.size() > 0){
	    if(!actual.remove().equals(expected.remove()))
		return false;
	}
	return true;

    }


    /**
       CoursePlanner
     */

    /* Test organizePrereqs
       Test cases: 
            No matching courses exist
	    File from 500 to 100 level
	    File from 100 to 500 level
    */
    @Test
    public void testOrganizePrereqsLonelyTarget(){
	CreditCourse[] courses = {new CreditCourse("a", 100),
		new CreditCourse("b", 100), new CreditCourse("c", 100),
		new CreditCourse("a", 201), new CreditCourse("a", 200), new CreditCourse("b", 200)};

	CreditCourse target = courses[2];
	Deque<CreditCourse> expected = new LinkedList<CreditCourse>();
	expected.push(target);
		
	String data = getFileString(courses);

	Scanner scan = new Scanner(data);
	Deque<CreditCourse> actual = CoursePlanner.organizePrereqs(target, scan);
	scan.close();

	assertEquals(actual.size(), expected.size());
	assertTrue(collectionEqual(actual, expected));
    }
    @Test
    public void testOrganizePrereqsDesc(){
	CreditCourse[] courses = {new CreditCourse("a", 500), new CreditCourse("b", 500),
				  new CreditCourse("a", 480), new CreditCourse("b", 480),
				  new CreditCourse("a", 461), new CreditCourse("b", 461),
				  new CreditCourse("a", 451), new CreditCourse("b", 451),
				  new CreditCourse("a", 400), new CreditCourse("b", 400)};
	
	CreditCourse target = new CreditCourse("b", 400);

	Deque<CreditCourse> expected = new LinkedList<CreditCourse>();
	expected.push(courses[1]);
	expected.push(courses[3]);
	expected.push(courses[9]);
	
	String data = getFileString(courses);

	Scanner scan = new Scanner(data);
	Deque<CreditCourse> actual = CoursePlanner.organizePrereqs(target, scan);
	scan.close();

	assertEquals(actual.size(), expected.size());
	assertTrue(collectionEqual(actual, expected));

    }
    @Test
    public void testOrganizePrereqsIncr(){
	CreditCourse[] courses = {new CreditCourse("a", 400), new CreditCourse("b", 400),
				  new CreditCourse("a", 451), new CreditCourse("b", 451),
				  new CreditCourse("a", 461), new CreditCourse("b", 461),
				  new CreditCourse("a", 480), new CreditCourse("b", 480)};

	CreditCourse target = new CreditCourse("a", 461);
				  
	Deque<CreditCourse> expected = new LinkedList<CreditCourse>();
	expected.push(courses[2]);
	expected.push(courses[4]);
	
	String data = getFileString(courses);

	Scanner scan = new Scanner(data);
	Deque<CreditCourse> actual = CoursePlanner.organizePrereqs(target, scan);
	scan.close();

	assertEquals(actual.size(), expected.size());
	assertTrue(collectionEqual(actual, expected));

    }
    
    /**
       Test chronologicalOrder
       Test cases: 
            One course file
	    Multiple courses
    */
    @Test
    public void testChronologicalOrderSingleCourse(){
	CreditCourse[] courses = {new CreditCourse("c", 400)};

	Deque<CreditCourse> expected = new LinkedList<CreditCourse>();
	expected.add(courses[0]);
		
	String data = getFileString(courses);

	Scanner scan = new Scanner(data);
	Queue<CreditCourse> actual = CoursePlanner.chronologicalOrder(scan);
	scan.close();

	assertEquals(actual.size(), expected.size());
	assertTrue(collectionEqual(actual, expected));
    }
    @Test
    public void testChronologicalOrder(){
	CreditCourse[] courses = {new CreditCourse("a", 100), new CreditCourse("b", 100),
				  new CreditCourse("c", 100), new CreditCourse("a", 201),
				  new CreditCourse("a", 200), new CreditCourse("b", 200)};

	Deque<CreditCourse> expected = new LinkedList<CreditCourse>();
	for(int i = courses.length - 1; i >= 0; i--)
	    expected.add(courses[i]);
		
	String data = getFileString(courses);

	Scanner scan = new Scanner(data);
	Queue<CreditCourse> actual = CoursePlanner.chronologicalOrder(scan);
	scan.close();

	assertEquals(actual.size(), expected.size());
	assertTrue(collectionEqual(actual, expected));
    }

    /**
       Test revChronologicalOrder
       Test cases: 
            One course file
	    Multiple courses
    */
    @Test
    public void testRevChronologicalOrderSingleCourse(){
	CreditCourse[] courses = {new CreditCourse("c", 400)};

	Queue<CreditCourse> expected = new LinkedList<CreditCourse>();
	for(CreditCourse course : courses)
	    expected.add(course);
		
	String data = getFileString(courses);

	Scanner scan = new Scanner(data);
	Queue<CreditCourse> actual = CoursePlanner.revChronologicalOrder(scan);
	scan.close();

	assertEquals(actual.size(), expected.size());
	assertTrue(collectionEqual(actual, expected));
    }
    @Test
    public void testRevChronologicalOrder(){
	CreditCourse[] courses = {new CreditCourse("a", 100), new CreditCourse("b", 100),
				  new CreditCourse("c", 100), new CreditCourse("a", 201),
				  new CreditCourse("a", 200), new CreditCourse("b", 200)};

	Queue<CreditCourse> expected = new LinkedList<CreditCourse>();
	for(CreditCourse course : courses)
	    expected.add(course);
		
	String data = getFileString(courses);

	Scanner scan = new Scanner(data);
	Queue<CreditCourse> actual = CoursePlanner.revChronologicalOrder(scan);
	scan.close();

	assertEquals(actual.size(), expected.size());
	assertTrue(collectionEqual(actual, expected));
    }

}
