/*  Java Class: HashMapTester.java
    Author: Jin Choi
    Class: CSCI230
    Date: 02/28/18
    Description: A test driver to see if the ChainHashMap code provided from the textbook produces identical results to the HashMap class provided in the java standard library. Additional test cases ran on the book code to certify the methods work as intended.
    I certify that the code below is my own work.
   Exception(s): N/A
*/

import java.util.HashMap;

public class HashMapTester{
public static void main(String[]args){
    HashMap<Integer, String> hash = new HashMap();
    System.out.println("Adding entries with keys 13, 21, 5, 37 and 15 with value corresponding to its key as a String using the java standard library HashMap.");
    hash.put(13,"13");
    hash.put(21,"21");
    hash.put(5,"5");
    hash.put(37,"37");
    hash.put(15,"15");
    System.out.println("Searching for key 10 within map: " + hash.get(10));
    System.out.println("Searching for key 21 within map: " + hash.get(21));
    System.out.println("Removing key 37 and returning to make sure it was removed: " + hash.remove(37));
    System.out.println("Searching for key 37 after removal: " + hash.get(37) + "\n\nNow testing using the ChainHashMap class provided in the textbook source code.");
    ChainHashMap<Integer, String> hash2 = new ChainHashMap<>();
    System.out.println("Adding entries with keys 13, 21, 5, 37 and 15 with value corresponding to its key as a String using the ChainHashMap class from book.");
    hash2.put(13,"13");
    hash2.put(21,"21");
    hash2.put(5,"5");
    hash2.put(37,"37");
    hash2.put(15,"15");
    System.out.println("Searching for key 10 within map: " + hash2.get(10));
    System.out.println("Searching for key 21 within map: " + hash2.get(21));
    System.out.println("Removing key 37 and returning to make sure it was removed: " + hash2.remove(37));
    System.out.println("Searching for key 37 after removal: " + hash2.get(37) + "\n");
    System.out.println("Testing some extra credit cases for duplicate entries, outputting iterators to see if duplicate values are shown.");
    System.out.println("Replacing the value at key 13 to 14. ");
    hash2.put(13,"14");
    System.out.println("Searching key 13 within map: " + hash2.get(13));
    System.out.println("Outputting current entries' keys in map.");
    for (Integer i : hash2.keySet()) {
        System.out.println(i);
    }
    System.out.println("Outputting current entries' values in map.");
    for (String s : hash2.values()){
        System.out.println(s);
    }
    System.out.println("Outputting the entries' key value pairs in map.");
    for (Entry e : hash2.entrySet()){
        System.out.println("<" + e.getKey() + ", " + e.getValue() + ">");
    }
}
}