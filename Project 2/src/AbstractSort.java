/*  Java Class: AbstractSort.java
    Author: Jin Choi
    Class: CSCI 230
    Date: April 18, 2018
    Description: The abstract superclass for all the various sorting classes. Holds the data required for project.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Comparator;

public abstract class AbstractSort<K,V> {
    private String fileName, sortingMethod;
    protected int dataMove, keyComp;
    private double runTime;
    private MapEntry<K,V>[] entries;
    private int size;

    public AbstractSort(int size){
        if (size == 1000){ fileName = "small1k.txt"; }
        else{ fileName = "large100k.txt"; }
        this.entries = new MapEntry[size];
        this.size = size;
    }


    public String getSortingMethod() {
        return sortingMethod;
    }

    public void setSortingMethod(String sortingMethod) {
        this.sortingMethod = sortingMethod;
    }
    public void zero(){
        dataMove = 0;
        keyComp = 0;
        runTime = 0;
    }

    public int getSize() {
        return size;
    }

    public MapEntry<K, V>[] entries() {
        return entries;
    }

    public void setEntries(MapEntry<K, V>[] entries) {
        this.entries = entries;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getRunTime() {
        return runTime;
    }

    public void setRunTime(double runTime) {
        this.runTime = runTime/1000000.0;
    }

    protected abstract void sort(MapEntry<K,V>[] entries, Comparator<K> comp); // The sorting method to be implemented in non-abstract classes.

    public StringBuilder generateReport(){
        StringBuilder result = new StringBuilder();
        result.append("1. Sorting Method: " + sortingMethod + "\n2. Input file name: " + fileName + "\n3. Value sorted: " + getSize() + "\n4. Key data type: " + entries[0].getKey().getClass() + "\n5. Key comparisons: " + keyComp + "\n6. Data moves: " + dataMove + "\n7. Run time: " + runTime + "ms" + "\n8. First and Last 5 pairs: " + firstAndLast() +"\n");
        return result;
    }

    private StringBuilder firstAndLast(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < entries.length; i++){
            if (i == 0){result.append("\n");}
            if (i == 5){
                result.append("\n");
                i = entries.length - 5;
            }
            result.append("<" + entries[i].getKey() + ", " + entries[i].getValue() + "> ");
        }
        return result;
    }

}
