public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1,2,3};

        int total = 5;
        System.out.println(recursiveCoinChange(total,coins, 0));
        dynamicCoinChange(total,coins);
    }

    //recursive soln: Exponential running time(2^N) n is length of array ie num of coin
    public static int recursiveCoinChange(int total, int[] coin, int index){
        if(total<0){
            return 0;
        }
        if(total==0){
            return 1;
        }
        //total>0, last index
        if(coin.length==index){
            return 0;
        }
        //recursive calls : sum of include coin and exclude coin
        return recursiveCoinChange(total-coin[index],coin, index ) + recursiveCoinChange(total, coin, index+1);
    }

    //dynamic programming soln
    //No of Columns is the total amount +1(for 0 as well)
    //No of rows is num of coins+1 including 0
    //Each cell represent the possible solutions

    public static void dynamicCoinChange(int total, int[] coins){
        int[][] dp = new int[coins.length+1][total+1];

        //total of 0 will have only one choice, not to include any coin
        for(int i=0; i< coins.length+1; i++){
            dp[i][0]=1;
        }

        //zero coins will give us no choice
        for(int i=1; i< total+1; i++){
            dp[0][i]=0;
        }

        //total of 0 will have only one choice, not to include any coin
        for(int i=1; i< coins.length+1; i++){
           for(int j=1; j< total+1; j++){
               //If the total sum is more than the coin value
               //The number of possible solution would be
               // sum of previous coin solns and (current coin combination with combination of remaining values)
               if(coins[i-1]<=j){
                   dp[i][j]=dp[i-1][j] + dp[i][j-coins[i-1]];
               }else{
                   //If the total sum is lesser than the coin value
                   // just use the same number of solutions that was calculated for the previous coin
                   dp[i][j]=dp[i-1][j];
               }
           }
        }

        System.out.println("Total possible solutions: "+ dp[coins.length][total]);
    }

}
