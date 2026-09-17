package session_six_topics.assignment_problems;

/**
 * PROBLEM 5: Membership Numbers, Referral Codes & the Weekly Check-in Settlement
 * membershipNumber is final, assigned once from a shared static counter inside the
 * constructor. isValidReferralCode() checks format manually with charAt()/Character
 * methods (no regex). payFee(int, String) reuses payFee(int) rather than duplicating
 * the total-tracking logic. processWeeklyCheckIn() is null-safe and dispatches via instanceof.
 */
public class MembershipNumbersReferralCodesDemo {

    static class GymMember {
        private static int counter = 2000;

        final String membershipNumber;
        private int monthlyFee;
        private int feesPaid;

        public GymMember(int monthlyFee) {
            counter++;
            this.membershipNumber = "GYM-" + counter;
            this.monthlyFee = monthlyFee;
        }

        void payFee(int amount) {
            feesPaid += amount;
        }

        void payFee(int amount, String mode) {
            // mode is only recorded for context; the actual total is delegated below
            payFee(amount);
        }

        int getFeesPaid() {
            return feesPaid;
        }

        static int getMembersEnrolled() {
            return counter - 2000;
        }

        static boolean isValidReferralCode(String code) {
            if (code == null || code.length() != 4) {
                return false;
            }
            if (code.charAt(0) != 'G') {
                return false;
            }
            if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
                return false;
            }
            return Character.isUpperCase(code.charAt(3));
        }
    }

    static class GroupClassMember extends GymMember {
        String className;

        public GroupClassMember(int monthlyFee, String className) {
            super(monthlyFee);
            this.className = className;
        }
    }

    static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (GymMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (member instanceof GroupClassMember) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        GymMember m1 = new GymMember(1000);
        System.out.println(m1.membershipNumber);                 // GYM-2001
        System.out.println(GymMember.getMembersEnrolled());       // 1

        System.out.println(GymMember.isValidReferralCode("G45B")); // true
        System.out.println(GymMember.isValidReferralCode("G4B"));  // false
        System.out.println(GymMember.isValidReferralCode("X45B")); // false

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid()); // 1000

        GymMember[] batch = {
                new GroupClassMember(1500, "Zumba"),
                null,
                new GymMember(1000)
        };
        System.out.println(processWeeklyCheckIn(batch));
        // 2 processed | 1 null skipped | 1 group | 1 individual
    }
}
