import java.util.*;
//https://leetcode.com/discuss/interview-question/344650/Amazon-Online-Assessment-Questions
public class LargestItemAssociation {

    class PairString{
        String first;
        String second;
        PairString(String first, String second){
            this.first = first;
            this.second = second;
        }
    }

    public List<String> largestItemAssociation(List<PairString> itemAssociation) {
        if (itemAssociation == null || itemAssociation.size() == 0) {
            return new ArrayList<>();
        }
        Collections.sort(itemAssociation, new Comparator<PairString>() {
            @Override
            public int compare(PairString a1, PairString a2) {
                return a1.first.compareTo(a2.first);
            }
        });
        Map<String, Set<String>> map = new HashMap<>();
        for (PairString pair : itemAssociation) {
            Set<String> set = map.getOrDefault(pair.first, new TreeSet<>());
            set.add(pair.first);
            set.add(pair.second);
            set.addAll(map.getOrDefault(pair.second, new TreeSet<>()));
            map.put(pair.first, set);
            map.put(pair.second, set);
        }
        Set<String> checked = new HashSet<>();
        PriorityQueue<List<String>> pq = new PriorityQueue<>((l1, l2)
                -> (l1.size() != l2.size() ? l2.size() - l1.size() : l1.get(0).compareTo(l2.get(0))));
        for (String item : map.keySet()) {
            if (checked.contains(item)) {
                continue;
            }
            pq.add(new ArrayList<>(map.get(item)));
            checked.add(item);
        }
        return pq.remove();
    }




    public static void main(String[] args) {
        LargestItemAssociation s = new LargestItemAssociation();
        /**
         * Example 1
         */
        List<PairString> input = Arrays.asList(
                new PairString[]{
                        s.new PairString("item1", "item2"),
                        s.new PairString("item3", "item4"),
                        s.new PairString("item4", "item5")
                }
        );
        /**
         * Testing equal sized arraylist. 1->2->3->7 4->5->6->7
         */
        List<PairString> input2 =  Arrays.asList(
                new PairString[] {
                        s.new PairString("item1","item2"),
                        s.new PairString("item2","item3"),
                        s.new PairString("item4","item5"),
                        s.new PairString("item6","item7"),
                        s.new PairString("item5","item6"),
                        s.new PairString("item3","item7")
                }
        );
        List<String> lst = s.largestItemAssociation(input);
        for (String sa : lst) System.out.print(" " + sa);
        System.out.println();
        List<String> lst2 = s.largestItemAssociation(input2);
        for (String sa : lst2) System.out.print(" " + sa);
        System.out.println();
        /**
         * Testing duplicates: 1->2->3->7 , 5->6
         */
        List<PairString> input3 =  Arrays.asList(
                new PairString[] {
                        s.new PairString("item1","item2"),
                        s.new PairString("item1","item3"),
                        s.new PairString("item2","item7"),
                        s.new PairString("item3","item7"),
                        s.new PairString("item5","item6"),
                        s.new PairString("item3","item7")
                }
        );

        List<String> lst3 = s.largestItemAssociation(input3);
        for (String sa : lst3) System.out.print(" " + sa);
    }


}
