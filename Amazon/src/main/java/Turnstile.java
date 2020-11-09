import java.util.*;

public class Turnstile {
//https://leetcode.com/discuss/interview-question/344650/Amazon-Online-Assessment-Questions
    public static void main(String[] args) {
        int numCustomers = 5;
        int[] arrTime = {0, 1, 1, 3, 3};
        int[] direction = {0, 1, 0, 0, 1};
        int[] expected = {0, 2, 1, 4, 3};
        test(numCustomers, arrTime, direction, expected);

        numCustomers = 4;
        arrTime = new int[]{0, 0, 1,5};
        direction = new int[]{0, 1, 1, 0};
        expected = new int[]{2,0,1,5};
        test(numCustomers, arrTime, direction, expected);

        numCustomers = 5;
        arrTime = new int[]{0, 1, 1,4, 4};
        direction = new int[]{1, 0, 1, 0, 0};
        expected = new int[]{0,2,1,4,5};
        test(numCustomers, arrTime, direction, expected);

        numCustomers = 5;
        arrTime = new int[]{ 1,3,3 ,4, 5};
        direction = new int[]{1, 0, 1, 1,1};
        expected = new int[]{1,6,3,4,5};
        test(numCustomers, arrTime, direction, expected);

        numCustomers = 5;
        arrTime = new int[]{0, 0, 1,3,5};
        direction = new int[]{1, 0, 1, 0, 1};
        expected = new int[]{0,2,1,3,5};
        test(numCustomers, arrTime, direction, expected);
    }
    private static boolean equal(int[] result, int[] expect) {
        if(result.length != expect.length){
            return false;
        }
        for(int i = 0; i< result.length; i++){
            if(result[i] != expect[i]){
                return false;
            }
        }
        return true;
    }
    private static void test(int numCustomers, int[] arrTime, int[] direction, int[] expected) {
        System.out.println(equal(getTimes(numCustomers, arrTime, direction), expected));
    }

    public static int[] getTimes(int numberOfCustomers, int[] timeArr, int[] direction){
        int[] output = new int[numberOfCustomers];
        int i=0, j=1, winningIndex = -1;
        boolean isExit = true, gap = false;
        Integer lastTimeUsed = null;
        int len = timeArr.length;
        if(numberOfCustomers == 0 || len == 0 || direction.length == 0)
            return new int[numberOfCustomers];
        if(numberOfCustomers != len || numberOfCustomers != direction.length)
            return new int[numberOfCustomers];
        while(i< len && j<len){
            //case 1 : both the times are not equal
            if( timeArr[i] != timeArr[j] ){
                winningIndex = i;
            }
            //case 2: Both arrive at same time and both want to either exit or either enter
            else if(timeArr[i] == timeArr[j] && direction[i] == direction[j] ){
                winningIndex = i;
                timeArr[j] = timeArr[j] + 1;
            }
            //case 3: Both arrive at same time with opposite directions
            else {
                //find exit and entry index
                int exitIndex = direction[i] == 1?i:j;
                int entryIndex = direction[i] == 0?i:j;
                //case 3.1: If lastTimeUsed is more than 1 sec or never used then exit guy wins
                if(lastTimeUsed == null || (timeArr[i] - Math.abs(lastTimeUsed))>1 ||
                        //case 3.2: If lastTimeUsed is previous second and last time used was an exit then current exit guy wins
                        ( ((timeArr[i] - Math.abs(lastTimeUsed))==1) && isExit ) )
                {
                    winningIndex = exitIndex;
                    timeArr[entryIndex] = timeArr[entryIndex] + 1;
                }
                //case 3.3: If lastTimeUsed is previous second and last time used was an entry then current entry guy wins
                else if(((timeArr[i] - Math.abs(lastTimeUsed))==1) && !isExit){
                    winningIndex = entryIndex;
                    timeArr[exitIndex] = timeArr[exitIndex] + 1;
                }
            }
            output[winningIndex] = timeArr[winningIndex];
            lastTimeUsed = timeArr[winningIndex];
            isExit = direction[winningIndex] == 1? true:false;
            if(i == winningIndex)
            {
                if(!gap) {
                    i++;
                    j++;
                }
                else{
                    i = j++;
                    j = i+1;
                }
                gap = false;
            }
            else {
                j++;
                gap = true;  //to keep track of i, if only j moved.
            }
        }
        output[i] = timeArr[i];
        return output;
    }
}
