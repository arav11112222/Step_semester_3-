package session_ten_topics.class_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 2: Campus Vehicle Pass System
 * Vehicle is abstract and supplies the pass fee per type. Chargeable is a separate interface
 * that only the electric vehicles implement, so "can this vehicle charge?" is just an
 * instanceof check and no vehicle needs a flag field.
 *   Bike 300, Car 1000, EBike 300 (chargeable), ECar 1000 (chargeable)
 */
public class CampusVehiclePassDemo {

    interface Chargeable {
        String bookChargingBay();
    }

    static abstract class Vehicle {
        String passNumber;
        String owner;
        String typeLabel;

        Vehicle(String typeLabel, String passNumber, String owner) {
            this.typeLabel = typeLabel;
            this.passNumber = passNumber;
            this.owner = owner;
        }

        abstract int getPassFee();
    }

    static class Bike extends Vehicle {
        Bike(String label, String pass, String owner) {
            super(label, pass, owner);
        }

        int getPassFee() {
            return 300;
        }
    }

    static class Car extends Vehicle {
        Car(String label, String pass, String owner) {
            super(label, pass, owner);
        }

        int getPassFee() {
            return 1000;
        }
    }

    static class EBike extends Vehicle implements Chargeable {
        EBike(String label, String pass, String owner) {
            super(label, pass, owner);
        }

        int getPassFee() {
            return 300;
        }

        public String bookChargingBay() {
            return "charging bay allotted";
        }
    }

    static class ECar extends Vehicle implements Chargeable {
        ECar(String label, String pass, String owner) {
            super(label, pass, owner);
        }

        int getPassFee() {
            return 1000;
        }

        public String bookChargingBay() {
            return "charging bay allotted";
        }
    }

    static Vehicle createVehicle(String type, String pass, String owner) {
        switch (type) {
            case "Bike":
                return new Bike(type, pass, owner);
            case "Car":
                return new Car(type, pass, owner);
            case "EBike":
            case "E-Bike":
                return new EBike(type, pass, owner);
            case "ECar":
            case "E-Car":
                return new ECar(type, pass, owner);
            default:
                return null;
        }
    }

    static void process(String[] operations) {
        Map<String, Vehicle> vehicles = new HashMap<>();

        for (String op : operations) {
            String[] t = op.trim().split("\\s+");

            if (t[0].equals("PASS")) {
                Vehicle v = createVehicle(t[1], t[2], t[3]);
                if (v == null) {
                    System.out.println("Unknown vehicle type: " + t[1]);
                    continue;
                }
                vehicles.put(v.passNumber, v);
                System.out.println(v.passNumber + " (" + v.typeLabel + ") pass fee " + v.getPassFee());
            } else if (t[0].equals("CHARGE")) {
                Vehicle v = vehicles.get(t[1]);
                if (v == null) {
                    System.out.println(t[1] + " not found");
                } else if (v instanceof Chargeable) {
                    System.out.println(v.passNumber + " " + ((Chargeable) v).bookChargingBay());
                } else {
                    System.out.println(v.passNumber + " rejected: charging unsupported");
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] input = {
                "PASS Bike KA01 Asha",
                "PASS ECar KA02 Ravi",
                "CHARGE KA02",
                "CHARGE KA01"
        };
        process(input);
        // KA01 (Bike) pass fee 300
        // KA02 (ECar) pass fee 1000
        // KA02 charging bay allotted
        // KA01 rejected: charging unsupported
    }
}
