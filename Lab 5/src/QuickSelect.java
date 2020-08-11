/*  Java Class: QuickSelect.java
    Author: Jin Choi
    Class: CSCI 230
    Date: April 3rd, 2018
    Description: Implements the QuickSelect algorithm outlined in the book.

    I certify that the code below is my own work.

	Exception(s): N/A

*/
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class QuickSelect {

    private int recurCount = -1;

    public QuickSelect(){ }

    /*find and returns median value using the quick select algorithm
    given in the book. Uses Arraylist over the queue used in quicksort since this allows for random pivot selection.
     */
    public <K> K quickSelect(ArrayList<K> S, Comparator<K> comp, int k){
        recurCount++;
        Random rand = new Random(System.nanoTime());
        if (S.size() == 1) { return S.get(0); }
        K pivot = S.get(rand.nextInt(S.size()/2)); //pick random pivot
        ArrayList<K> L = new ArrayList<>(); // Less than pivot
        ArrayList<K> E = new ArrayList<>(); // Equal to pivot
        ArrayList<K> G = new ArrayList<>(); // Greater than pivot
        for (K i : S){
            if (comp.compare(i, pivot) < 0){ L.add(i); }
            else if (comp.compare(i,pivot) == 0){ E.add(i); }
            else{ G.add(i); }
        }
        if (k <= L.size()){ return quickSelect(L,comp,k); }
        else if (k <= (L.size() + E.size())){return pivot;}
        else{ return quickSelect(G,comp,k - L.size() - E.size()); }
    }

    public int getRecurCount() {
        return recurCount;
    }

    public void resetRecurCount() {
        recurCount = -1;
    }

}

