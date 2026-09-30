package session_eight_topics.class_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Q4: Hotel Booking System
 * Room is abstract with its own calculatePrice(), so a new category (e.g. Suite)
 * plugs in without changing reservation/availability logic. Availability is checked
 * against the room's own list of active reservations for date overlap.
 */
public class HotelBookingSystemDemo {

    static abstract class Room {
        String id;
        List<Reservation> reservations = new ArrayList<>();

        Room(String id) {
            this.id = id;
        }

        abstract double calculatePrice(int nights);

        boolean isAvailable(int start, int end) {
            for (Reservation r : reservations) {
                if (r.active && start < r.end && end > r.start) {
                    return false; // overlapping range
                }
            }
            return true;
        }
    }

    static class StandardRoom extends Room {
        StandardRoom(String id) {
            super(id);
        }

        @Override
        double calculatePrice(int nights) {
            return nights * 80.0;
        }
    }

    static class DeluxeRoom extends Room {
        DeluxeRoom(String id) {
            super(id);
        }

        @Override
        double calculatePrice(int nights) {
            return nights * 120.0;
        }
    }

    static class Reservation {
        String customer;
        Room room;
        int start;
        int end;
        boolean active = true;

        Reservation(String customer, Room room, int start, int end) {
            this.customer = customer;
            this.room = room;
            this.start = start;
            this.end = end;
        }
    }

    static void checkAvailability(Room room, int start, int end, String label) {
        if (room.isAvailable(start, end)) {
            System.out.println(room.id + " is available from " + label);
        } else {
            System.out.println(room.id + " is not available from " + label);
        }
    }

    static Reservation reserve(String customer, Room room, int start, int end, String label) {
        if (!room.isAvailable(start, end)) {
            System.out.println(room.id + " is not available from " + label);
            return null;
        }
        int nights = end - start;
        double price = room.calculatePrice(nights);
        Reservation reservation = new Reservation(customer, room, start, end);
        room.reservations.add(reservation);
        System.out.printf("Reservation confirmed for %s, %s (%s). Price: $%.2f%n",
                customer, room.id, label, price);
        return reservation;
    }

    static void cancel(Reservation reservation, String label) {
        reservation.active = false;
        System.out.println("Reservation for " + reservation.customer + ", " + reservation.room.id
                + " (" + label + ") cancelled successfully.");
    }

    public static void main(String[] args) {
        StandardRoom room101 = new StandardRoom("Standard Room 101");
        DeluxeRoom room201 = new DeluxeRoom("Deluxe Room 201");

        checkAvailability(room101, 1, 5, "Jan 1 to Jan 5"); // available

        Reservation resA = reserve("Customer A", room101, 1, 5, "Jan 1-5"); // Price: $320.00

        checkAvailability(room101, 3, 7, "Jan 3 to Jan 7"); // not available (overlaps)

        cancel(resA, "Jan 1-5");

        reserve("Customer C", room201, 40, 42, "Feb 10-12"); // Price: $240.00
    }
}
