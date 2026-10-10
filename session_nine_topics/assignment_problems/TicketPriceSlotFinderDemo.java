package session_nine_topics.assignment_problems;

/**
 * Problem 5: Ticket Price Slot Finder
 * Normal binary search. If the price is never found, the loop ends with `low` sitting
 * exactly at the index where the price belongs (everything before it is smaller).
 *
 * Linear scan:   Time O(n)      Space O(1)
 * Binary search: Time O(log n)  Space O(1)
 */
public class TicketPriceSlotFinderDemo {

    static int findSlot(int[] prices, int newPrice) {
        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] == newPrice) {
                return mid;
            } else if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] prices = {120, 150, 200, 260};

        System.out.println(findSlot(prices, 150)); // 1
        System.out.println(findSlot(prices, 210)); // 3
        System.out.println(findSlot(prices, 300)); // 4
    }
}
