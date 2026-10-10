package session_nine_topics.class_problems;

/**
 * Problem 5: Maximize Area Between Two Boundaries
 *
 * Brute force: try every pair of walls.   Time: O(n^2)   Space: O(1)
 * Two pointers: start at both ends; the area is limited by the SHORTER wall, so moving the
 * taller wall inward can never help (width shrinks, height cap stays the same). Always move
 * the shorter wall.                        Time: O(n)     Space: O(1)
 *
 * For very large inputs (say n = 10^5) the brute force needs about 5 * 10^9 pair checks,
 * while the two-pointer version needs about 10^5 steps.
 */
public class MaximizeAreaBetweenBoundariesDemo {

    static int maxContainerAreaBruteForce(int[] heights) {
        int best = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                best = Math.max(best, Math.min(heights[i], heights[j]) * (j - i));
            }
        }
        return best;
    }

    static int maxContainerArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int best = 0;

        while (left < right) {
            int area = Math.min(heights[left], heights[right]) * (right - left);
            best = Math.max(best, area);

            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(maxContainerArea(heights));           // 49
        System.out.println(maxContainerAreaBruteForce(heights)); // 49
    }
}
