/*  Java Class: KMPMatch.java
    Author: Jin Choi
    Class: CSCI 230
    Date: May 9th, 2018
    Description: Knutt-Morris-Pratt algorithm from the book, modified to output the data required for project.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

/*  Java Class: KMPMatch.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: April 18, 2018
    Description: The book source code for the KMP pattern matching.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class KMPMatch extends AbstractMatch{

    public KMPMatch(){
        super("Knuth-Morris-Pratt");


    }

    public int find(String text, String pattern){
        this.pattern = pattern;
        long startTime = System.nanoTime();
        int result = findinternal(text,pattern);
        long endTime = System.nanoTime();
        runTime = endTime - startTime;
        return result;
    }

    private int findinternal(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        if (m == 0) {
            return 0;                            // trivial search for empty string
        }
        int[] fail = computeFailKMP(pattern);            // computed by private utility
        int j = 0;                                       // index into text
        int k = 0;                                       // index into pattern
        while (j < n) {
            if (text.charAt(j) == pattern.charAt(k)) {       // pattern[0..k] matched thus far
                if (k == m - 1) {
                    return j - m + 1;            // match is complete
                }
                j++;                                         // otherwise, try to extend match
                k++;
            } else if (k > 0)
                k = fail[k-1];                               // reuse suffix of P[0..k-1]
            else { j++; }
            comparisons++;
        }
        return -1;                                       // reached end without match
    }

    private int[] computeFailKMP(String pattern) {
        int m = pattern.length();
        int[] fail = new int[m];                         // by default, all overlaps are zero
        int j = 1;
        int k = 0;
        while (j < m) {                                  // compute fail[j] during this pass, if nonzero
            if (pattern.charAt(j) == pattern.charAt(k)) {                // k + 1 characters match thus far
                fail[j] = k + 1;
                j++;
                k++;
            } else if (k > 0)                              // k follows a matching prefix
                k = fail[k-1];
            else                                           // no match found starting at j
                j++;
        }
        return fail;
    }
}

