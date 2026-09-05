package session_five_topics.assignment_problems;

/**
 * PROBLEM 3: Book Copy Circulation Guard
 * copiesAvailable can only move through checkOut()/checkIn(); both silently reject
 * a transition that would push the count out of bounds (below 0 or above copiesTotal).
 */
public class BookInventoryDemo {

    static class BookInventory {
        private int copiesTotal;
        private int copiesAvailable;

        BookInventory(int copiesTotal) {
            this.copiesTotal = copiesTotal;
            this.copiesAvailable = copiesTotal;
        }

        void checkOut() {
            if (copiesAvailable > 0) {
                copiesAvailable--;
            }
            // else: silently rejected, nothing left to check out
        }

        void checkIn() {
            if (copiesAvailable < copiesTotal) {
                copiesAvailable++;
            }
            // else: silently rejected, already at full capacity
        }

        int getCopiesAvailable() {
            return copiesAvailable;
        }
    }

    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut(); // 4th attempt, silently rejected
        System.out.println(b.getCopiesAvailable()); // 0

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn(); // 4th attempt, silently rejected — already back to full
        System.out.println(b.getCopiesAvailable()); // 3
    }
}
