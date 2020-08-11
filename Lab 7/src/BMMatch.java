/*  Java Class: BMMatch.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: April 18, 2018
    Description: The book source code for the boyer moore pattern matching.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.HashMap;
import java.util.Map;

public class BMMatch {

    private int comparisons;

    public BMMatch(){comparisons = 0;}

    public int getComparisons() {
        return comparisons;
    }

    public int find(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        if (m == 0) return 0;                            // trivial search for empty string
        Map<Character,Integer> last = new HashMap<>();   // the 'last' map
        for (int i=0; i < n; i++)
            last.put(text.charAt(i), -1);               // set -1 as default for all text characters
        for (int k=0; k < m; k++)
            last.put(pattern.charAt(k), k);             // rightmost occurrence in pattern is last
        // start with the end of the pattern aligned at index m-1 of the text
        int i = m-1;                                     // an index into the text
        int k = m-1;                                     // an index into the pattern
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(k)) {                  // a matching character
                comparisons++;
                if (k == 0) return i;                        // entire pattern has been found
                i--;                                         // otherwise, examine previous
                k--;                                         // characters of text/pattern
            } else {
                i += m - Math.min(k, 1 + last.get(text.charAt(i))); // case analysis for jump step
                k = m - 1;                                   // restart at end of pattern
            }
        }
        return -1;                                       // pattern was never found
    }

}
