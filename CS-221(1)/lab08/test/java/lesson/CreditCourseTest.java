package lesson;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Tests CreditCourse Object
 * 
 * @author krodgers
 */


public class CreditCourseTest {

    // Constant test values
    private final String DEPT_A = "ABC";
    private final String DEPT_B = "BCD";
    private final int COURSE_A_NUM = 100;
    private final int COURSE_B_NUM = 200;
    private final int THREE = 3;
    private final int FOUR = 4;
    private final int NEGATIVE = -3;
    


    /**
       Tests all CreditCourse getters
       @param course - course object on which to call getter methods
       @param expectedDept - expected value for course department
       @param expectedNo - expected value for course number
       @param expectedCredit - expected value for course credits
     */
    private void testGetters(CreditCourse course, String expectedDept, int expectedNo, int expectedCredit){
	assertEquals(course.getDepartment(), expectedDept);
	assertEquals(course.getNumber(), expectedNo);
	assertEquals(course.getCredits(), expectedCredit);
    }

    // Verify dept, number constructor and getters
    @Test
    public void testTwoParamConstructor(){
	CreditCourse course = new CreditCourse(DEPT_A, COURSE_A_NUM);
	testGetters(course, DEPT_A, COURSE_A_NUM, 0);
    }

    // Verify dept, number, credits constructor and getter
    @Test
    public void testThreeParamConstructor(){
	CreditCourse course = new CreditCourse(DEPT_B, COURSE_B_NUM, THREE);
	testGetters(course, DEPT_B, COURSE_B_NUM, THREE);
    }

    // Test get/set Department
    @Test
    public void testSetDepartment(){
	CreditCourse course = new CreditCourse(DEPT_A, COURSE_A_NUM);
	course.setDepartment(DEPT_B);
	testGetters(course, DEPT_B, COURSE_A_NUM, 0);
    }

    // Test get/set number
    @Test
    public void testSetCourseNumber(){
	CreditCourse course = new CreditCourse(DEPT_A, COURSE_A_NUM, FOUR);
	course.setNumber(COURSE_B_NUM);
	testGetters(course, DEPT_A, COURSE_B_NUM, FOUR);
    }

    // Test get/set number with invalid integer
    @Test
    public void testSetCourseNumberInvalidValue(){
	CreditCourse course = new CreditCourse(DEPT_A, COURSE_B_NUM, FOUR);
	course.setNumber(NEGATIVE);
	testGetters(course, DEPT_A, COURSE_B_NUM, FOUR);
    }


    // Test get/set credits with valid integer
    @Test
    public void testSetCourseCredits(){
	CreditCourse course = new CreditCourse(DEPT_B, COURSE_B_NUM, THREE);
	course.setCredits(FOUR);
	testGetters(course, DEPT_B, COURSE_B_NUM, FOUR);
    }


    // Test get/set credits with invalid integer
    @Test
    public void testSetCourseCreditsInvalid(){
	CreditCourse course = new CreditCourse(DEPT_B, COURSE_B_NUM, THREE);
	course.setCredits(NEGATIVE);
	testGetters(course, DEPT_B, COURSE_B_NUM, THREE);
    }



    // Test compareTo,equals - dept eq, courseNo equal, credits differ
    @Test
    public void testRelationalSameDeptSameNumberDiffCredits(){
	CreditCourse courseA = new CreditCourse(DEPT_A, COURSE_A_NUM, THREE);
	CreditCourse courseB = new CreditCourse(DEPT_A, COURSE_A_NUM, FOUR);

	assertTrue(courseA.compareTo(courseB) == 0);
	assertTrue(courseB.compareTo(courseA) == 0);
	assertTrue(courseA.equals(courseB));
	assertTrue(courseB.equals(courseA));
	assertTrue(courseA.equals(courseA));
    }
    
    // Test compareTo,equals - dept eq, courseNo not eq, credits eq
    @Test
    public void testRelationalSameDeptDiffNumberSameCredits(){
	CreditCourse courseA = new CreditCourse(DEPT_A, COURSE_A_NUM, THREE);
	CreditCourse courseB = new CreditCourse(DEPT_A, COURSE_B_NUM, THREE);

	assertTrue(courseA.compareTo(courseB) < 0);
	assertTrue(courseB.compareTo(courseA) > 0);
	
	assertFalse(courseA.equals(courseB));
	assertFalse(courseB.equals(courseA));	
    }


    // Test compareTo,equals - dept not eq, courseNo eq, credits eq
    @Test
    public void testRelationalDiffDeptSameNumberSameCredits(){
	CreditCourse courseA = new CreditCourse(DEPT_B, COURSE_B_NUM, THREE);
	CreditCourse courseB = new CreditCourse(DEPT_A, COURSE_B_NUM, THREE);

	assertTrue(courseA.compareTo(courseB) > 0);
	assertTrue(courseB.compareTo(courseA) < 0);
	
	assertFalse(courseA.equals(courseB));
	assertFalse(courseB.equals(courseA));
    }


    // Test toString
    @Test
    public void testToString(){
	CreditCourse courseA = new CreditCourse(DEPT_A, COURSE_A_NUM, THREE);
	CreditCourse courseB = new CreditCourse(DEPT_B, COURSE_B_NUM);

	String expectedA = String.format("(%d)%s %s",THREE, DEPT_A, COURSE_A_NUM);
	String expectedB = String.format("(0)%s %s", DEPT_B, COURSE_B_NUM);

	assertEquals(courseA.toString(), expectedA);
	assertEquals(courseB.toString(), expectedB);
    }



}
