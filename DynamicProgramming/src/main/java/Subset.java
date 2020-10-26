public class Subset {

    public static void main(String[] args) {
        int[] num = {5,2,1,3};
        int sum = 9;
        System.out.println("Result: "+subset(num, sum));
    }
    public static boolean subset(int[] num, int sum){
        boolean[][] dp = new boolean[num.length+1][sum+1];
        //By default first row will be set to false
        for(int i=0; i<=num.length; i++){
            dp[i][0]=true;
        }
        for(int i=1; i<=num.length; i++){
            for(int j=1; j<=sum; j++){
                if(j<num[i-1]){
                    dp[i][j]=dp[i-1][j];
                }else{
                    if(dp[i-1][j]){
                        dp[i][j]=true;
                    }else
                        dp[i][j]=dp[i-1][j-num[i-1]];
                }
            }
        }
        printResult(dp, num, sum);
        return dp[num.length][sum];
    }

    private static void printResult(boolean[][] dp, int[] num, int sum) {
        int r = num.length;//row
        int c = sum;//column
        while (c>0 || r>0){
            if(dp[r][c]!=dp[r-1][c]){
                System.out.println("We consider: "+num[r-1]);
                c=c- num[r-1];
            }
            r--;
        }
    }
}
