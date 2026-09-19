package session_five_topics.class_problems;

/**
 * PROBLEM 1: Movie Ticket Field Visibility Checker
 * AccessChecker classifies a single access attempt using Java's real visibility rules
 * for private/default/protected/public against SAME_CLASS/SAME_PACKAGE/DIFFERENT_PACKAGE.
 */
public class AccessCheckerDemo {

    /**
     * MovieTicket's field access levels, chosen for real reasons:
     * - seatNumber: private   -> only MovieTicket itself needs to touch the raw seat value.
     * - screenId: default     -> other classes in the booking package (same package) need it,
     *                            but no one outside the module should.
     * - ticketPrice: protected -> a subclass (PremiumMovieTicket, see Problem 2) needs it too.
     * - movieTitle: public    -> the UI, receipts, anything anywhere needs to display this.
     */
    static class MovieTicket {
        private int seatNumber;
        int screenId;
        protected double ticketPrice;
        public String movieTitle;

        MovieTicket(int seatNumber, int screenId, double ticketPrice, String movieTitle) {
            this.seatNumber = seatNumber;
            this.screenId = screenId;
            this.ticketPrice = ticketPrice;
            this.movieTitle = movieTitle;
        }
    }

    static class AccessChecker {

        static String classifyAccess(String fieldModifier, String accessorContext) {
            switch (fieldModifier) {
                case "private":
                    return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
                case "default":
                    return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                            ? "ALLOWED" : "DENIED";
                case "protected":
                    return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE"))
                            ? "ALLOWED" : "DENIED";
                case "public":
                    return "ALLOWED";
                default:
                    return "DENIED";
            }
        }

        static String summarizeBatch(String[][] attempts) {
            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {
                String result = classifyAccess(attempt[0], attempt[1]);
                if (result.equals("ALLOWED")) {
                    allowed++;
                } else {
                    denied++;
                }
            }

            return "Allowed: " + allowed + " | Denied: " + denied;
        }
    }

    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));        // ALLOWED
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE")); // DENIED

        String[][] batch = {
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(AccessChecker.summarizeBatch(batch)); // Allowed: 2 | Denied: 1
    }
}
