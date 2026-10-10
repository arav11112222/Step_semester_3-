package session_nine_topics.assignment_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 3: Most Popular Canteen Order
 * Pass 1: count every item in a HashMap. Pass 2: walk the orders in their ORIGINAL order and
 * pick the first item whose count is the highest, so ties go to the item that appeared first.
 *
 * Hash-based:   Time O(n)    Space O(k), k = number of distinct items
 * Rescan-each:  Time O(n^2)  Space O(1)  (count each item by scanning the whole list again)
 */
public class MostPopularCanteenOrderDemo {

    static String mostPopular(String[] orders) {
        Map<String, Integer> counts = new HashMap<>();
        for (String item : orders) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }

        int maxCount = 0;
        for (int count : counts.values()) {
            maxCount = Math.max(maxCount, count);
        }

        for (String item : orders) {
            if (counts.get(item) == maxCount) {
                return "(\"" + item + "\", " + maxCount + ")";
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(mostPopular(new String[]{"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"}));
        // ("dosa", 3)
        System.out.println(mostPopular(new String[]{"tea", "coffee", "coffee", "tea"}));
        // ("tea", 2)
    }
}
