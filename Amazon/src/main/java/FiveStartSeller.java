import java.util.*;

public class FiveStartSeller {
//https://leetcode.com/discuss/interview-question/344650/Amazon-Online-Assessment-Questions
    public static void main(String[] args) {
        List<List<Integer>> ratings = new ArrayList(){
            {
                add(Arrays.asList(4,4));
                add(Arrays.asList(1,2));
                add(Arrays.asList(3,6));
            }
        };
        int threshold = 77;
        System.out.println(fiveStarReviews(ratings, threshold));//3
        ratings = new ArrayList(){
            {
                add(Arrays.asList(4,4));
                add(Arrays.asList(1,2));
                add(Arrays.asList(3,6));
                add(Arrays.asList(3,5));
                add(Arrays.asList(2,7));
                add(Arrays.asList(1,4));
            }
        };
        threshold = 90;
        System.out.println(fiveStarReviews(ratings, threshold));//86

//        int[][] logs = {{4, 4}, {1, 2}, {3, 6}};
//        System.out.println(fiveStarReviews(logs, 77));
//        logs = new int [][]{{4, 4}, {1, 2}, {3, 6},{3, 5}, {2, 7}, {1, 4}};
//        System.out.println(fiveStarReviews(logs, 90));
    }

    static class IndexRating{
        public int index;
        public List<Integer> rating;
        public IndexRating(int index, List<Integer> rating){
            this.index = index;
            this.rating = rating;
        }
    }
    public static int fiveStarReviews(List<List<Integer>> productRatings, int ratingsThreshold){
        int totalProducts = productRatings.size();
        PriorityQueue<IndexRating> maxHeap = new PriorityQueue<>((a, b) -> getMaxRatingDifference(b.rating) - getMaxRatingDifference(a.rating));
        double[] individualPercentages = new double[totalProducts];
        for (List<Integer> review : productRatings) {
            double percentage = (review.get(0)*100.0)/review.get(1);
            percentage = Math.round(percentage * 100.0) / 100.0;
            individualPercentages[productRatings.indexOf(review)] = percentage;
            maxHeap.add(new IndexRating(productRatings.indexOf(review), review));
        }
        double threshold = calculateTotalPercentageThreshold(individualPercentages, totalProducts);
        threshold = Math.round(threshold * 100.0) / 100.0;
       // System.out.println(threshold);
        int newStars = 0;
        while (threshold < ratingsThreshold) {
            IndexRating entry = maxHeap.poll();
            //System.out.println(entry);
            int index = entry.index;
            List<Integer> review = productRatings.get(index);

            review.set(0, review.get(0) + 1);
            review.set(1, review.get(1) + 1);
            double newPercentage = (review.get(0)*100.0)/review.get(1);
            newPercentage = Math.round(newPercentage * 100.0) / 100.0;
            maxHeap.add(new IndexRating(index, review));

            individualPercentages[index] = newPercentage;
            threshold = calculateTotalPercentageThreshold(individualPercentages, totalProducts);
           // System.out.println(threshold);
            newStars++;
        }
        return newStars;
    }

    public static double calculateTotalPercentageThreshold(double[] individualPercentage, int totalProducts){
        double totalPercentage = 0.0;
        for (double v : individualPercentage) {
            totalPercentage = totalPercentage + v;
        }
        totalPercentage = Math.round(totalPercentage * 100.0) / 100.0;
        double threshold = totalPercentage / totalProducts;
        threshold = Math.round(threshold * 100.0) / 100.0;
        return threshold;
    }
    static int getMaxRatingDifference(List<Integer> a){
        double ratingWithAdd1 = (a.get(0)+1)*100.0/(a.get(1)+1);
        double rating = (a.get(0))*100.0/(a.get(1));
        rating = Math.round(rating * 100.0) / 100.0;
        ratingWithAdd1 = Math.round(ratingWithAdd1 * 100.0) / 100.0;
        int diff = (int) (ratingWithAdd1*100 - rating*100);
        return diff;
    }

}
