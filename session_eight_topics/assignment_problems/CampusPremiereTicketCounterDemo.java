package session_eight_topics.assignment_problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Q3: The Campus Premiere Ticket Counter
 * Seat is abstract with its own getPrice(), so a new category (e.g. VIP) plugs in
 * without touching booking logic. Show privately owns the set of booked seat IDs —
 * cancelling a booking releases them all back to the show.
 */
public class CampusPremiereTicketCounterDemo {

    static abstract class Seat {
        String seatId;

        Seat(String seatId) {
            this.seatId = seatId;
        }

        abstract double getPrice();
    }

    static class RegularSeat extends Seat {
        RegularSeat(String seatId) {
            super(seatId);
        }

        double getPrice() {
            return 150;
        }
    }

    static class PremiumSeat extends Seat {
        PremiumSeat(String seatId) {
            super(seatId);
        }

        double getPrice() {
            return 250;
        }
    }

    static class ReclinerSeat extends Seat {
        ReclinerSeat(String seatId) {
            super(seatId);
        }

        double getPrice() {
            return 400;
        }
    }

    static class Show {
        String name;
        private Set<String> bookedSeatIds = new HashSet<>();

        Show(String name) {
            this.name = name;
        }

        boolean isBooked(String seatId) {
            return bookedSeatIds.contains(seatId);
        }

        private void book(String seatId) {
            bookedSeatIds.add(seatId);
        }

        private void release(String seatId) {
            bookedSeatIds.remove(seatId);
        }
    }

    static class Booking {
        String customer;
        Show show;
        List<Seat> seats;
        boolean cancelled = false;

        Booking(String customer, Show show, List<Seat> seats) {
            this.customer = customer;
            this.show = show;
            this.seats = seats;
        }
    }

    static Booking book(String customer, Show show, Seat... seats) {
        for (Seat seat : seats) {
            if (show.isBooked(seat.seatId)) {
                System.out.println("Seat " + seat.seatId + " is already booked for this show.");
                return null;
            }
        }

        double total = 0;
        List<Seat> seatList = new ArrayList<>();
        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < seats.length; i++) {
            show.book(seats[i].seatId);
            seatList.add(seats[i]);
            total += seats[i].getPrice();
            ids.append(seats[i].seatId);
            if (i < seats.length - 1) {
                ids.append(", ");
            }
        }

        System.out.printf("Booking confirmed for %s: %s. Total: \u20b9%.2f%n", customer, ids, total);
        return new Booking(customer, show, seatList);
    }

    static void cancel(Booking booking) {
        booking.cancelled = true;
        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < booking.seats.size(); i++) {
            Seat seat = booking.seats.get(i);
            booking.show.release(seat.seatId);
            ids.append(seat.seatId);
            if (i < booking.seats.size() - 1) {
                ids.append(", ");
            }
        }
        System.out.println(booking.customer + "'s booking cancelled. Seats " + ids + " released.");
    }

    public static void main(String[] args) {
        Show show7pm = new Show("7 PM show");

        Booking ashaBooking = book("Asha", show7pm,
                new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5"));
        // Booking confirmed for Asha: A1, A2, F5. Total: ₹550.00

        book("Ravi", show7pm, new RegularSeat("A2"));
        // Seat A2 is already booked for this show.

        book("Ravi", show7pm, new ReclinerSeat("R1"));
        // Booking confirmed for Ravi: R1. Total: ₹400.00

        cancel(ashaBooking);
        // Asha's booking cancelled. Seats A1, A2, F5 released.

        book("Neha", show7pm, new RegularSeat("A2"));
        // Booking confirmed for Neha: A2. Total: ₹150.00
    }
}
