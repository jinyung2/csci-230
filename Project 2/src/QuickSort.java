/*  Java Class: QuickSort.java
    Author: Jin Choi
    Class: CSCI 230
    Date: April 18, 2018
    Description: The quick sort book code, modified to handle the array in the way provided by the abstractSort class.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Comparator;

public class QuickSort<K,V> extends AbstractSort<K,V> {

    public QuickSort(int size){
        super(size);
        setSortingMethod("Quick Sort");
    }

    public void sort(MapEntry<K,V>[] S, Comparator<K> comp) {
        zero();
        RunTime r = new RunTime();
        quickSortInPlace(S, comp, 0, S.length-1);
        r.setEndTime();
        setEntries(S);
        setRunTime(r.computeTime());
    }

    /** Sort the subarray S[a..b] inclusive. */
    private void quickSortInPlace(MapEntry<K,V>[] S, Comparator<K> comp,
                                             int a, int b) {
        if (a >= b) return;                // subarray is trivially sorted
        int left = a;
        int right = b-1;
        MapEntry<K,V> pivot = S[b];
        MapEntry<K,V> temp;                            // temp object used for swapping
        while (left <= right) {
            // scan until reaching value equal or larger than pivot (or right marker)
            while (left <= right && comp.compare(S[left].getKey(), pivot.getKey()) < 0){
                left++;
                keyComp++;
            }
            // scan until reaching value equal or smaller than pivot (or left marker)
            while (left <= right && comp.compare(S[right].getKey(), pivot.getKey()) > 0) {
                right--;
                keyComp++;
            }
            if (left <= right) {             // indices did not strictly cross
                // so swap values and shrink range
                temp = S[left]; S[left] = S[right]; S[right] = temp;
                left++; right--;
                dataMove = dataMove + 3;
            }
        }
        // put pivot into its final place (currently marked by left index)
        temp = S[left]; S[left] = S[b]; S[b] = temp;
        dataMove = dataMove + 3;
        // make recursive calls
        quickSortInPlace(S, comp, a, left - 1);
        quickSortInPlace(S, comp, left + 1, b);
    }
}
