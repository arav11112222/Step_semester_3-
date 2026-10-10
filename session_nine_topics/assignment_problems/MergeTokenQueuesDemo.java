package session_nine_topics.assignment_problems;

import java.util.Arrays;

/**
 * Problem 2: Merging Two Token Queues
 * Two pointers, one per sorted list: copy the smaller current value and advance that
 * pointer; when one list runs out, copy the rest of the other. Duplicates are kept
 * because on a tie we simply take one of them first and the other next.
 *
 * Time: O(m + n)   Space: O(m + n) for the output list
 * Join-then-sort alternative: O((m + n) log(m + n)) time, because it ignores the fact
 * that both inputs are already sorted.
 */
public class MergeTokenQueuesDemo {

    static int[] mergeTokens(int[] counterA, int[] counterB) {
        int[] merged = new int[counterA.length + counterB.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                merged[k++] = counterA[i++];
            } else {
                merged[k++] = counterB[j++];
            }
        }
        while (i < counterA.length) {
            merged[k++] = counterA[i++];
        }
        while (j < counterB.length) {
            merged[k++] = counterB[j++];
        }
        return merged;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                mergeTokens(new int[]{3, 8, 15, 20}, new int[]{5, 8, 12}))); // [3, 5, 8, 8, 12, 15, 20]
        System.out.println(Arrays.toString(
                mergeTokens(new int[]{}, new int[]{4, 9})));                 // [4, 9]
    }
}
