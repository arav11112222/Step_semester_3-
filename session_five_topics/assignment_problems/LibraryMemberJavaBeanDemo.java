package session_five_topics.assignment_problems;

/**
 * PROBLEM 4: LibraryMember JavaBean & Security Answer Property
 * membershipId is write-once through a setter that technically exists (as the JavaBean
 * scanner requires) but only takes effect on the first call. securityAnswer is stored only
 * as a one-way transformed value, with no getter anywhere on the class.
 */
public class LibraryMemberJavaBeanDemo {

    static class LibraryMember {
        private String membershipId;
        private boolean membershipIdSet = false;
        private String name;
        private boolean premiumMember;
        private String securityAnswerHash; // one-way transformed, intentionally has no getter

        public LibraryMember() {
        }

        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String id) {
            if (!membershipIdSet) {
                this.membershipId = id;
                this.membershipIdSet = true;
            }
            // else: silently ignored — write-once
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPremiumMember() {
            return premiumMember;
        }

        public void setPremiumMember(boolean premium) {
            this.premiumMember = premium;
        }

        public void setSecurityAnswer(String answer) {
            // Deterministic one-way transform — no real crypto needed, just no way back.
            this.securityAnswerHash = String.valueOf(answer.hashCode());
        }
        // No getSecurityAnswer() exists anywhere.
    }

    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        System.out.println(m.getMembershipId()); // LIB-8841

        m.setMembershipId("FAKE-0000"); // silently ignored, write-once
        System.out.println(m.getMembershipId()); // LIB-8841

        System.out.println(m.isPremiumMember()); // true

        m.setSecurityAnswer("BlueMountain"); // stored, but unobservable — no getter exists
    }
}
