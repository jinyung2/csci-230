/*  Java Class: MergeSort.java
    Author: Jin Choi
    Class: CSCI 230
    Date: April 18, 2018
    Description: The merge sort code, taken mostly from the book source code.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort<K,V> extends AbstractSort<K,V> {

    public MergeSort(int size){
        super(size);
        setSortingMethod("Merge Sort");
    }

    public void sort(MapEntry<K,V>[] entries, Comparator<K> comp){
        zero();
        RunTime r = new RunTime();
        mergeSort(entries,comp);
        r.setEndTime();
        setRunTime(r.computeTime());
        setEntries(entries);
    }

    public void merge(MapEntry<K,V>[] S1, MapEntry<K,V>[] S2, MapEntry<K,V>[] S, Comparator<K> comp) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && comp.compare(S1[i].getKey(), S2[j].getKey()) < 0)){
                if (j != S2.length){
                    keyComp++;
                }
                dataMove++;
                S[i+j] = S1[i++];                     // copy ith element of S1 and increment i
            }
            else{
                dataMove++;
                S[i+j] = S2[j++];                     // copy jth element of S2 and increment j
            }
        }
    }

    private void mergeSort(MapEntry<K,V>[] entries, Comparator<K> comp) {
        int n = entries.length;
        if (n < 2) return;                        // array is trivially sorted
        // divide
        int mid = n/2;
        MapEntry<K,V>[] S1 = Arrays.copyOfRange(entries, 0, mid);   // copy of first half
        MapEntry<K,V>[] S2 = Arrays.copyOfRange(entries, mid, n);   // copy of second half
        // conquer (with recursion)
        mergeSort(S1, comp);                      // sort copy of first half
        mergeSort(S2, comp);                      // sort copy of second half
        // merge results
        merge(S1, S2, entries, comp);               // merge sorted halves back into original
    }
}
