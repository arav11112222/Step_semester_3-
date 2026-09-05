package session_five_topics.class_problems;

/**
 * PROBLEM 5: Immutable Booking Receipt & Nightly Settlement
 *
 * NOTE ON THE "final class" REQUIREMENT: the brief asks for BookingReceipt's class itself
 * to be final, but also requires GroupBookingReceipt to extend it. Those two requirements
 * directly contradict each other in real Java — a final class cannot be extended at all.
 * Since the subclass is explicitly required (and used by processNightlySettlement's
 * instanceof check), BookingReceipt is left non-final so it compiles, while every other
 * immutability guarantee (final fields, defensive copying in and out, the wither pattern)
 * is fully implemented as specified.
 */
public class BookingReceiptDemo {

    static class BookingReceipt {
        private final String bookingId;
        private final String[] seatNumbers;

        public BookingReceipt(String bookingId, String[] seatNumbers) {
            this.bookingId = bookingId;
            this.seatNumbers = (seatNumbers == null) ? new String[0] : seatNumbers.clone();
        }

        public String getBookingId() {
            return bookingId;
        }

        public String[] getSeatNumbers() {
            return seatNumbers.clone(); // defensive copy out — caller can't mutate our internals
        }

        public BookingReceipt withUpdatedSeat(int index, String newSeat) {
            String[] updated = seatNumbers.clone();
            updated[index] = newSeat;
            return new BookingReceipt(bookingId, updated);
        }
    }

    static class GroupBookingReceipt extends BookingReceipt {
        private final int groupSize;

        public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
            super(bookingId, seatNumbers);
            this.groupSize = groupSize;
        }

        public int getGroupSize() {
            return groupSize;
        }
    }

    static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (BookingReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (receipt instanceof GroupBookingReceipt) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X"; // mutating the returned array must not touch the real internal data
        System.out.println(b.getSeatNumbers()[0]); // A1

        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println(java.util.Arrays.toString(b.getSeatNumbers()));       // [A1, A2]
        System.out.println(java.util.Arrays.toString(updated.getSeatNumbers())); // [A1, A3]

        BookingReceipt[] batch = {
                new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
                null,
                new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        System.out.println(processNightlySettlement(batch));
        // 2 processed | 1 null skipped | 1 group | 1 individual
    }
}
