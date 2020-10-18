public class NQueen {

    //keep these no same
    private static int[][] chessTable = new int[4][4];
    private static int numOfQueens = 4;

    public static void main(String[] args) {
        if(queen(0)){
            System.out.println("Done");
        }else{
            System.out.println("Not Possible");
        }
        printAll();
    }

    private static boolean queen(int c) {
        if(c==numOfQueens){
            return true;
        }
        for(int r = 0; r<numOfQueens; r++){
            if(isValidPlace(r, c)){
                chessTable[r][c] = 1;
                if(queen(c+1)){
                    return true;
                }
                //bactrack
                chessTable[r][c] = 0;
            }
        }

        return false;
    }

    private static boolean isValidPlace(int r, int c) {
        //check same row prev cols
        for(int i=0; i<c; i++){
            if(chessTable[r][i]==1){
                return false;
            }
        }

        //check diagonally
        for(int i=c, j=r; i>=0 && j>=0; i--,j--){
            if(chessTable[j][i]==1){
                return false;
            }
        }
        //check diagonally opp direction
        for(int i=c, j=r; i>=0 && j<chessTable.length; i--,j++){
            if(chessTable[j][i]==1){
                return false;
            }
        }

        return true;
    }

    private static void printAll(){
        for(int i = 0; i< chessTable.length; i++){
            for(int j = 0; j<chessTable.length; j++){
                if(chessTable[i][j]==1){
                    System.out.print(" * ");
                }else{
                    System.out.print(" - ");
                }
            }
            System.out.println("");
        }
    }
}
