/*  Java Class: Merge.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: 4/11/2018
    Description: Implements union, intersect, and subtract using hashset and its contained methods (add, retain and remove)

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.HashSet;

public class Merge<E> {

    public Merge(){ }

    public E[] unionMerge(E[] a, E[] b){
        E[] result;
        HashSet<E> unionSet = new HashSet<>();
        for (E e : a){
            unionSet.add(e);
        }
        for (E e : b){
            unionSet.add(e);
        }
        result =(E[]) unionSet.toArray();
        return result;
    }

    public E[] intMerge(E[] a, E[] b){
        E[] result;
        HashSet<E> mainSet = new HashSet();
        for (E e : a){ mainSet.add(e); }
        HashSet<E> intSet = new HashSet();
        for (E e : b){ intSet.add(e); }
        mainSet.retainAll(intSet);
        result = (E[]) mainSet.toArray();
        return result;
    }

    public E[] subMerge(E[] a, E[] b){
        E[] result;
        HashSet<E> mainSet = new HashSet();
        for (E e : a){ mainSet.add(e); }
        HashSet<E> subSet = new HashSet();
        for (E e : b){ subSet.add(e); }
        mainSet.removeAll(subSet);
        result = (E[]) mainSet.toArray();
        return result;
    }
}
