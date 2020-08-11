/*  Java Class: MatchTester.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: April 18, 2018
    Description: The drive class that tests the three different pattern matching algorithms.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class MatchTester {
    public static void main(String[] args) {
        String T1 = "JOHNABCCOMPSCIFOOD";
        String P1 = "CCOM";
        String T2 = "yeshorsebateryasdfjkl;asdfj;lasdfjk;lasdfjl;asdftter";
        String P2 = "tter";
        String T3 = "findmatrixsolverdifferentialkevin";
        String P3 = "chexmix";

        System.out.println("T1: " + T1);
        System.out.println("P1: " + P1);
        System.out.println("T2: " + T2);
        System.out.println("P2: " + P2);
        System.out.println("T3: " + T3);
        System.out.println("P3: " + P3);

        NaiveMatch brute = new NaiveMatch();
        BMMatch boyerMoore = new BMMatch();
        KMPMatch kmp = new KMPMatch();

        System.out.println("T1 and P1:");
        System.out.println("index of match: " + brute.find(T1,P1) + "\ncomparisons: " + brute.getComparisons());
        System.out.println("index of match: " + boyerMoore.find(T1,P1) + "\ncomparisons: " + boyerMoore.getComparisons());
        System.out.println("index of match: " + kmp.find(T1,P1) + "\ncomparisons: " + kmp.getComparisons());

        System.out.println("\nT2 and P2:");
        System.out.println("index of match: " + brute.find(T2,P2) + "\ncomparisons: " + brute.getComparisons());
        System.out.println("index of match: " + boyerMoore.find(T2,P2) + "\ncomparisons: " + boyerMoore.getComparisons());
        System.out.println("index of match: " + kmp.find(T2,P2) + "\ncomparisons: " + kmp.getComparisons());

        System.out.println("\nT3 and P3:");
        System.out.println("index of match: " + brute.find(T3,P3) + "\ncomparisons: " + brute.getComparisons());
        System.out.println("index of match: " + boyerMoore.find(T3,P3) + "\ncomparisons: " + boyerMoore.getComparisons());
        System.out.println("index of match: " + kmp.find(T3,P3) + "\ncomparisons: " + kmp.getComparisons());

    }
}
