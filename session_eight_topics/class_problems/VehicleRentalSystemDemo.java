package session_eight_topics.class_problems;

/**
 * Q1: Vehicle Rental System
 * Vehicle is abstract with its own calculateCharge() per category, so a new category
 * (e.g. Van) can be added without touching RentalSystem. Availability is private to
 * Vehicle — only rent()/returnVehicle() can flip it.
 */
public class VehicleRentalSystemDemo {

    static abstract class Vehicle {
        String id;
        private boolean available = true;

        Vehicle(String id) {
            this.id = id;
        }

        abstract double calculateCharge(int days);

        boolean isAvailable() {
            return available;
        }

        void markRented() {
            available = false;
        }

        void markAvailable() {
            available = true;
        }
    }

    static class Sedan extends Vehicle {
        Sedan(String id) {
            super(id);
        }

        @Override
        double calculateCharge(int days) {
            return days * 50.0;
        }
    }

    static class SUV extends Vehicle {
        SUV(String id) {
            super(id);
        }

        @Override
        double calculateCharge(int days) {
            return days * 70.0;
        }
    }

    static class Truck extends Vehicle {
        Truck(String id) {
            super(id);
        }

        @Override
        double calculateCharge(int days) {
            return days * 100.0;
        }
    }

    static class Rental {
        String customer;
        Vehicle vehicle;
        int days;

        Rental(String customer, Vehicle vehicle, int days) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
        }
    }

    static class RentalSystem {
        void rent(String customer, Vehicle vehicle, int days) {
            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.id + " is currently unavailable.");
                return;
            }
            vehicle.markRented();
            double charge = vehicle.calculateCharge(days);
            System.out.printf("%s rented successfully by %s. Rental charge: $%.2f.%n",
                    vehicle.id, customer, charge);
        }

        void returnVehicle(String customer, Vehicle vehicle) {
            vehicle.markAvailable();
            System.out.println(vehicle.id + " returned by " + customer + ".");
        }
    }

    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();

        Sedan sedanA = new Sedan("Sedan A");
        SUV suvB = new SUV("SUV B");

        system.rent("Customer 1", sedanA, 3);         // Sedan A rented... Rental charge: $150.00.
        system.rent("Customer 2", sedanA, 2);          // Sedan A is currently unavailable.
        system.returnVehicle("Customer 1", sedanA);     // Sedan A returned by Customer 1.
        system.rent("Customer 3", suvB, 5);             // SUV B rented... Rental charge: $350.00.
    }
}
