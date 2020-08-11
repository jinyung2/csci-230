/*  Java Class: InsertionSort.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: Mar 18, 2018
    Description: The tester class for insertion sort. Takes into account sorted, descending and random lists as well as extra credit cases for run time performance analysis.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.ArrayList;
import java.util.Random;

public class SortTester {
    public static void main(String[] args) {
        //INSERTION SORT//
        InsertionSort test = new InsertionSort();
        Random rand = new Random(System.nanoTime());
        ArrayList<Integer> sorted = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++){
            sorted.add(i);
        }
        ArrayList<Integer> descend = new ArrayList<Integer>();
        for (int i = 10; i >= 1; i--){
            descend.add(i);
        }
        ArrayList<Integer> random = new ArrayList<Integer>();
        for(int i = 1; i <= 10; i++){
            random.add(rand.nextInt(1000)+1);
        }
        test.addAll(sorted);
        System.out.println("Contents of sorted list: "+ test.toString());
        test.sort();
        System.out.println("After insertion sort: " + test.toString() + "\n" + test.generateReport());
        test.clear();
        test.addAll(descend);
        System.out.println("Contents of descending list: "+ test.toString());
        test.sort();
        System.out.println("After insertion sort: " + test.toString() + "\n"  + test.generateReport());
        test.clear();
        test.addAll(random);
        System.out.println("Contents of random list: "+ test.toString());
        test.sort();
        System.out.println("After insertion sort: " + test.toString() + "\n"  + test.generateReport());

        //EXTRA CREDIT//

        ArrayList<Integer> oneThousand = new ArrayList<>();
        ArrayList<Integer> tenThousand = new ArrayList<>();
        ArrayList<Integer> hundredThousand = new ArrayList<>();
        fill(oneThousand, 1000);
        fill(tenThousand, 10000);
        fill(hundredThousand, 100000);

        System.out.println("\nTesting Extra Credit cases for 1000, 10000 and 100000 entries using Insertion Sort: ");
        test.clear();
        test.addAll(oneThousand);
        long startTime = System.nanoTime();
        test.sort();
        long endTime = System.nanoTime();
        System.out.println("Run time (1000 entries): " + (endTime - startTime)/1000000.0 + " ms");
        test.clear();
        test.addAll(tenThousand);
        startTime = System.nanoTime();
        test.sort();
        endTime = System.nanoTime();
        System.out.println("Run time (10000 entries): " + (endTime - startTime)/1000000.0 + " ms");
        test.clear();
        test.addAll(hundredThousand);
        startTime = System.nanoTime();
        test.sort();
        endTime = System.nanoTime();
        System.out.println("Run time (100000 entries): " + (endTime - startTime)/1000000.0 + " ms");
    }
    private static void fill(ArrayList<Integer> list, int size){
        Random rand = new Random(System.nanoTime());
        for(int i = 0; i < size; i++){
            list.add(rand.nextInt());
        }
    }
}
