package lesson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * Reads in course data from the user, then prints in sorted order
 *
 * @author Denzel Balbosa
 */


public class App {

    public static void main(String[] args) {
	// Read in the data
	ArrayList<CreditCourse> courses = getDataPieces();
		
	// Print unsorted data
	// Sort data
	// and print sorted data
	System.out.println(matchingDepts(courses));
	
    }

    /**
     * Reads data from user in the format
     * department courseNo numCredits
     *
     * Reads until the user enters a blank line
     * @return ArrayList containing CreditCourses created from user data
     */
    private static ArrayList<CreditCourse> getData(){
	// O(n) runtime
	ArrayList<CreditCourse> courses = new ArrayList<CreditCourse>();
	System.out.println("Start entering data like: dept courseNo credit");
				
	Scanner kbd = new Scanner(System.in);
	try {
	    String line = kbd.nextLine();
	    while(!line.isEmpty()){
		Scanner lineScan = new Scanner(line);
		String dept = lineScan.next();
		int num = lineScan.nextInt();
		int credits = lineScan.nextInt();
				
		lineScan.close();
		courses.add(new CreditCourse(dept, num, credits));
		line = kbd.nextLine();
	    }
	} catch(InputMismatchException e){
	    System.out.println("Entered wrong data. Usage: dept courseNo credits");
	} catch(NoSuchElementException  e){
	    System.out.println("Didn't enter enough data. Usage: dept courseNo credits");
	} finally{
	    kbd.close();
	}
	return courses;
    }

    /**
     * Reads course data from user by asking for all departments, all
     * course numbers, and all credits
     *
     * Example input:
     * CS CE
     * 100 104
     * 3 6
     *
     * Example returns an ArrayList with two CreditCourses - "CS 100 (3)" and "CE 104 (6)"
     *
     * @return ArrayList containing CreditCourses created from user data
     */
    private static ArrayList<CreditCourse> getDataPieces() {
		ArrayList<CreditCourse> courses = new ArrayList<CreditCourse>();
		String[] depts;
		String[] nums;
		String[] creds;
					
		Scanner kbd = new Scanner(System.in);
		try {
			System.out.println("Enter all departments");
			String line1 = kbd.nextLine();

			System.out.println("Enter all course numbers");
			String line2 = kbd.nextLine();

			System.out.println("Enter all course credits");
			String line3 = kbd.nextLine();
				
			depts = line1.split(" ");
			nums = line2.split(" ");
			creds = line3.split(" ");
			
			Integer[] numParse = new Integer[nums.length];
			Integer[] credParse = new Integer[creds.length];

			for (int i = 0; i < nums.length; i++) {
				int x = Integer.parseInt(nums[i]);
				numParse[i] = x;
			}
			for (int i = 0; i < creds.length; i++) {
				int x = Integer.parseInt(creds[i]);
				credParse[i] = x;
			}
			
			for (int i = 0; i < depts.length; i++) {
				courses.add(new CreditCourse(depts[i], numParse[i], credParse[i]));
			}
			
		} catch(InputMismatchException e) {
			System.out.println("Entered wrong data. Usage: dept courseNo credits");
		} catch(NoSuchElementException  e) {
			System.out.println("Didn't enter enough data. Usage: dept courseNo credits");
		} finally{
			kbd.close();
		}
		return courses;
		}
    
    /**
     * Returns a string that prints a course, followed by a list of
     * all other courses in the same department
     */
    private static String matchingDepts(ArrayList<CreditCourse> courses){
		String s = "";

		for (CreditCourse course : courses) {
			s += "\n" + course.toString();
		}

		Collections.sort(courses);

		s += "\n";
		for (CreditCourse course : courses) {
			s += "\n" + course.toString();
		}
	return s;
    }
    
}
