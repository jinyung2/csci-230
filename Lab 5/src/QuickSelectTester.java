/*  Java Class: QuickSelectTester.java
    Author: Jin Choi
    Class: CSCI 230
    Date: April 3rd, 2018
    Description: The driver class for QuickSelect. Reads the 1k and 100k files from project two and searches for the smallest, median and largest value (based on k value of 1, n/2 and n. Also counts the number of recursive calls for extra credit.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Comparator;
import java.util.ArrayList;
import java.io.*;
import java.util.stream.Stream;

public class QuickSelectTester<K> {

    public static void main(String[] args) {
        QuickSelect test = new QuickSelect();
        ArrayList<Integer> list1k = new ArrayList<>();
        ArrayList<Integer> list100k = new ArrayList<>();
        try{
            FileReader smallRan1k = new FileReader("/home/john/Downloads/small1k.txt");
            FileReader large100k = new FileReader("/home/john/Downloads/large100k.txt");
            compileIntList(list1k,smallRan1k);
            compileIntList(list100k,large100k);
            Integer first1k = test.quickSelect(list1k,new IntComparator(), 1);
            System.out.println("k = 1 for 1,000 random entry file is : " + first1k + "\nThe number of recursive calls was: " + test.getRecurCount());
            test.resetRecurCount();
            Integer median1k = test.quickSelect(list1k,new IntComparator(), list1k.size()/2);
            System.out.println("k = n/2 for 1,000 random entry file is : " + median1k + "\nThe number of recursive calls was: " + test.getRecurCount());
            test.resetRecurCount();
            Integer last1k = test.quickSelect(list1k,new IntComparator(), list1k.size());
            System.out.println("k = n for 1,000 random entry file is : " + last1k + "\nThe number of recursive calls was: " + test.getRecurCount());
            test.resetRecurCount();
            Integer first100k = test.quickSelect(list100k,new IntComparator(), 1);
            System.out.println("k = 1 for 100,000 random entry file is : " + first100k + "\nThe number of recursive calls was: " + test.getRecurCount());
            test.resetRecurCount();
            Integer median100k = test.quickSelect(list100k,new IntComparator(), list100k.size()/2);
            System.out.println("k = n/2 for 100,000 random entry file is : " + median100k + "\nThe number of recursive calls was: " + test.getRecurCount());
            test.resetRecurCount();
            Integer last100k = test.quickSelect(list100k,new IntComparator(), list100k.size());
            System.out.println("k = n for 100,000 random entry file is : " + last100k + "\nThe number of recursive calls was: " + test.getRecurCount());
            test.resetRecurCount();
            System.out.println("log n of 1000: " + log02(1000) +"\nlog n of 100000: "+ log02(100000));
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private static void compileIntList(ArrayList<Integer> list, FileReader file){
        BufferedReader reader = null;
        reader = new BufferedReader(file);
        Stream<String> stream = reader.lines();
        Object[] inputs = stream.toArray();
        for (Object o : inputs){
            String[] strArr = o.toString().split("  ");
            for (int i = 0; i < strArr.length;i++){
                if (!strArr[i].equals("")){
                    strArr[i] = strArr[i].replace(" ","");
                    Integer cur = Integer.parseInt(strArr[i]);
                    list.add(cur);
                }
            }
        }
    }
    private static class IntComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer a, Integer b) {
            if (a > b){return 1;}
            else if (a == b) {return 0;}
            else{return -1;}
        }
    }
    private static double log02(int x){
        return (Math.log(x)/Math.log(2));
    }
}
