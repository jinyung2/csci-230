/*  Java Class: InsertionSort.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: Mar 18, 2018
    Description: Insertion Sort implementation using Array List.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Collection;
import java.util.ArrayList;

public class InsertionSort{
    private ArrayList<Integer> list;
    private int dataMove;
    private int keyComp;


    public InsertionSort(){
        list = new ArrayList<>();
    }

    public void sort(){
        dataMove = 0;
        keyComp = 0;
        for (int i = 1; i < list.size(); i++){
            int startIndex = i;
            int targetIndex = i;
            for (int j = i-1; j >= 0; j--){
                if (compareInt(list.get(i),list.get(j)) < 0){
                    targetIndex = j;
                }
                else{break;}
            }
            Integer temp = list.remove(i);
            list.add(targetIndex, temp);
            if (startIndex != targetIndex){
                dataMove++;
            }
        }
    }

    public void add(Integer e){
        list.add(e);
    }

    void addAll(Collection<? extends Integer> c){
        list.addAll(c);
    }

    private int compareInt(Integer a, Integer b){
        keyComp++;
        int result = a - b;
        if (result == 0){
            return 0;
        }
        else if(result < 0){
            return -1;
        }
        else{
            return 1;
        }
    }

    void clear(){
        list.clear();
    }

    public String toString(){
        return list.toString();
    }

    public int getDataMove() {
        return dataMove;
    }

    public int getKeyComp() {
        return keyComp;
    }
    public String generateReport(){
        String result = "Total key comparisons: " + keyComp + "\nTotal data moved: " + dataMove + "\n";
        return result;
    }


}
