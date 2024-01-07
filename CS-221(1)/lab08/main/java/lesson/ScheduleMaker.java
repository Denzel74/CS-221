package lesson;

import java.util.Iterator;
import java.util.ArrayList;

/**
   Utility class that generates a String representation of a course
   schedule or creates a collection of CreditCourses from a collection
   of Strings.

   @author Denzel Balbosa
*/
public class ScheduleMaker{

    /**
       Returns a string of the course schedule in the form

       Full Schedule
       -------------
       <dept> <course number>
       <dept> <course number>

       @param iter - an iterator over a collection of CreditCourses to print
       @return String listing all courses from the iterator
       
    */
    public static String getScheduleStr(Iterator<CreditCourse> iter){
	String res = "Full Schedule\n-------------\n";

    while (iter.hasNext()) {
        CreditCourse list = iter.next();
        res += list.getDepartment() + " " + list.getNumber() + "\n";
    }
	return res;
    }


    /**
       Parses and creates CreditCourses from Strings. Each String
       should be in the format <courseNo> <credits> <dept>.

       Catch NumberFormatException. You don't have to do anything with it except skip that String.

       @param iter - an iterator over Strings in the format <courseNo> <credits> <dept>
       @return an ArrayList of CreditCourse objects created from the Strings
    */

    public static ArrayList<CreditCourse> parseSchedule(Iterator<String> iter){
        ArrayList<CreditCourse> courseList = new ArrayList<CreditCourse>();

        try {
            while(iter.hasNext()) {
                String line = iter.next();
                String[] lineParse = line.split(" ");

                    if (((lineParse[0].toCharArray()[0] < '0' 
                    || lineParse[0].toCharArray()[0] > '9'))
                    || ((lineParse[1].toCharArray()[0] < '0' 
                    || lineParse[1].toCharArray()[0] > '9'))) {
                        iter.remove();
                    } else {
                        int num = Integer.parseInt(lineParse[0]);
                        int cred = Integer.parseInt(lineParse[1]);
                        String dept = lineParse[2];

                        courseList.add(new CreditCourse(dept, num, cred));
                    }


                
            }

        } catch(NumberFormatException e){

        }
	return courseList;
    }

}
