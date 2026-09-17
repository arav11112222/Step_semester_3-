package session_six_topics.class_problems;

/**
 * PROBLEM 5: Membership Numbers, Renewal Codes & the Nightly Circulation Audit
 * memberNumber is final, assigned once from a shared static counter inside the
 * constructor. isValidRenewalCode() checks format manually with charAt()/Character
 * methods (no regex). borrowBook(String) reuses borrowBook() rather than duplicating
 * the increment logic. processNightlyAudit() is null-safe and dispatches via instanceof.
 */
public class MembershipNumbersRenewalCodesDemo {

    static class LibraryMember {
        private static int counter = 100;

        final String memberNumber;
        private int borrowLimit;
        private int booksBorrowed;

        public LibraryMember(int borrowLimit) {
            counter++;
            this.memberNumber = "LIB-" + counter;
            this.borrowLimit = borrowLimit;
        }

        void borrowBook() {
            booksBorrowed++;
        }

        void borrowBook(String genre) {
            // genre is only recorded for context; the actual count is delegated below
            borrowBook();
        }

        int getBooksBorrowed() {
            return booksBorrowed;
        }

        static int getMembersEnrolled() {
            return counter - 100;
        }

        static boolean isValidRenewalCode(String code) {
            if (code == null || code.length() != 4) {
                return false;
            }
            if (code.charAt(0) != 'R') {
                return false;
            }
            if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
                return false;
            }
            return Character.isUpperCase(code.charAt(3));
        }
    }

    static class FacultyMember extends LibraryMember {
        String department;

        public FacultyMember(int borrowLimit, String department) {
            super(borrowLimit);
            this.department = department;
        }
    }

    static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }
            processed++;
            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | "
                + faculty + " faculty | " + regular + " regular";
    }

    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember(3);
        System.out.println(m1.memberNumber);                 // LIB-101
        System.out.println(LibraryMember.getMembersEnrolled()); // 1

        System.out.println(LibraryMember.isValidRenewalCode("R12A")); // true
        System.out.println(LibraryMember.isValidRenewalCode("R1A"));  // false
        System.out.println(LibraryMember.isValidRenewalCode("X12A")); // false

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed()); // 2

        LibraryMember[] batch = {
                new FacultyMember(5, "Physics"),
                null,
                new LibraryMember(3)
        };
        System.out.println(processNightlyAudit(batch));
        // 2 processed | 1 null skipped | 1 faculty | 1 regular
    }
}
