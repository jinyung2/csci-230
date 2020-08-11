/*  Java Class: DoubleHashMap.java
    Author: Jin Choi
    Class: CSCI 230
    Date: Mar 20, 2018
    Description: A hashing scheme for doublehashmap based off of probehashmap. Utilizes the double hashing function outlined in the book.

    I certify that the code below is my own work.

	Exception(s): Lots of recycled code from ProbeHashMap.java

*/

import java.util.ArrayList;

public class DoubleHashMap<K,V> extends AbstractHashMap<K,V> {
    private MapEntry<K,V>[] table;
    private MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null);

    public DoubleHashMap(){ super(); }
    public DoubleHashMap(int cap){ super(cap); }
    public DoubleHashMap(int cap, int p){super(cap, p); }

    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() { table = (MapEntry<K,V>[]) new MapEntry[capacity]; }

    private boolean isAvailable(int j) { return (table[j] == null || table[j] == DEFUNCT); }

    private int findSlot(int h, K k) {
        int avail = -1;                               // no slot available (thus far)
        int j = h;                                   // index while scanning table
        int currProbe = 0;
        int count = 0;
            for(int i = 0; i < capacity;i++){
                currProbe++;
                count++;
                totalProbe++;
                if (isAvailable(j)) {                       // may be either empty or defunct
                    if (avail == -1) avail = j;               // this is the first available slot!
                    if (table[j] == null) {
                        break;                               // if empty, search fails immediately
                    }
                } else if (table[j].getKey().equals(k)) {
                    maxProbe = Math.max(currProbe, maxProbe);
                    totalProbe += currProbe;
                    return j;                                 // successful match
                }
                int f = hashTwo(k, 5, i);                       // keep looking
                j = (j+f)%capacity;
            }
        maxProbe = Math.max(currProbe, maxProbe);
        totalProbe += currProbe;
        return -(avail + 1);                          // search has failed
    }

    private int hashTwo(K key, int prime, int i){return i*(prime - (key.hashCode()%prime));}

    /** clusterData method that computes cluster data for double hashing after elements have been inputted. */
    private void clusterData(){
        int i = 0;
        do{
            int currCluster = 1;
            if (table[i] == null){
                i++;
            }else{
                for(int j = i+1; j < table.length; j++){
                    if (j == table.length-1){
                        if (table[j]!= null){ currCluster++; }
                        i=j;
                        break;
                    }
                    if (table[j] != null) {
                        currCluster++;
                    }
                    else {i = j; break;}
                }

            }
            if (currCluster > 1){ totalCluster += currCluster;}
            maxCluster = Math.max(maxCluster,currCluster);
            if(currCluster > 1) { numCluster++; }
        }while(i < table.length-1);
    }

    protected V bucketGet(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) return null;                   // no match found
        return table[j].getValue();
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        numProbe++;
        int j = findSlot(h, k);
        if (j >= 0)                               // this key has an existing entry
            return table[j].setValue(v);
        table[-(j+1)] = new MapEntry<>(k, v);     // convert to proper index
        n++;
        return null;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0) return null;                   // nothing to remove
        V answer = table[j].getValue();
        table[j] = DEFUNCT;                       // mark this slot as deactivated
        n--;
        return answer;
    }

    @Override
    protected int reportTableSize() {
        return capacity;
    }

    @Override
    protected double getAverageProbes() {
        double avg = (double)totalProbe/numProbe;
        return avg;
    }

    @Override
    protected int getMaxProbesWorstCase() {
        return maxProbe;
    }

    @Override
    protected int getNumCluster() { return numCluster; }

    @Override
    protected double getAverageCluster() {
        double avg = 0.0;
        if (numCluster == 0){
            return 0.0;
        }
        avg = ((double)totalCluster)/((double)numCluster);
        return avg;
    }

    @Override
    protected int getLargestCluster() {
        return maxCluster;
    }

    @Override
    protected void generateReport() {
        System.out.println("Double Hash Map: ");
        System.out.println("Table size is: " + reportTableSize());
        System.out.println("Average number of probes: " + getAverageProbes());
        System.out.println("Maximum number of probes for worst case: " + getMaxProbesWorstCase());
        clusterData();
        System.out.println("Number of clusters: " + getNumCluster());
        System.out.println("Average cluster size: " + getAverageCluster());
        System.out.println("Largest cluster size: " + getLargestCluster());
    }

    @Override
    protected void setNumProbe(Integer n) {
        numProbe = n;
    }


    @Override
    public Iterable<Entry<K,V>> entrySet() {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        for (int h=0; h < capacity; h++)
            if (!isAvailable(h)) buffer.add(table[h]);
        return buffer;
    }
}
