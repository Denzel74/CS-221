package lesson;

import java.util.ArrayList;
import java.util.List;

/**
 * Lets users use binary search program
 * 
 * @author Denzel Balbosa
 */

public class App {

    public static void main(String[] args) {
        List<Integer> tickets = new ArrayList<Integer>();
        MyBinarySearch myBin = new MyBinarySearch();

        tickets.add(24);
        tickets.add(32);
        tickets.add(72);
        tickets.add(54);

        System.out.println("Unsorted list: " + tickets.get(0) + ", " +
        tickets.get(1) + ", " + tickets.get(2) + ", " + tickets.get(3));
        System.out.println("Sorting list...");
        myBin.sort(tickets);

        System.out.println("There are " + tickets.size() + " tickets");
        System.out.println("Ticket 72 is in line: " + 
        myBin.search(tickets, 72, 0, 3));
    }
}
