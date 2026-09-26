package session_seven_topics.assignment_problems;

/**
 * PROBLEM 5: Skyline Delivery Fleet
 * Drone is abstract with an abstract fly(). Trackable is a separate interface.
 * DeliveryDrone extends Drone and implements Trackable; ScoutDrone extends Drone but
 * deliberately does NOT implement Trackable (a sibling can opt out of a capability).
 * GroundRobot implements only Trackable, with no relationship to Drone at all — proving
 * that getLocationIfTrackable() cares about the interface, not the ancestry.
 */
public class SkylineDeliveryFleetDemo {

    static abstract class Drone {
        public abstract String fly();
    }

    interface Trackable {
        String getLocation();
    }

    static class DeliveryDrone extends Drone implements Trackable {
        String id;

        public DeliveryDrone(String id) {
            this.id = id;
        }

        @Override
        public String fly() {
            return id + " flying delivery route";
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
    }

    static class ScoutDrone extends Drone {
        String id;

        public ScoutDrone(String id) {
            this.id = id;
        }

        @Override
        public String fly() {
            return id + " scouting the area";
        }
        // Deliberately does not implement Trackable.
    }

    static class GroundRobot implements Trackable {
        String id;

        public GroundRobot(String id) {
            this.id = id;
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
        // No relationship to Drone at all.
    }

    static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable trackable = (Trackable) o;
            return trackable.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println(getLocationIfTrackable(d)); // DR-1 at Sector 4

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println(getLocationIfTrackable(s)); // Tracking not available

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(g)); // GR-1 at Sector 4
    }
}
