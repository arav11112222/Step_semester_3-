package session_nine_topics.class_problems;

import java.util.Arrays;

/**
 * Problem 4: Pair With Target Sum (Unsorted Array)
 * Same question as Problem 3, so this file shows two different approaches.
 *
 * Approach 1 - Brute force over all pairs.
 *   Time: O(n^2)         Space: O(1)
 * Approach 2 - Sort a copy, then two pointers moving inward.
 *   Time: O(n log n)     Space: O(n) for the copy (O(1) if sorting in place is allowed)
 *
 * (The hash-set approach in Problem 3 is O(n) time / O(n) space and is the fastest.)
 */
public class PairWithTargetSumArrayDemo {

    static boolean bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean sortAndTwoPointers(int[] nums, int target) {
        int[] sorted = nums.clone(); // keep the caller's array untouched
        Arrays.sort(sorted);

        int left = 0;
        int right = sorted.length - 1;

        while (left < right) {
            int sum = sorted[left] + sorted[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int[] b = {3, 4, 6};

        System.out.println(bruteForce(a, 9));          // true
        System.out.println(bruteForce(b, 20));         // false
        System.out.println(sortAndTwoPointers(a, 9));  // true
        System.out.println(sortAndTwoPointers(b, 20)); // false
    }
}
