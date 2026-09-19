package session_five_topics.class_problems;

/**
 * PROBLEM 2: Subclass Ticket Access
 * Extends classifyAccess to 5 contexts, capturing the tricky rule: protected access via
 * a subclass in a different package is only allowed when reached through the subclass's
 * OWN declared type, not through a variable declared as the PARENT type.
 */
public class SubclassTicketAccessDemo {

    // MovieTicket lives in one package; PremiumMovieTicket (in a different package) extends
    // it to reach the protected ticketPrice field.
    static class MovieTicket {
        protected double ticketPrice;

        MovieTicket(double ticketPrice) {
            this.ticketPrice = ticketPrice;
        }
    }

    static class PremiumMovieTicket extends MovieTicket {
        PremiumMovieTicket(double ticketPrice) {
            super(ticketPrice);
        }
    }

    static class AccessChecker {

        // Now handles 5 contexts total.
        static String classifyAccess(String fieldModifier, String accessorContext) {
            switch (fieldModifier) {
                case "private":
                    return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
                case "default":
                    return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                            ? "ALLOWED" : "DENIED";
                case "protected":
                    if (accessorContext.equals("SAME_CLASS")
                            || accessorContext.equals("SAME_PACKAGE")
                            || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                        return "ALLOWED";
                    }
                    // DIFFERENT_PACKAGE and SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE both denied:
                    // Java checks the variable's declared (compile-time) type, not the real object type.
                    return "DENIED";
                case "public":
                    return "ALLOWED";
                default:
                    return "DENIED";
            }
        }
    }

    public static void main(String[] args) {
        // Even though the object really is a PremiumMovieTicket, accessing it through a
        // MovieTicket-typed reference denies protected access.
        System.out.println(AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));    // ALLOWED
        System.out.println(AccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")); // DENIED
    }
}
