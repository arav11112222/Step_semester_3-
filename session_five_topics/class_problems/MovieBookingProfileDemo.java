package session_five_topics.class_problems;

/**
 * PROBLEM 4: MovieBookingProfile JavaBean & OTP Property
 * Fully JavaBean-compliant getX()/setX() pairs, isX() for booleans, a convenience
 * constructor chaining via this(...), and otp as a genuinely write-only property
 * (settable, but with no getter anywhere on the class).
 */
public class MovieBookingProfileDemo {

    static class MovieBookingProfile {
        private String name;
        private boolean confirmed;
        private String otp; // intentionally has no matching getter

        public MovieBookingProfile() {
        }

        public MovieBookingProfile(String name) {
            this();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public void setConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }
        // No getOtp() method exists anywhere — otp can never be read back out.
    }

    public static void main(String[] args) {
        System.out.println(new MovieBookingProfile("Rahul Dev").getName()); // Rahul Dev

        MovieBookingProfile p = new MovieBookingProfile("Rahul Dev");
        p.setConfirmed(true);
        System.out.println(p.isConfirmed()); // true

        p.setOtp("4471"); // stored, but unobservable — no method can retrieve it again
    }
}
