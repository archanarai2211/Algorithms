import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Subsets {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        List<List<Integer>> list = new ArrayList<>();
        bt(arr, list);
    }

    private static void bt(int[] arr, List<List<Integer>> list) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(arr[0]);
        int i=1;
        while(q.isEmpty()){


        }
    }

}
