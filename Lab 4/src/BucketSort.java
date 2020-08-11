/*  Java Class: BucketSort.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: Mar 26, 2018
    Description: A Bucket Sort algorithm implementation, similar to the algorithm outlined in the book. The sorting algorithm collects values from an unsorted array in the tester class and inserts them into a bucket of however many entries, incremented based on count (accounting for duplicates). It is then removed from the bucket into the resulting array from starting from the back to maintain stability and produces a sorted array.

    I certify that the code below is my own work.

	Exception(s): Code based off of a combination of pseudocode algorithm in book as well as reference from online.

*/

public class BucketSort {
    private int maxValue = 0;

    public BucketSort(){ }

    public Integer[] sort(Integer[] a){
        // find max value //
        for (Integer i : a ){
            maxValue = Math.max(maxValue,i);
        }
        Integer[] bucket = new Integer[maxValue+1];
        //fill with all 0s to aoid null pointer excep.
        for (int i = 0; i < bucket.length;i++){
            bucket[i] = 0;
        }
        Integer[] result = new Integer[a.length];
        for (Integer anA : a) {
            bucket[anA]++; //counts how many of those values there are, increments everytime you run into a duplicate entry.
        }
        int outPosition = a.length-1;
        for (int i = bucket.length-1; i >= 0; i--){
            for (int j = 0; j < bucket[i]; j++){
                result[outPosition--] = i;
            }
        }
        return result;
    }
    /*
    For debugging purposes, outputs the contents of the array being sorted
     */
    String toString(Integer[] a){
        StringBuilder result = new StringBuilder();
        for (Integer i : a) {
            result.append(i);
            result.append(" ");
        }
        return result.toString();
    }

}
