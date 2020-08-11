/*  Java Class: SkipListSimTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: Mar 12, 2018
    Description: A testing driver for the Skip List simulation.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class SkipListSimTester {
    public static void main(String[] args) {
        int totalMax = 0;
        int highestMax = 0;
        int[] perLevelAvg = new int[20];
        for (int i = 0; i < 10; i++){
            SkipListSim test = new SkipListSim();
            test.simulate();
            totalMax += test.getMaxLevel();
            highestMax = Math.max(test.getMaxLevel(),highestMax);
            for (int j : test.getPerLevel()){
                for (int k = 0; k < 20; k++){
                    perLevelAvg[k] += test.getPerLevel()[k];
                }
            }
        }
        System.out.println("Average number of levels: " + totalMax/10.0 + "\nHighest max level: " + highestMax);
        for (int i = 0; i < perLevelAvg.length;i++){
            System.out.println("Average number of values at level " + i + " is: " + perLevelAvg[i]/200.0);
        }
    }
}
