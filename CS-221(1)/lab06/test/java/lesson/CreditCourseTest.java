package lesson;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Tests CreditCourse Object
 * Write at least one test method per class method
 * @author Denzel Balbosa
 */


public class CreditCourseTest {

    // Testing getters with 2 param contructor
    @Test
    public void testCreditCourseGetters(){
        CreditCourse userCredit = new CreditCourse("department", 1);
	    assertEquals(userCredit.getDepartment(), "department");
        assertEquals(userCredit.getNumber(), 1);
    }

    // Testing getters with 3 param contructor
    @Test
    public void testCreditCourseGetters2(){
        CreditCourse userCredit = new CreditCourse("department2", 2, 2);
	    assertEquals(userCredit.getDepartment(), "department2");
        assertEquals(userCredit.getNumber(), 2);
        assertEquals(userCredit.getCredits(), 2);
    }

    // Testing setters then calling getters
    @Test
    public void testCreditCourseGetAndSet() {
        CreditCourse userCredit = new CreditCourse("department", 5, 3);
        assertEquals(userCredit.getDepartment(), "department");
        assertEquals(userCredit.getNumber(), 5);
        assertEquals(userCredit.getCredits(), 3);
        userCredit.setDepartment("Produce");
        userCredit.setNumber(23);
        userCredit.setCredits(2);
        assertEquals(userCredit.getDepartment(), "Produce");
        assertEquals(userCredit.getNumber(), 23);
        assertEquals(userCredit.getCredits(), 2);

    }

    // Testing compareTo with 1 return
    @Test
    public void testCompareToPositive() {
        CreditCourse userCredit = new CreditCourse("Produce", 32);
        CreditCourse userCredit2 = new CreditCourse("Meat", 43, 72);
        assertEquals(userCredit.compareTo(userCredit2), 1);
    }

    // Testing compareTo with -1 return
    @Test
    public void testCompareToNegative() {
        CreditCourse userCredit = new CreditCourse("Produce", 32);
        CreditCourse userCredit2 = new CreditCourse("Produce", 43, 72);
        assertEquals(userCredit.compareTo(userCredit2), -1);
    }

    // Testing compareTo with 0 return
    @Test
    public void testCompareToZero() {
        CreditCourse userCredit = new CreditCourse("Produce", 32);
        CreditCourse userCredit2 = new CreditCourse("Produce", 32, 72);
        assertEquals(userCredit.compareTo(userCredit2), 0);
    }

    // Testing equals with true return
    @Test
    public void testEqualsTrue() {
        CreditCourse userCredit = new CreditCourse("Produce", 32);
        CreditCourse userCredit2 = new CreditCourse("Produce", 32);
        assertEquals(userCredit.equals(userCredit2), true);
    }

    // Testing equals with false return
    @Test
    public void testEqualsFalse() {
        CreditCourse userCredit = new CreditCourse("Produce", 32);
        CreditCourse userCredit2 = new CreditCourse("Meat", 33);
        assertEquals(userCredit.equals(userCredit2), false);
    }

    // Testing toString with no credit parameters
    @Test
    public void testToStringNoCredits() {
        CreditCourse userCredit = new CreditCourse("Produce", 32);
        assertEquals(userCredit.toString(), "(0)Produce 32");
    }

    // Testing toString with all parameters
    @Test
    public void testToStringWithCredits() {
        CreditCourse userCredit = new CreditCourse("Produce", 32, 72);
        assertEquals(userCredit.toString(), "(72)Produce 32");
    }
    
    // Testing toString with numbers and special characters in department
    @Test
    public void testToStringWithNumberDept() {
        CreditCourse userCredit = new CreditCourse("CP-223", 44, 74);
        assertEquals(userCredit.toString(), "(74)CP-223 44");
    }
    
}
