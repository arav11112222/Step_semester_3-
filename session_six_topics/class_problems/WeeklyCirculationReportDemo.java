package session_six_topics.class_problems;

/**
 * PROBLEM 4: The Weekly Circulation Report
 * batchPrint() calls displayInfo() polymorphically (no instanceof/if-else deciding what
 * to print) and only uses instanceof to guard the one place a real downcast is needed:
 * reaching StudentMember's course field.
 */
public class WeeklyCirculationReportDemo {

    static class LibraryMember {
        private String memberId;
        private int borrowLimit;
        private int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
                throw new IllegalArgumentException("construction rejected");
            }
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        int getBooksBorrowed() {
            return booksBorrowed;
        }

        String displayInfo() {
            return "General | Books: " + booksBorrowed;
        }
    }

    static class StudentMember extends LibraryMember {
        String course;

        public StudentMember(String memberId, int borrowLimit, String course) {
            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        String displayInfo() {
            return "Student | Course: " + course + " | Books: " + getBooksBorrowed();
        }
    }

    static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (LibraryMember member : members) {
            sb.append(member.displayInfo()); // polymorphic call, no type check needed here

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member; // guarded downcast
                sb.append(" [Course via downcast: ").append(student.course).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LibraryMember[] members = {
                new LibraryMember("LB5", 3),
                new StudentMember("STU6", 3, "ECE")
        };
        System.out.println(batchPrint(members));
        // General | Books: 0 | Student | Course: ECE | Books: 0 [Course via downcast: ECE] |

        try {
            LibraryMember plain = new LibraryMember("LB6", 3);
            StudentMember bad = (StudentMember) plain; // compiles fine, fails at runtime
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
