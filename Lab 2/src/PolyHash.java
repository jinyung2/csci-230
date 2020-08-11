/*  Java Class: PolyHash.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: Mar 12, 2018
    Description: Poly Hash class that contains a hashmap within a hashmap. Uses the polynomial hashing method outlined in the book using a long type that is converted back into a int to deal with overflow.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.HashMap;

public class PolyHash {
    private HashMap<Integer, HashMap<String, Integer>> outerMap;

    public PolyHash(){
        outerMap = new HashMap<>();
    }

    private int PolyHashCode(String s, int a) {
        Long code = 0L;
        char[] input = s.toCharArray();
        int n = input.length;
        for (int i = 0; i < n; i++) {
            code += (long)(input[i] * Math.pow(a, n - (i + 1)));
        }
        Integer result = code.intValue();
        return result;
    }

    public void PolyAdd(String s, int a){
        int outerKey = PolyHashCode(s,a);
        HashMap<String, Integer> innerMap = outerMap.get(outerKey);
        if (innerMap == null){
            innerMap = new HashMap<String, Integer>();
            innerMap.put(s,1);
        }
        else if(innerMap.containsKey(s)){
            int oldVal = innerMap.get(s);
            innerMap.replace(s,oldVal,oldVal+1);
        }
        else{
            innerMap.put(s,1);
        }
        outerMap.put(outerKey,innerMap);
    }

    public void debug(){
        if (outerMap.isEmpty()){ System.out.println("It's Empty"); }
        else{
            int count = 0;
            for (Integer i : outerMap.keySet()){
                if (outerMap.get(i).size() > 1){
                    System.out.println("Collision found at: "  + outerMap.get(i).toString());
                    for (int j = 0; j < outerMap.get(i).size(); j++){
                        count++;
                    }
                }
            }
            if (count == 0){
                System.out.println("There were no collisions.");
            }
            else{
                System.out.println("\nThe number of collisions is: " + count);
            }
        }
    }
}
