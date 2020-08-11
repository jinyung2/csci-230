public class KnapSack {

    public static int knapsack(int W, int[] w, int[] v, int n){
        int[][] N = new int[n+1][W+1];
        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= W; j++){
                if (i == 0 || j == 0){
                    N[i][j] = 0;
                }else if (w[i-1] <= W){
                    N[i][j] = Math.max(v[i-1] + N[i-1][W-w[i-1]],N[i-1][W]);
                }else{
                    N[i][j] = 0 ;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] weight = {10,20,30};
        int[] val = {60,100,120};
        int max = 50;
        System.out.println(knapsack(max,weight,val,val.length));
    }
}
