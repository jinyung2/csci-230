/*  Java Class: SelectionSort.java
    Author: Jin Choi
    Class: CSCI 230
    Date: April 18, 2018
    Description: The code that implements the selection sort algorithm.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Comparator;

public class SelectionSort<K,V> extends AbstractSort<K,V>{

    public SelectionSort(int size){
        super(size);
        setSortingMethod("Selection Sort");
    }

    public void sort(MapEntry<K,V>[] entries, Comparator<K> comp) {
        zero();
        RunTime r = new RunTime();
        int index = 0;
        while (index < getSize()-1){
            MapEntry<K,V> minimum = entries[index]; // sets first entry as min
            int minIndex = 0;
            for (int i = index + 1; i < getSize(); i++){
                keyComp++;
                if (comp.compare(entries[i].getKey(),minimum.getKey()) < 0){ // compare the key value of the current and minimum.
                    minimum = entries[i];
                    minIndex = i;
                }
            }
            if (!minimum.equals(entries[index])){ // minimum is not the first index you started at, then swap
                dataMove = dataMove + 3;
                MapEntry<K,V> temp = entries[index];
                entries[index] = minimum;
                entries[minIndex] = temp;
            }
            index++; // increment index and perform one less search for min, the starting elements will have been sorted to correct order.
        }
        r.setEndTime();
        setEntries(entries);
        setRunTime(r.computeTime()); // compute run time
    }
}