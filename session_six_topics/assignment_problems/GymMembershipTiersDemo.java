package session_six_topics.assignment_problems;

/**
 * PROBLEM 2: Three Tiers of Gym Membership
 * EliteMember extends PremiumMember (multilevel, 3 deep).
 * GroupClassMember extends GymMember directly (hierarchical sibling of PremiumMember).
 * classifyGeneration() uses instanceof only; getTotalSessionsAttended() sums polymorphically.
 */
public class GymMembershipTiersDemo {

    static class GymMember {
        private String memberId;
        protected int monthlyFee;
        private int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
                throw new IllegalArgumentException("construction rejected");
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        void attendSession() {
            sessionsAttended++;
        }

        int getSessionsAttended() {
            return sessionsAttended;
        }

        String displayInfo() {
            return "Standard Member | Sessions: " + sessionsAttended;
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
            return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
        }
    }

    static class EliteMember extends PremiumMember {
        String lockerNumber;

        public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
            super(memberId, monthlyFee, trainerName);
            this.lockerNumber = lockerNumber;
        }

        @Override
        String displayInfo() {
            return "Elite Member | Trainer: " + trainerName + " | Locker: " + lockerNumber
                    + " | Sessions: " + getSessionsAttended();
        }
    }

    static class GroupClassMember extends GymMember {
        String className;

        public GroupClassMember(String memberId, int monthlyFee, String className) {
            super(memberId, monthlyFee);
            this.className = className;
        }

        @Override
        String displayInfo() {
            return "Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended();
        }
    }

    static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Direct subclass";
        } else {
            return "Base class";
        }
    }

    static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember member : members) {
            total += member.getSessionsAttended(); // polymorphic dispatch — no type checks needed
        }
        return total;
    }

    public static void main(String[] args) {
        GymMember standard = new GymMember("MEM1", 1000);
        PremiumMember premium = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember elite = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember groupClass = new GroupClassMember("MEM4", 1500, "Zumba");

        System.out.println(standard.displayInfo());   // Standard Member | Sessions: 0
        System.out.println(premium.displayInfo());     // Premium Member | Trainer: Coach Riya | Sessions: 0
        System.out.println(elite.displayInfo());        // Elite Member | Trainer: Coach Arjun | Locker: L12 | Sessions: 0
        System.out.println(groupClass.displayInfo());   // Group Class Member | Class: Zumba | Sessions: 0

        System.out.println(classifyGeneration(elite));      // Multilevel descendant (3 generations deep)
        System.out.println(classifyGeneration(groupClass)); // Hierarchical sibling (independent branch)

        premium.attendSession();
        premium.attendSession();
        premium.attendSession();
        elite.attendSession();
        elite.attendSession();
        groupClass.attendSession();
        groupClass.attendSession();
        groupClass.attendSession();
        groupClass.attendSession();

        System.out.println(getTotalSessionsAttended(new GymMember[]{premium, elite, groupClass})); // 9
    }
}
