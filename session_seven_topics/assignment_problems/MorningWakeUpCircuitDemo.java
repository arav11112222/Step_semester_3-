package session_seven_topics.assignment_problems;

/**
 * PROBLEM 1: Morning Wake-Up Circuit
 * AlarmClock and Doorbell share nothing else — Ringable is the only thing that lets
 * one method (ringAll) work on both, with no shared parent class.
 */
public class MorningWakeUpCircuitDemo {

    interface Ringable {
        String ring();
    }

    static class AlarmClock implements Ringable {
        String time;

        public AlarmClock(String time) {
            this.time = time;
        }

        @Override
        public String ring() {
            return "Alarm ringing for " + time;
        }
    }

    static class Doorbell implements Ringable {
        String location;

        public Doorbell(String location) {
            this.location = location;
        }

        @Override
        public String ring() {
            return "Doorbell ringing at " + location;
        }
    }

    static void ringAll(Ringable[] devices) {
        for (Ringable device : devices) {
            System.out.println(device.ring());
        }
    }

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        System.out.println(a.ring()); // Alarm ringing for 7:00 AM

        Doorbell d = new Doorbell("Front Door");
        System.out.println(d.ring()); // Doorbell ringing at Front Door

        ringAll(new Ringable[]{a, d});
        // Alarm ringing for 7:00 AM
        // Doorbell ringing at Front Door
    }
}
