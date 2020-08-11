/*  Java Class: NaiveMatch.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: April 18, 2018
    Description: The code given in the book for the brute force (naive) pattern match.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class NaiveMatch {

    private int comparisons;

    public int getComparisons() {
        return comparisons;
    }

    public NaiveMatch(){
        comparisons = 0;
    }

    public int find(String text, String pattern){
        int n = text.length();
        int m = pattern.length();
        for (int i = 0; i <= n-m; i++){
            int k = m-1;
            while (k >= 0 && text.charAt(i+k) == pattern.charAt(k)){
                comparisons++;
                k--;
            }
            if (k == -1){
                return i;
            }
        }
        return -1;
    }
}
