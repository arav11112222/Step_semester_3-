package session_six_topics.assignment_problems;

/**
 * PROBLEM 4: The Monthly Attendance Announcer
 * batchPrint() calls displayInfo() polymorphically (no instanceof/if-else deciding what
 * to print) and only uses instanceof to guard the one place a real downcast is needed:
 * reaching PremiumMember's trainer name.
 */
public class MonthlyAttendanceAnnouncerDemo {

    static class GymMember {
        private String memberId;
        private int monthlyFee;
        private int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
                throw new IllegalArgumentException("construction rejected");
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        int getSessionsAttended() {
            return sessionsAttended;
        }

        String displayInfo() {
            return "Standard | Sessions: " + sessionsAttended;
        }
    }

    static class PremiumMember extends GymMember {
        String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        String displayInfo() {
            return "Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
        }
    }

    static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (GymMember member : members) {
            sb.append(member.displayInfo()); // polymorphic call, no type check needed here

            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member; // guarded downcast
                sb.append(" [Trainer via downcast: ").append(premium.trainerName).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        GymMember[] members = {
                new GymMember("MEM6", 1000),
                new PremiumMember("MEM7", 2000, "Coach Riya")
        };
        System.out.println(batchPrint(members));
        // Standard | Sessions: 0 | Premium | Trainer: Coach Riya | Sessions: 0 [Trainer via downcast: Coach Riya] |

        try {
            GymMember plain = new GymMember("MEM8", 1000);
            PremiumMember bad = (PremiumMember) plain; // compiles fine, fails at runtime
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
