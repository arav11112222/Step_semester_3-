package session_five_topics.class_problems;

/**
 * PROBLEM 3: Seat Booking Encapsulation Guard
 * seatsAvailable can only ever move through bookSeat()/cancelBooking(); both silently
 * reject a transition that would push the count out of bounds. Construction with a
 * non-positive seatsTotal is rejected outright.
 */
public class CineScreenDemo {

    static class CineScreen {
        private int seatsTotal;
        private int seatsAvailable;

        CineScreen(int seatsTotal) {
            if (seatsTotal <= 0) {
                throw new IllegalArgumentException("construction rejected");
            }
            this.seatsTotal = seatsTotal;
            this.seatsAvailable = seatsTotal;
        }

        void bookSeat() {
            if (seatsAvailable > 0) {
                seatsAvailable--;
            }
            // else: silently rejected, nothing left to book
        }

        void cancelBooking() {
            if (seatsAvailable < seatsTotal) {
                seatsAvailable++;
            }
            // else: silently rejected, already back to full
        }

        int getSeatsAvailable() {
            return seatsAvailable;
        }
    }

    public static void main(String[] args) {
        try {
            new CineScreen(0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // construction rejected
        }

        CineScreen c = new CineScreen(2);
        c.bookSeat();
        c.bookSeat();
        c.bookSeat(); // 3rd booking rejected, no seat left
        System.out.println(c.getSeatsAvailable()); // 0

        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking(); // 3rd cancel rejected, already full
        System.out.println(c.getSeatsAvailable()); // 2
    }
}
