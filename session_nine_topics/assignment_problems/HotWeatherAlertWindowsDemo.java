package session_nine_topics.assignment_problems;

/**
 * Problem 4: Hot Weather Alert Windows
 * Sliding window: keep the sum of the current k-hour block, and when it slides forward add
 * the new reading and subtract the one that left. Compare sum >= k * threshold so there is
 * no decimal division.
 *
 * Recalculating every block from scratch: O(n * k) time
 * Sliding window:                         O(n) time, O(1) additional space
 */
public class HotWeatherAlertWindowsDemo {

    static int countAlerts(int[] readings, int k, int threshold) {
        long target = (long) k * threshold;
        long windowSum = 0;
        int alerts = 0;

        for (int i = 0; i < readings.length; i++) {
            windowSum += readings[i];

            if (i >= k) {
                windowSum -= readings[i - k];
            }
            if (i >= k - 1 && windowSum >= target) {
                alerts++;
            }
        }
        return alerts;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        System.out.println(countAlerts(readings, 3, 4)); // 3
    }
}
