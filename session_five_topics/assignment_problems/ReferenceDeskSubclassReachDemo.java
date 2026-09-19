package session_five_topics.assignment_problems;

/**
 * PROBLEM 2: Reference Desk Subclass Reach
 * Extends classifyAccess to 5 contexts (same protected/cross-package/subclass rule as the
 * mentor set), plus firstDeniedAttempt() which scans in order and stops at the first denial
 * — a genuinely different aggregation style from batch counting.
 */
public class ReferenceDeskSubclassReachDemo {

    static class AccessChecker {

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
                    return "DENIED"; // includes SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE
                case "public":
                    return "ALLOWED";
                default:
                    return "DENIED";
            }
        }

        // Scans strictly in order and stops the moment a denial is found — no full pass first.
        static String firstDeniedAttempt(String[][] attempts) {
            for (int i = 0; i < attempts.length; i++) {
                String modifier = attempts[i][0];
                String context = attempts[i][1];
                if (classifyAccess(modifier, context).equals("DENIED")) {
                    return modifier + " via " + context + " (attempt #" + (i + 1) + ")";
                }
            }
            return "None Denied";
        }
    }

    public static void main(String[] args) {
        String[][] attempts1 = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(AccessChecker.firstDeniedAttempt(attempts1));
        // protected via SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE (attempt #2)

        String[][] attempts2 = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(AccessChecker.firstDeniedAttempt(attempts2));
        // None Denied
    }
}
