/*  Java Class: ProbeHashmap.java
    Author: Jin Choi
    Class: CSCI 230
    Date: Mar 20, 2018
    Description: The probe hash map provided from the book, edited to implement the methods and results required for project.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;

/*
 * Map implementation using hash table with linear probing.
 *
 * @author Eric Zamore
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class ProbeHashMap<K,V> extends AbstractHashMap<K,V> {
  private MapEntry<K,V>[] table;        // a fixed array of entries (all initially null)
  private MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null);   //sentinel

  // provide same constructors as base class
  /** Creates a hash table with capacity 17 and prime factor 109345121. */
  public ProbeHashMap() { super(); }

  /** Creates a hash table with given capacity and prime factor 109345121. */
  public ProbeHashMap(int cap) { super(cap); totalCluster = -1; }

  /** Creates a hash table with the given capacity and prime factor. */
  public ProbeHashMap(int cap, int p) { super(cap, p); }

  /** Creates an empty table having length equal to current capacity. */
  @Override
  @SuppressWarnings({"unchecked"})
  protected void createTable() {
    table = (MapEntry<K,V>[]) new MapEntry[capacity];   // safe cast
  }

  /** Returns true if location is either empty or the "defunct" sentinel. */
  private boolean isAvailable(int j) {
    return (table[j] == null || table[j] == DEFUNCT);
  }

  /**
   * Searches for an entry with key equal to k (which is known to have
   * hash value h), returning the index at which it was found, or
   * returning -(a+1) where a is the index of the first empty or
   * available slot that can be used to store a new such entry.
   *
   * @param h the precalculated hash value of the given key
   * @param k the key
   * @return index of found entry or if not found, value -(a+1) where a is index of first available slot
   */

    private int findSlot(int h, K k) {
        int avail = -1;                               // no slot available (thus far)
        int j = h;                                   // index while scanning table
        int currProbe = 0;
        int count = 0;
        do {
            count++;
            currProbe++;
            totalProbe++;
            if (isAvailable(j)) {                       // may be either empty or defunct
                if (avail == -1) avail = j;               // this is the first available slot!
                if (table[j] == null) {
                    break;                              // if empty, search fails immediately
                }
            } else if (table[j].getKey().equals(k)) {
                maxProbe = Math.max(currProbe, maxProbe);
                if(count > 1){ numCluster++; }
                totalProbe += currProbe;
                maxCluster = Math.max(maxCluster, currProbe-1);
                return j;                                 // successful match
            }
            j = (j+1) % capacity;                       // keep looking (cyclically)
        } while (j != h);                             // stop if we return to the start
        maxProbe = Math.max(currProbe, maxProbe);
        if(count > 1){ numCluster++; }
        totalProbe += currProbe;
        totalCluster += currProbe-1;
        maxCluster = Math.max(maxCluster, currProbe-1);
        return -(avail + 1);                          // search has failed
    }

  /**
   * Returns value associated with key k in bucket with hash value h.
   * If no such entry exists, returns null.
   * @param h  the hash value of the relevant bucket
   * @param k  the key of interest
   * @return   associate value (or null, if no such entry)
   */
  @Override
  protected V bucketGet(int h, K k) {
    int j = findSlot(h, k);
    if (j < 0) return null;                   // no match found
    return table[j].getValue();
  }

  /**
   * Associates key k with value v in bucket with hash value h, returning
   * the previously associated value, if any.
   * @param h  the hash value of the relevant bucket
   * @param k  the key of interest
   * @param v  the value to be associated
   * @return   previous value associated with k (or null, if no such entry)
   */
  @Override
  protected V bucketPut(int h, K k, V v) {
      numProbe++;
      int j = findSlot(h, k);
      if (j >= 0)                               // this key has an existing entry
          return table[j].setValue(v);          // this will replace the current value at that key (keys are unique, no duplicates).
      table[-(j+1)] = new MapEntry<>(k, v);     // convert to proper index
      n++;
      return null;
  }

  /**
   * Removes entry having key k from bucket with hash value h, returning
   * the previously associated value, if found.
   * @param h  the hash value of the relevant bucket
   * @param k  the key of interest
   * @return   previous value associated with k (or null, if no such entry)
   */
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
        avg = ((double)totalCluster)/numCluster;
        return avg;
  }

  @Override
  protected int getLargestCluster() {
    return maxCluster;
  }

    @Override
    protected void generateReport() {
        System.out.println("Probe Hash Map: ");
        System.out.println("Table size is: " + reportTableSize());
        System.out.println("Average number of probes: " + getAverageProbes());
        System.out.println("Maximum number of probes for worst case: " + getMaxProbesWorstCase());
        System.out.println("Number of clusters: " + getNumCluster());
        System.out.println("Average cluster size: " + getAverageCluster());
        System.out.println("Largest cluster size: " + getLargestCluster());
    }

    @Override
    protected void setNumProbe(Integer n) {
        numProbe = n;
    }

    /**
   * Returns an iterable collection of all key-value entries of the map.
   *
   * @return iterable collection of the map's entries
   */
  @Override
  public Iterable<Entry<K,V>> entrySet() {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>();
    for (int h=0; h < capacity; h++)
      if (!isAvailable(h)) buffer.add(table[h]);
    return buffer;
  }
}
