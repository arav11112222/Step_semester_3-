package session_nine_topics.class_problems;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem 3: Pair With Target Sum (Unsorted)
 *
 * Approach 1 - Brute force: check every pair (i, j) with i < j.
 *   Time: O(n^2)   Space: O(1)
 * Approach 2 - Hash set of seen values: for each number, look up its complement.
 *   Time: O(n)     Space: O(n)
 *
 * Trade-off: the hash set buys speed by spending memory.
 */
public class PairWithTargetSumDemo {

    static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(target - num)) {
                return true; // the complement came from an earlier index, so the two are distinct elements
            }
            seen.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int[] b = {3, 4, 6};

        System.out.println(hasPairWithSum(a, 9));            // true
        System.out.println(hasPairWithSum(b, 20));           // false
        System.out.println(hasPairWithSumBruteForce(a, 9));  // true
        System.out.println(hasPairWithSumBruteForce(b, 20)); // false
    }
}
