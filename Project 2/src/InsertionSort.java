/*  Java Class: InsertionSort.java
    Author: Jin Choi
    Class: CSCI 230
    Date: April 18, 2018
    Description: The code for implementing insertion sort.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Comparator;

public class InsertionSort<K,V> extends AbstractSort<K,V> {

    public InsertionSort(int size){
        super(size);
        setSortingMethod("Insertion Sort");
    }
    @Override
    protected void sort(MapEntry<K, V>[] entries, Comparator<K> comp) {
        zero();
        RunTime r = new RunTime();
        for (int i = 1; i < entries.length; i++){
            int targetIndex = i;
            for (int j = i-1; j >= 0; j--){
                if (comp.compare(entries[i].getKey(),entries[j].getKey()) < 0){
                    keyComp++;
                    targetIndex = j;
                }
                else{ break; }
            }
            if (targetIndex != i){
                dataMove = dataMove + 3;
                MapEntry<K,V> temp = entries[i];
                for (int k = i; k > targetIndex; k--){
                    dataMove++;
                    entries[k] = entries[k-1];
                }
                entries[targetIndex] = temp;
            }
        }
        r.setEndTime();
        setRunTime(r.computeTime());
        setEntries(entries);
    }
}
