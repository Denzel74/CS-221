package lesson;

/**
 * Represents a course associated with a number of credits
 *
 * @author Denzel Balbosa
 */

public class CreditCourse implements Comparable<CreditCourse> {

    // Instance Variables
    private String dept;
    private Integer number;
    private Integer credits;


    // Constructors
    
    /**
     * Constructor - Creates course with department dept, course
     * number number, and zero credits
     *
     * @param dept - the department in which this course belongs
     * @param number - the number of this course
     */
    CreditCourse(String dept, int number){
        this.dept = dept;
        this.number = number;
        this.credits = 0;

    }


    /**
     * Constructor - Creates course with department dept, course
     * number number, and numCredits credits
     *
     * @param dept - the department in which this course belongs
     * @param number - the number of this course
     * @param numCredits - the number of credits this course is worth
     */
    CreditCourse(String dept, int number, int numCredits){
        this.dept = dept;
        this.number = number;
        this.credits = numCredits;

    }

    // Getters and Setters

    /**
     * Returns the course's department
     * @return deparment code
     */
    public String getDepartment(){
	return dept;
    }

    /**
     * Sets course's department code
     */
    public void setDepartment(String department){
        this.dept = department;
    }

    /**
     * Returns the course's number
     * @return course number
     */
    public int getNumber(){
	return number;
    }

    /**
     * Sets course's number
     */
    public void setNumber(int number){
        this.number = number;
    }

    /**
     * Returns the course's credit number
     * @return credit number
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Sets course's credit number
     * @param userCredits
     */
    public void setCredits(int userCredits) {
        this.credits = userCredits;
    }


    // Utility Methods

    /**
     * Compares this course to other 
     * Courses are ordered by
     * department, then by course number
     *
     * @param other - the course to compare
     * @return a negative integer if this course comes before other, 0 if they are the
     *          same course, or a positive if this course comes after other
     */
    @Override
    public int compareTo(CreditCourse other){
        int compareVal;

        if (dept.compareTo(other.dept) == 0) {
            if (number.compareTo(other.number) > 0) {
                compareVal = 1;
            } else if (number.compareTo(other.number) < 0) {
                compareVal = -1;
            } else {
                compareVal = 0;
            }
        } else if (dept.compareTo(other.dept) > 0) {
            compareVal = 1;
        } else {
            compareVal = -1;
        }
	return compareVal;
    }

    /**
     * Compares this course to other 
     * 
     *
     * @param other - the course to compare
     * @return true if this and other department's and course 
     *         numbers are equal, false otherwise
     */
    public boolean equals(CreditCourse other){
        if (dept.equals(other.dept) && number.equals(other.number)) {
            return true;
        } else {
            return false;
        }
	
    }

    /**
     * Returns representation of this object in the format
     * (credits)dept number 
     *
     * For example:
     * (3)CS 221
     * 
     * @return (credits)dept number 
     */
    @Override
    public String toString(){
        String s = "";
        s += "(" + getCredits() + ")" + getDepartment() + " " + getNumber();

	return s;
    }
    
}
