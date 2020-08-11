/*  Java Class: HeapSort.java
    Author: Jin Choi
    Class: CSCI 230
    Date: April 18, 2018
    Description: The code for the heap sort algorithm. Uses the array and the L: 2n+1 R: 2n+2 index scheme to heapify to create max heap.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Comparator;

public class HeapSort<K,V> extends AbstractSort<K,V> {

    public HeapSort(int size){
        super(size);
        setSortingMethod("Heap Sort");
    }

    @Override
    protected void sort(MapEntry<K, V>[] entries, Comparator<K> comp) {
        zero();
        RunTime r = new RunTime();
        int n = entries.length;
        for (int i = n/2 - 1; i >=0; i--){
            heapify(entries, n, i, comp);
        }
        for (int i = n-1; i >= 0; i--){
            dataMove = dataMove + 3;
            MapEntry<K,V> temp = entries[i];
            entries[i] = entries[0];
            entries[0] = temp;

            heapify(entries,i,0, comp);
        }
        setEntries(entries);
        r.setEndTime();
        setRunTime(r.computeTime());
    }

    private void heapify(MapEntry<K,V>[] entries, int size, int i, Comparator<K> comp){
        int largestIndex = i;
        int leftIndex = 2*i + 1;
        int rightIndex = 2*i + 2;

        if (leftIndex < size && comp.compare(entries[leftIndex].getKey(), entries[largestIndex].getKey()) > 0){
            keyComp++;
            largestIndex = leftIndex;
        }
        if (rightIndex < size && comp.compare(entries[rightIndex].getKey(), entries[largestIndex].getKey())>0){
            keyComp++;
            largestIndex = rightIndex;
        }
        if (largestIndex != i){
            dataMove = dataMove + 3;
            MapEntry<K,V> temp = entries[i];
            entries[i] = entries[largestIndex];
            entries[largestIndex] = temp;

            heapify(entries, size, largestIndex, comp);
        }
    }
}
