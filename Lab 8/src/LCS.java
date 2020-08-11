/*  Java Class: LCS.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: Apr 30, 2018
    Description: The Longest Common Subsequence (LCS) code given in the book. Accompanying code to reconstruct the LCS from the given matrix.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

import java.util.Scanner;

public class LCS {
    public static int[][] LCS(char[] X, char[] Y){
        int n = X.length;
        int m = Y.length;
        int[][] L = new int[n+1][m+1];
        for(int j = 0; j < n; j++){
            for(int k = 0; k < m; k++){
                if (X[j] == Y[k]){
                    L[j+1][k+1] = L[j][k]+1;
                }
                else{
                    L[j+1][k+1] = Math.max(L[j][k+1], L[j+1][k]);
                }
            }
        }
        return L;
    }

    public static char[] reconstructLCS(char[] X, char[] Y, int[][] L){
        StringBuilder solution = new StringBuilder();
        int j = X.length;
        int k = Y.length;
        while(L[j][k] > 0){
            if (X[j-1] == Y[k-1]){
                solution.append(X[j-1]);
                j--;
                k--;
            }
            else if(L[j-1][k] >= L[j][k-1]){
                j--;
            }
            else{
                k--;
            }
        }
        return solution.reverse().toString().toCharArray();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter String a: ");
        String a = in.next();
        System.out.println("Enter String b: ");
        String b = in.next();
        int[][] test = LCS(a.toCharArray(),b.toCharArray());
        for (int i = 0; i < test.length; i++){
            for (int j = 0; j < test[0].length; j++){
                System.out.print(test[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\nLongest Common Subsequence: ");
        System.out.println(reconstructLCS(a.toCharArray(),b.toCharArray(), test));
    }
}

