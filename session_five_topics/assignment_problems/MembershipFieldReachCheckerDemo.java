package session_five_topics.assignment_problems;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * PROBLEM 1: Membership Field Reach Checker
 * Same classifyAccess rules as the mentor set, plus summarizeByModifier() which groups
 * counts per modifier (not a flat total) — every modifier appears even with zero attempts.
 */
public class MembershipFieldReachCheckerDemo {

    /**
     * LibraryMember's field access levels:
     * - membershipPin: private -> inaccessible outside LibraryMember itself.
     * - branchCode: default    -> reachable only within the same package.
     * - finesOwed: protected   -> reachable by same package (and by subclasses, see Problem 2).
     * - displayName: public    -> reachable from anywhere.
     */
    static class LibraryMember {
        private String membershipPin;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        LibraryMember(String membershipPin, String branchCode, double finesOwed, String displayName) {
            this.membershipPin = membershipPin;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
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

        static String summarizeByModifier(String[][] attempts) {
            String[] modifiers = {"private", "default", "protected", "public"};

            Map<String, int[]> counts = new LinkedHashMap<>();
            for (String modifier : modifiers) {
                counts.put(modifier, new int[]{0, 0}); // [allowed, denied]
            }

            for (String[] attempt : attempts) {
                String modifier = attempt[0];
                String result = classifyAccess(modifier, attempt[1]);
                int[] tally = counts.get(modifier);
                if (result.equals("ALLOWED")) {
                    tally[0]++;
                } else {
                    tally[1]++;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < modifiers.length; i++) {
                int[] tally = counts.get(modifiers[i]);
                sb.append(modifiers[i]).append(": ").append(tally[0]).append(" allowed / ")
                        .append(tally[1]).append(" denied");
                if (i < modifiers.length - 1) {
                    sb.append(" | ");
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));        // ALLOWED
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE")); // DENIED

        String[][] batch = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(AccessChecker.summarizeByModifier(batch));
        // private: 1 allowed / 1 denied | default: 1 allowed / 1 denied | protected: 2 allowed / 0 denied | public: 1 allowed / 0 denied
    }
}
