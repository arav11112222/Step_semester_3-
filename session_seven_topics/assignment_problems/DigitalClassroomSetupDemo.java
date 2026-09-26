package session_seven_topics.assignment_problems;

/**
 * PROBLEM 4: Digital Classroom Setup
 * ClassroomDevice is abstract with an abstract operate(). Chargeable is a separate
 * interface with two overloaded methods (charge() and charge(int minutes)) — true
 * compile-time polymorphism, Java picks the right one based on argument count.
 */
public class DigitalClassroomSetupDemo {

    static abstract class ClassroomDevice {
        public abstract String operate();
    }

    interface Chargeable {
        String charge();

        String charge(int minutes);
    }

    static class Tablet extends ClassroomDevice implements Chargeable {
        String assetTag;

        public Tablet(String assetTag) {
            this.assetTag = assetTag;
        }

        @Override
        public String operate() {
            return "Tablet " + assetTag + " displaying lesson";
        }

        @Override
        public String charge() {
            return assetTag + " charging";
        }

        @Override
        public String charge(int minutes) {
            return assetTag + " charging for " + minutes + " minutes";
        }
    }

    public static void main(String[] args) {
        Tablet t = new Tablet("TAB-5");
        System.out.println(t.operate());   // Tablet TAB-5 displaying lesson
        System.out.println(t.charge());    // TAB-5 charging
        System.out.println(t.charge(30));  // TAB-5 charging for 30 minutes
    }
}
