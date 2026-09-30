package session_eight_topics.assignment_problems;

/**
 * Q1: The Hostel Laundry Queue
 * WashType is abstract with its own duration/charge, so a new type (e.g. Delicate)
 * plugs in without touching the booking logic. WashingMachine's busy flag is private —
 * only startWash()/completeWash() can change it.
 */
public class HostelLaundryQueueDemo {

    static abstract class WashType {
        abstract String getName();

        abstract int getDurationMinutes();

        abstract double getCharge();
    }

    static class QuickWash extends WashType {
        String getName() {
            return "Quick";
        }

        int getDurationMinutes() {
            return 30;
        }

        double getCharge() {
            return 20;
        }
    }

    static class NormalWash extends WashType {
        String getName() {
            return "Normal";
        }

        int getDurationMinutes() {
            return 45;
        }

        double getCharge() {
            return 30;
        }
    }

    static class HeavyWash extends WashType {
        String getName() {
            return "Heavy";
        }

        int getDurationMinutes() {
            return 60;
        }

        double getCharge() {
            return 45;
        }
    }

    static class WashingMachine {
        String id;
        private boolean busy = false;

        WashingMachine(String id) {
            this.id = id;
        }

        boolean isBusy() {
            return busy;
        }

        private void markBusy() {
            busy = true;
        }

        private void markFree() {
            busy = false;
        }
    }

    static class WashCycle {
        String student;
        WashingMachine machine;
        WashType washType;

        WashCycle(String student, WashingMachine machine, WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }
    }

    static class LaundrySystem {
        WashCycle startWash(String student, WashingMachine machine, WashType washType) {
            if (machine.isBusy()) {
                System.out.println("Machine " + machine.id + " is currently busy.");
                return null;
            }

            machine.markBusy();
            System.out.printf("%s wash started on %s for %s (%d min). Charge: \u20b9%.2f.%n",
                    washType.getName(), machine.id, student, washType.getDurationMinutes(), washType.getCharge());

            return new WashCycle(student, machine, washType);
        }

        void completeWash(WashingMachine machine) {
            machine.markFree();
            System.out.println(machine.id + " cycle completed. " + machine.id + " is now free.");
        }
    }

    public static void main(String[] args) {
        LaundrySystem system = new LaundrySystem();
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        system.startWash("Asha", m1, new QuickWash());
        // Quick wash started on M1 for Asha (30 min). Charge: ₹20.00.

        system.startWash("Ravi", m1, new HeavyWash());
        // Machine M1 is currently busy.

        system.startWash("Ravi", m2, new HeavyWash());
        // Heavy wash started on M2 for Ravi (60 min). Charge: ₹45.00.

        system.completeWash(m1);
        // M1 cycle completed. M1 is now free.

        system.startWash("Neha", m1, new NormalWash());
        // Normal wash started on M1 for Neha (45 min). Charge: ₹30.00.
    }
}
