public class RodCutting {

    public static void main(String[] args) {

        int[] price = {0,2,5,7,3};
        int target = 5;
        solution( price, target);
    }
    //Dynamic programming
    public static void solution( int[] price, int target){
        int[][] dp = new int[price.length+1][target+1];
        for(int i =1; i< price.length; i++){
            for(int j=1; j< target+1; j++){

                if( i <= j ) {
                    dp[i][j] = Math.max(dp[i-1][j], price[i] + dp[i][j-i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }

            }
        }

        System.out.println("Total Value: "+dp[price.length-1][target]);
        printResult(dp, price, target);
    }

    public static void printResult(int[][] dp, int[] price, int lengthOfRod){
        for(int i=0; i<dp.length;i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }

        for(int n=price.length, l=lengthOfRod; n>0; ){
            if(dp[n][l] != 0 && dp[n][l] != dp[n-1][l]){
                System.out.println("Values are from: "+ n+" meter");
                l = l-n;
            }else{
                n--;
            }
        }
    }
}
