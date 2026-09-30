package session_eight_topics.assignment_problems;

/**
 * Q4: The FitZone Membership Desk
 * MembershipPlan is abstract with its own calculateFee(), so a new plan (e.g. Half-Yearly)
 * plugs in without touching Membership's status logic. Membership privately owns its
 * status and rejects invalid transitions (e.g. freezing an Expired membership).
 */
public class FitZoneMembershipDeskDemo {

    static abstract class MembershipPlan {
        private static final double BASE_RATE = 1000;

        abstract String getName();

        abstract int getMonths();

        double calculateFee() {
            return BASE_RATE * getMonths() * (1 - getDiscount());
        }

        abstract double getDiscount();
    }

    static class MonthlyPlan extends MembershipPlan {
        String getName() {
            return "Monthly";
        }

        int getMonths() {
            return 1;
        }

        double getDiscount() {
            return 0;
        }
    }

    static class QuarterlyPlan extends MembershipPlan {
        String getName() {
            return "Quarterly";
        }

        int getMonths() {
            return 3;
        }

        double getDiscount() {
            return 0.10;
        }
    }

    static class AnnualPlan extends MembershipPlan {
        String getName() {
            return "Annual";
        }

        int getMonths() {
            return 12;
        }

        double getDiscount() {
            return 0.25;
        }
    }

    static class Membership {
        String member;
        MembershipPlan plan;
        private String status = "Active";

        Membership(String member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;
            System.out.printf("%s membership created for %s. Fee: \u20b9%.2f. Status: Active%n",
                    plan.getName(), member, plan.calculateFee());
        }

        void checkIn() {
            if (!status.equals("Active")) {
                System.out.println("Check-in denied: " + member + "'s membership is " + status + ".");
                return;
            }
            System.out.println(member + " checked in successfully.");
        }

        void freeze() {
            if (status.equals("Expired")) {
                System.out.println("Cannot freeze an Expired membership.");
                return;
            }
            if (!status.equals("Active")) {
                System.out.println("Cannot freeze a membership that isn't Active.");
                return;
            }
            status = "Frozen";
            System.out.println(member + "'s membership frozen. Status: Frozen");
        }

        void unfreeze() {
            if (status.equals("Expired")) {
                System.out.println("Cannot unfreeze an Expired membership.");
                return;
            }
            if (!status.equals("Frozen")) {
                System.out.println("Cannot unfreeze a membership that isn't Frozen.");
                return;
            }
            status = "Active";
            System.out.println(member + "'s membership unfrozen. Status: Active");
        }

        void expire() {
            status = "Expired";
            System.out.println(member + "'s membership expired. Status: Expired");
        }
    }

    public static void main(String[] args) {
        Membership ashaMembership = new Membership("Asha", new QuarterlyPlan()); // Fee: ₹2700.00
        Membership raviMembership = new Membership("Ravi", new MonthlyPlan());   // Fee: ₹1000.00

        ashaMembership.checkIn();  // checked in successfully
        ashaMembership.freeze();   // Frozen
        ashaMembership.checkIn();  // Check-in denied: Frozen

        raviMembership.expire();   // Expired
        raviMembership.freeze();   // Cannot freeze an Expired membership.
    }
}
