/*  Java Class: AbstractMatch.java
    Author: Jin Choi
    Class: CSCI 230
    Date: May 9th, 2018
    Description: Abstract Class that holds the data for the two matching schemes.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public abstract class AbstractMatch {

    protected int comparisons;
    protected double runTime;
    private String type;
    protected String pattern;

    public AbstractMatch(String type){
        comparisons = 0;
        runTime = 0;
        this.type = type;
    }

    public int getComparisons() {
        return comparisons;
    }

    public double getRunTime() {return runTime/1000000.0; }

    public void resetComp() { comparisons = 0; }

    public void resetRunTime() {runTime = 0; }

    public String generateReport(int index, int length){
        String result;
        double avg = 0.0;
        if (index == -1) { avg = comparisons/(double)length; }
        else { avg = comparisons/(double)index;}
        result = "Pattern: " + pattern + "\nAlgorithm: " + type + "\nRun Time: " + runTime/1000000.0 + "ms" + "\nTotal Comparisons: " + comparisons + "\nAverage comparisons: " + avg;
        if (index == -1){ result = result + "\nMatch not found"; }
        else { result = result + "\nMatch found at index: " + index; }
        result = result + "\n";
        resetComp();
        resetRunTime();
        return result;
    }

    public abstract int find(String pattern, String text);
}
