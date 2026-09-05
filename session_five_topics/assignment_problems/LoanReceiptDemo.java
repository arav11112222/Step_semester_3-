package session_five_topics.assignment_problems;

/**
 * PROBLEM 5: Immutable Loan Receipt & Nightly Circulation Ledger
 *
 * Same "final class" vs. subclassing contradiction as the mentor set's capstone: the brief
 * asks for LoanReceipt to be a final class, but also requires ReferenceOnlyLoanReceipt to
 * extend it — those two requirements cannot both hold in real Java. LoanReceipt is left
 * non-final so the required subclass compiles; every other immutability guarantee (final
 * fields, defensive copying in and out, the wither pattern, static one-time setup) is
 * implemented exactly as specified.
 */
public class LoanReceiptDemo {

    static class LoanReceipt {
        private final String memberId;
        private final String[] bookIds;

        public LoanReceipt(String memberId, String[] bookIds) {
            this.memberId = memberId;
            this.bookIds = (bookIds == null) ? new String[0] : bookIds.clone();
        }

        public String getMemberId() {
            return memberId;
        }

        public String[] getBookIds() {
            return bookIds.clone(); // defensive copy out
        }

        public LoanReceipt withCorrectedBookId(int index, String newId) {
            String[] updated = bookIds.clone();
            updated[index] = newId;
            return new LoanReceipt(memberId, updated);
        }
    }

    static class ReferenceOnlyLoanReceipt extends LoanReceipt {
        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    static class CirculationLedger {
        static String branchCode;

        static {
            branchCode = "PT-MAIN";
        }

        static String processNightlyCirculation(LoanReceipt[] receipts) {
            int processed = 0;
            int nullSkipped = 0;
            int referenceOnly = 0;
            int regular = 0;

            for (LoanReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }

            return processed + " processed | " + nullSkipped + " null skipped | "
                    + referenceOnly + " reference-only | " + regular + " regular";
        }
    }

    public static void main(String[] args) {
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED"; // mutating the returned array must not reach the real internal data
        System.out.println(r.getBookIds()[0]); // BK-100

        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println(java.util.Arrays.toString(r.getBookIds()));         // [BK-100, BK-101]
        System.out.println(java.util.Arrays.toString(corrected.getBookIds())); // [BK-100, BK-102]

        LoanReceipt[] batch = {
                new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
                null,
                new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(CirculationLedger.processNightlyCirculation(batch));
        // 2 processed | 1 null skipped | 1 reference-only | 1 regular
    }
}
