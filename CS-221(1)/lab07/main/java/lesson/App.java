package lesson;


import java.util.Collection;
import java.util.Deque;
import java.util.Queue;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Allows user to enter filename containing courses to display courses
 * in a particular order
 * 
 * @author krodgers
 * @author Denzel Balbosa
 */


public class App {


    public static void main(String[] args) {

	Scanner userScan = new Scanner(System.in);

	int userInput = 0;
	boolean done = false;
	while(!done){
	    switch(userInput){
	    case 0:
		// Print menu - Get prereqs, list courses, quit
		System.out.println("Your choices:");
		System.out.println("0. Print Menu");
		System.out.println("1. Get prereqs");
		System.out.println("2. List courses");
		System.out.println("3. Quit");
		break;
	    case 1:
		try{
		    /** 
			 * Get Prerequisite classes
			 */
			

		    System.out.println("Listing the prerequisites");
		    System.out.println("Enter the course for which to get prereqs (dept courseNo credits): " );

		    Scanner lineScan = new Scanner(userScan.nextLine());
		    CreditCourse target = new CreditCourse(lineScan.next(),
					      lineScan.nextInt(), lineScan.nextInt());
		    
		    lineScan.close();
		    System.out.println("Enter filename (x.txt): ");
		    String filename = userScan.nextLine();
		    Scanner fileScan = new Scanner(new File(filename));
		    Deque<CreditCourse> prereqs = CoursePlanner.organizePrereqs(target, fileScan);

		    System.out.println("To take " + target + ", you must take the following classes: " );
		    printStructure(prereqs);
		    
		    fileScan.close();
		} catch(FileNotFoundException e){
		    System.out.println("Tried to open file, but couldn't find it");
		} 
		break;
	    case 2:

			/**
			 * Get courses in either chronological order or 
			 * reverse chronological order
			 */

			try {
				System.out.println("M - most recent to least recent");
				System.out.println("L - least recent to most recent");
				System.out.print("In what order should courses be displayed? M/L ");
				char order = userScan.next().charAt(0);
	
				System.out.print("In what order are courses listed in the file? M/L ");
				char fileOrder = userScan.next().charAt(0);
	
				System.out.print("Enter filename (x.txt): ");
				String file = userScan.next();
				Scanner fileScan = new Scanner(new File(file));
	
				Deque<CreditCourse> listCourse;
				Queue<CreditCourse> revListCourse;
				if (order == 'M' && fileOrder == 'M') {
					listCourse = CoursePlanner.chronologicalOrder(fileScan);
					printStructure(listCourse);
				} else if (order == 'L' && fileOrder == 'M') {
					revListCourse = CoursePlanner.revChronologicalOrder(fileScan);
					printStructure(revListCourse);
				} else if (order == 'L' && fileOrder == 'L') {
					listCourse = CoursePlanner.chronologicalOrder(fileScan);
					printStructure(listCourse);
				} else if (order == 'M' && fileOrder == 'L') {
					revListCourse = CoursePlanner.revChronologicalOrder(fileScan);
					printStructure(revListCourse);
				}
				fileScan.close();
			} catch(FileNotFoundException e) {
				System.out.println("Tried to open file, but couldn't find it");
			}
			break;
			case 3:
			done = true;
			break;
			default:
			System.out.println("Invalid menu option - enter 1, 2, or 3");
			}

			System.out.print("Enter Option: ");
			userScan = new Scanner(System.in);
			userInput = Integer.parseInt(userScan.nextLine());

			
		}
		/**
		 * close scanner
		 */
		userScan.close();
	    

    }


    /**
     * Prints the contents of a Queue or Deque by popping items.  This
     * method will modify the data structure - courses will be empty
     * when this method returns
     * DO NOT MODIFY THIS METHOD
     * @param courses - the data structure to print
     */
    public static void printStructure(Queue<CreditCourse> courses){
	while(courses.size() > 0){
	    System.out.println(courses.remove());
	}
    }
}
