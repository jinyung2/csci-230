/*  Java Class: SkipListSim.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: Mar 12, 2018
    Description: The class that handles the simulation for the skip list.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Random;

public class SkipListSim {
    private int maxLevel;
    private int[] perLevel;
    private int[] entries;


    public SkipListSim(){
        maxLevel = 0;
        perLevel = new int[20];
        entries = new int[100];
    }

    public void simulate(){
        Random rand = new Random(System.nanoTime());
        for (int i = 0; i < entries.length; i++){
            entries[i] = rand.nextInt();
        }
        for (int i : entries){
            int count = 0;
            while (rand.nextInt(2) == 1){
                perLevel[count]++;
                count++;
            }
            perLevel[count]++;
            maxLevel = Math.max(count,maxLevel);
        }
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int[] getPerLevel(){
        return perLevel;
    }
}