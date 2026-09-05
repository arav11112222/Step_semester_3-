package session_four_topics.assignment_problems;

/**
 * A4. Static Block — Library Membership Card Setup
 * The static block sets libraryName and validUntil exactly once, regardless of
 * how many MembershipCard objects are created afterward.
 */
public class MembershipCardDemo {

    static class MembershipCard {
        static String libraryName;
        static String validUntil;

        static {
            libraryName = "SRM Central Library";
            validUntil = "May 2027";
            System.out.println("Library info loaded");
        }

        String studentName;

        MembershipCard(String studentName) {
            this.studentName = studentName;
            System.out.println("Membership card issued: " + studentName);
        }
    }

    public static void main(String[] args) {
        String[] names = {"Ananya", "Rohan", "Priya", "Arjun", "Sneha"};

        for (String name : names) {
            new MembershipCard(name);
        }
    }
}
