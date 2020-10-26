public class Knapsack {



    public static void main(String[] args) {
        int numOfItems =3;
        int capacityOfKnapsack = 5;
        int[] weight = new int[numOfItems+1];
        weight[1]=4;
        weight[2]=2;
        weight[3]=3;

        int[] value = new int[numOfItems+1];
        value[1]=10;
        value[2]=4;
        value[3]=7;

        solve(numOfItems, capacityOfKnapsack, weight, value);

    }



    // time complexity O(N*W) where N is no of Items and W is weight
    // row index: Num of items
    // column index: capacity of knapsack ie weight
    // every cell represent value in $
    // select the max of the notTakingItem ie value of the previous item
    // and taking item ie the sum of current item with the remaining capacity value
    // since each item can be added only once, we check the previous item (i-1)
    // to add the remaining capacity
    public static int solve(int numOfItems, int capacityOfKnapsack, int[] weight, int[] value){
        int[][] dp = new int[numOfItems+1][capacityOfKnapsack+1];
        // for 0 item and 0 weight capacity the value will be 0
        for(int i=1; i<numOfItems+1; i++){
            for(int w=1; w<capacityOfKnapsack+1; w++){
                int notTakingItem = dp[i-1][w];
                int takingItem =0;
                if(weight[i] <= w){
                    takingItem = value[i]+dp[i-1][w-weight[i]];
                }
                dp[i][w]= Math.max(notTakingItem, takingItem);
            }
        }
        System.out.println("Total Benefit: "+ dp[numOfItems][capacityOfKnapsack]);
        for(int n=numOfItems, w=capacityOfKnapsack;n>0;n--)
            if(dp[n][w] != 0 && dp[n][w]!=dp[n-1][w]) {
                System.out.println("We take : " + n);
                w = w-weight[n];
            }
        return dp[numOfItems][capacityOfKnapsack];
    }

}
