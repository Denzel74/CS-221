package lesson;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Scanner;

import java.io.File;

/**
 * Contains methods to organize courses.
 * 
 * All methods take course information input in the form
 * <pre>
 * <dept> <courseNo> <credits>
 * <dept> <courseNo> <credits>
 * ...
 * </pre>
 *
 * @author
 */

public class CoursePlanner{
    /**
       Returns data structure that will list courses in the order they
       must be taken in order to take target course

       Classes with the same last digit are in a series and must be
       taken in series order.  For example, EDU 101, EDU 221, EDU 251,
       EDU 321, EDU 451 form a series.
       Hint: Use modulus division to extract the last digit from the course number


       @param target - the course for which to find prereqs
       @param courses - a Scanner object already opened to input containing classes in numerical order
    */
    public static Deque<CreditCourse> organizePrereqs(CreditCourse target, Scanner courses) {
        ArrayList<CreditCourse> course = new ArrayList<CreditCourse>();
        Deque<CreditCourse> sortedCourse = new LinkedList<CreditCourse>();


        while(courses.hasNext()) {

            String dept = courses.next();
            int num = courses.nextInt();
            int cred = courses.nextInt();
            if (dept.compareTo(target.getDepartment()) == 0
            && (num % 10 == target.getNumber() % 10)) {

                course.add(new CreditCourse(dept, num, cred));
            }      
             
        }

        for (CreditCourse print : course) {
            if (print.getNumber() < target.getNumber()) {
                sortedCourse.addLast(print);
            } else {
                sortedCourse.addFirst(print);
            }
            
        }
        
        return sortedCourse;
    }
    

    /**
     * Returns a Deque that will list courses from earliest taken
     * taken to most recently taken
     *
     * @param transcript - Scanner open to input containing courses ordered
     *                   most recently taken to earliest taken
     */
    public static Deque<CreditCourse> chronologicalOrder(Scanner transcript){
        ArrayList<CreditCourse> course = new ArrayList<CreditCourse>();
        Deque<CreditCourse> sortedCourses = new LinkedList<CreditCourse>();

        while (transcript.hasNext()) {

            String dept = transcript.next();
            int num = transcript.nextInt();
            int cred = transcript.nextInt();

            course.add(new CreditCourse(dept, num, cred));
        }

        for (CreditCourse sort : course) {
            sortedCourses.addFirst(sort);
        }
	    return sortedCourses;
    }

 
    /**
     * Returns a Queue that will list courses from most
     * recently taken to earliest taken
     *
     * @param transcript - Scanner open to input containing courses ordered
     *                   most recently taken to earliest taken
     */
    public static Queue<CreditCourse> revChronologicalOrder(Scanner transcript){
	    ArrayList<CreditCourse> course = new ArrayList<CreditCourse>();
        Queue<CreditCourse> sortedCourses = new LinkedList<CreditCourse>();

        while (transcript.hasNext()) {

            String dept = transcript.next();
            int num = transcript.nextInt();
            int cred = transcript.nextInt();

            course.add(new CreditCourse(dept, num, cred));
        }

        for (CreditCourse sort : course) {
            sortedCourses.add(sort);
        }
        
        if (!sortedCourses.element().equals(course.get(0))){
            Collections.reverse(course);
            for (CreditCourse sort : course) {
                sortedCourses.add(sort);
            }
        }
	    return sortedCourses;
    }

}
