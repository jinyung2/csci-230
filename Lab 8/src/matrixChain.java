/*  Java Class: matrixChain.java
    Author: Jin Choi & Henry Nguyen
    Class: CSCI 230
    Date: Apr 30, 2018
    Description: The book algorithm for the MCP. Used to test and output the matrix formed by the resulting algorithm.

    I certify that the code below is my own work.

	Exception(s): N/A

*/

public class matrixChain {

    public static int[][] matrixChain(int[] d){
        int n = d.length-1;
        int[][] N = new int[n][n];
        for (int b = 1; b < n; b++){
            for (int i = 0; i < n-b; i++){
                int j = i + b;
                N[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++){
                    N[i][j] = Math.min(N[i][j],N[i][k]+N[k+1][j]+d[i]*d[k+1]*d[j+1]);
                }
            }
        }
        return N;
    }

    public static void main(String[] args) {
        int lowest = Integer.MAX_VALUE;
        int[] d = {2,3,4,3};
        System.out.println("Using matrices: 10x40, 40x20, 20x30, 30x10: \n");
        int[][] result = matrixChain(d);
        for (int i = 0; i < result.length; i++){
            for (int j = 0; j < result[0].length; j++){
                if (result[i][j] != 0){
                    lowest = Math.min(lowest,result[i][j]);
                }
                System.out.printf("%-6d ", result[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n" + "Lowest value: " + lowest);
    }
}
