import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/discuss/interview-question/344650/Amazon-Online-Assessment-Questions
public class UtilizationCheck {

    private static final int LIMIT = 2 * 100_000_000;

    public static void main(String[] args) {
        List<Integer> averageUtil = new ArrayList<>();
//        averageUtil.add(5);
//        averageUtil.add(10);
//        averageUtil.add(80);
        averageUtil.add(25);
        averageUtil.add(23);
        averageUtil.add(1);
        averageUtil.add(2);
        averageUtil.add(3);
        averageUtil.add(4);
        averageUtil.add(5);
        averageUtil.add(6);
        averageUtil.add(7);
        averageUtil.add(8);
        averageUtil.add(9);
        averageUtil.add(10);
        averageUtil.add(76);
        averageUtil.add(80);
        System.out.println(finalInstance(2, averageUtil));//2

        averageUtil.add(24);
        averageUtil.add(25);
        averageUtil.add(5);
        averageUtil.add(6);
        averageUtil.add(7);
        averageUtil.add(10);
        averageUtil.add(80);
        averageUtil.add(5);
        averageUtil.add(12);
        averageUtil.add(16);
        averageUtil.add(34);
        averageUtil.add(27);
        averageUtil.add(17);
        averageUtil.add(50);
        System.out.println(finalInstance(2, averageUtil));//1
    }
    public static int finalInstance(int instances, List<Integer> utilizationUtil) {
        int pastUtil = Integer.MAX_VALUE;
        for (int i = 0; i < utilizationUtil.size(); i++) {
            int util = utilizationUtil.get(i);
            if (pastUtil < 25 && instances > 1) {
                instances = instances / 2 + (instances & 1);
                i += 9;//i += 10;
            } else if (util > 65) {
                int newInstances = instances * 2;
                if (newInstances <= LIMIT) {
                    instances = newInstances;
                    i += 10;
                }
            }
            pastUtil = util;
        }
        return instances;
    }
}
