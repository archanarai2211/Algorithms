import java.util.*;
//https://leetcode.com/discuss/interview-question/344650/Amazon-Online-Assessment-Questions
public class ItemsInContainer {

    public static void main(String[] args) {
        test( "|**|*|*", Arrays.asList(1, 1), Arrays.asList(5, 6), Arrays.asList(2, 3));
        test( "*|*|", Arrays.asList(1), Arrays.asList(3), Arrays.asList(0));
        test( "*|*|*|", Arrays.asList(1), Arrays.asList(6), Arrays.asList(2));
    }

    public static void test(String input, List<Integer> start, List<Integer> end, List<Integer> expect) {
        System.out.println(equal(numberOfItems(input, start, end) , expect));
    }

    private static boolean equal(List<Integer> numberOfItems, List<Integer> expect) {
        if(numberOfItems.size() != expect.size()){
            return false;
        }
        for(int i = 0; i< numberOfItems.size(); i++){
            if(numberOfItems.get(i) != expect.get(i)){
                return false;
            }
        }
        return true;
    }

    public static List<Integer> numberOfItems(String s, List<Integer> start, List<Integer> end) {
        NavigableMap<Integer, Integer> treeMap = new TreeMap<>();

        int countSoFar = 0;
        for (int i = 0; i< s.length(); i++) {
            if (s.charAt(i) == '|') {
                treeMap.put(i, countSoFar);
            } else {
                countSoFar++;
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i<start.size(); i++) {
            list.add(number(treeMap, start.get(i) - 1, end.get(i) - 1));
        }
        return list;
    }

    static int number(NavigableMap<Integer, Integer> treemap, int start, int end) {
        if (treemap.floorEntry(end) == null || treemap.ceilingEntry(start) == null)
            return 0;
        int i = treemap.floorEntry(end).getValue() - treemap.ceilingEntry(start).getValue();
        return Math.max(i, 0);
    }
}
