/*  Java Class: BucketSortTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: Mar 26, 2018
    Description: A Bucket Sort algorithm implementation, similar to the algorithm outlined in the book. The sorting algorithm collects values from an unsorted array in the tester class and

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Random;

public class BucketSortTester {
    public static void main(String[] args) {
        Random rand = new Random(System.nanoTime());
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++){     // Fills the initial array with 100 elements.
            array[i] = rand.nextInt(100000);
        }
        BucketSort sorter = new BucketSort();
        System.out.println("Before sort: " + sorter.toString(array));
        System.out.println();
        System.out.println("After sort: " + sorter.toString(sorter.sort(array)));
    }
}
