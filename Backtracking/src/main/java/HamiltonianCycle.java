public class HamiltonianCycle {

    private int numOfVertex;
    private int[] hamiltonianPath;
    private int[][] matrix;

    public HamiltonianCycle(int[][] matrix) {
        this.hamiltonianPath = new int[matrix.length];
        this.matrix = matrix;
        this.numOfVertex = matrix.length;
        //First vertex is index 0
        this.hamiltonianPath[0] = 0;

    }

    private void showHamiltonianPath() {
        System.out.println("Hamiltonian Cycle");
        for(int i=0; i<hamiltonianPath.length;i++){
            System.out.print(hamiltonianPath[i]+" ");
        }
        System.out.print(hamiltonianPath[0]);
    }

    private boolean findFeasibleSoln(int pos) {
        if(pos == numOfVertex){
            //if the last vertex in the path is connected to the first vertex in the path
            if(matrix[hamiltonianPath[pos-1]][hamiltonianPath[0]]==1)
                return true;
            else
                return false;
        }
        //first vertex already added
        for(int vi = 1; vi<numOfVertex; vi++){
            if(isFeasible(vi, pos)){
                hamiltonianPath[pos]=vi;
                if(findFeasibleSoln(pos+1)){
                    return true;
                }
                //BACKTRACK

            }
        }
        return false;
    }

    private boolean isFeasible(int vi, int pos) {
        // 1 criteria: whether two nodes are connected
        if(matrix[hamiltonianPath[pos-1]][vi]==0){
            return false;
        }

        //2 criteria: whether it is visited
        for(int i=0; i<pos;i++){
            if(hamiltonianPath[i]==vi){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int [][] matrix = {
                {0,1,1,1,0,0},
                {1,0,1,0,1,0},
                {1,1,1,1,0,1},
                {1,0,1,0,0,1},
                {0,1,0,0,0,1},
                {0,1,1,1,1,1}
        };

//        int [][] matrix = {
//                {0,1,0},
//                {1,0,1},
//                {0,1,0}
//        };

        HamiltonianCycle cycle = new HamiltonianCycle(matrix);
        cycle.solve();
    }

    private void solve() {
        if(findFeasibleSoln(1)){
            showHamiltonianPath();
        }else{
            System.out.println("No Solution!");
        }
    }
}
