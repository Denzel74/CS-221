package lesson;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Uses several kinds of data structures to print and parse schedules 
 * 
 * @author krodgers
 * @author Denzel Balbosa
 */


public class App {
    
    
    public static void main(String[] args) {
	// Define courses to be used
	CreditCourse[] courses = {new CreditCourse("EDU", 100, 2),
				  new CreditCourse("EDU", 101, 2),
				  new CreditCourse("EDU", 102, 2),
				  new CreditCourse("EDU", 103, 2),
				  new CreditCourse("HIST", 200, 2),
				  new CreditCourse("HIST", 204, 2)
	};


	/**
	 * ArrayList of Credit Courses
	 */
	ArrayList<CreditCourse> arrCourse = new ArrayList<CreditCourse>();
	for(CreditCourse course : courses)
	    arrCourse.add(course);
	
	/**
	 * HashSet of Credit Courses 
	 */
	HashSet<CreditCourse> setCourse = new HashSet<CreditCourse>(arrCourse);
	
	/**
	 * Queue of Credit Courses
	 */
	Queue<CreditCourse> queCourse = new LinkedList<CreditCourse>(arrCourse);
	
	/**
	 * ArrayList result of getScheduleStr passed iterator
	 */
	System.out.println("ArrayList: ");
	Iterator<CreditCourse>  iter = arrCourse.iterator();
	System.out.println(ScheduleMaker.getScheduleStr(iter));

	/**
	 * Hashset result of getScheduleStr passed iterator
	 */
	System.out.println("HashSet: ");
	Iterator<CreditCourse>  iter1 = setCourse.iterator();
	System.out.println(ScheduleMaker.getScheduleStr(iter1));

	/**
	 * Queue result of getScheduleStr passed iterator
	 */
	System.out.println("Queue: ");
	Iterator<CreditCourse>  iter2 = queCourse.iterator();
	System.out.println(ScheduleMaker.getScheduleStr(iter2));
	
	/** 
	 * Data to be parsed
	 */ 
	LinkedList<String> listCourse = new LinkedList<String>();
	listCourse.add("100 4 EDU");
	listCourse.add("345 2 HIST");
	listCourse.add("146 3 SOC");
	listCourse.add("602 1 ENG");
	listCourse.add("103 1 CSE");
	listCourse.add("246 2 INMX");
	listCourse.add("not numbers EDU");
	
		
	/** 
	 * Create an iterator for listCourse and pass it to ScheduleMaker.parseSchedule
	 */ 
	Iterator<String>  iter3 = listCourse.iterator();
	ArrayList<CreditCourse> courseList = ScheduleMaker.parseSchedule(iter3);



	/**
	 * Printing the return value from parseSchedule THREE times, three DIFFERENT ways
	 */ 

	/**
	 * 1. Use an enhanced for loop (for-each loop) to print each item 
	 */ 
	System.out.println("Enhanced For Loop: ");
	for (CreditCourse list : courseList) {
		System.out.println(list);
	}

	/**
	 * 2. Use the iterator and a while loop to print each element of the
	 * ArrayList.
	 */
	
	System.out.println("\nWhile Loop + Iterator");
	Iterator<CreditCourse> listIterator = courseList.iterator();
	while (listIterator.hasNext()) {
		System.out.println(listIterator.next());
	}



	/**
	 * 3. Pass the iterator to getScheduleStr and print that String
	 */
	
	System.out.println("\nIterator + getScheduleStr");
	Iterator<CreditCourse> listIterator2 = courseList.iterator();

	System.out.println(ScheduleMaker.getScheduleStr(listIterator2));
    }

    
}
