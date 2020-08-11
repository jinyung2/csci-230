/*  Java Class: WordCount.java
    Author: Jin Choi
    Class: CSCI 230
    Date: June 11, 2018
    Description: WordCount for US Declaration of Independence.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WordCount {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader("/home/john/Documents/CSCI230/usdeclarPC.txt"));
            HashMap<String,Integer> freq = new HashMap<>();
            Stream<String> stream = reader.lines();
            Object[] arr = stream.toArray();
            for (Object o : arr ){
                String[] strArr = String.valueOf(o).split(" ");
                for (String s : strArr){
                    s = s.toLowerCase();
                    if (!s.equals("") && !s.equals(" ")){
                        if(freq.containsKey(s)){
                            int val = freq.get(s);
                            freq.replace(s,val, val+1);
                        }
                        else{
                            freq.put(s,1);
                        }
                    }
                }
            }
            Map.Entry<String,Integer> one = null;
            int oneVal = Integer.MIN_VALUE;
            Map.Entry<String,Integer> two = null;
            int twoVal = Integer.MIN_VALUE;
            Map.Entry<String,Integer> three = null;
            int threeVal = Integer.MIN_VALUE;
            for (Map.Entry<String,Integer> e : freq.entrySet()){
                if (e.getValue() > oneVal){
                    one = e;
                    oneVal = e.getValue();
                }
                else if (e.getValue() > twoVal){
                    two = e;
                    twoVal = e.getValue();
                }
                else if (e.getValue() > threeVal){
                    three = e;
                    threeVal = e.getValue();
                }
            }
            System.out.println("Word: " + one.getKey() + " Count: " + one.getValue());
            System.out.println("Word: " + two.getKey() + " Count: " + two.getValue());
            System.out.println("Word: " + three.getKey() + " Count: " + three.getValue());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
