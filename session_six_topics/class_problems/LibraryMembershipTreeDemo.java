package session_six_topics.class_problems;

/**
 * PROBLEM 2: Three Branches of the Membership Tree
 * HonorsStudentMember extends StudentMember (multilevel, 3 deep).
 * FacultyMember extends LibraryMember directly (hierarchical sibling of StudentMember).
 * classifyGeneration() uses instanceof only; getTotalBooksBorrowed() sums polymorphically.
 */
public class LibraryMembershipTreeDemo {

    static class LibraryMember {
        private String memberId;
        protected int borrowLimit;
        private int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
                throw new IllegalArgumentException("construction rejected");
            }
            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        void borrowBook() {
            booksBorrowed++;
        }

        int getBooksBorrowed() {
            return booksBorrowed;
        }

        String displayInfo() {
            return "General Member | Books Borrowed: " + booksBorrowed;
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
            return "Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    static class HonorsStudentMember extends StudentMember {
        int bonusLimit;

        public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
            super(memberId, borrowLimit, course);
            this.bonusLimit = bonusLimit;
        }

        @Override
        String displayInfo() {
            return "Honors Student Member | Course: " + course + " | Bonus Limit: " + bonusLimit
                    + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    static class FacultyMember extends LibraryMember {
        String department;

        public FacultyMember(String memberId, int borrowLimit, String department) {
            super(memberId, borrowLimit);
            this.department = department;
        }

        @Override
        String displayInfo() {
            return "Faculty Member | Department: " + department + " | Books Borrowed: " + getBooksBorrowed();
        }
    }

    static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Direct subclass";
        } else {
            return "Base class";
        }
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            total += member.getBooksBorrowed(); // polymorphic dispatch — no type checks needed
        }
        return total;
    }

    public static void main(String[] args) {
        LibraryMember general = new LibraryMember("STU1", 3);
        StudentMember student = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember honors = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember faculty = new FacultyMember("STU4", 5, "Physics");

        System.out.println(general.displayInfo()); // General Member | Books Borrowed: 0
        System.out.println(student.displayInfo());  // Student Member | Course: CSE | Books Borrowed: 0
        System.out.println(honors.displayInfo());    // Honors Student Member | Course: ECE | Bonus Limit: 2 | Books Borrowed: 0
        System.out.println(faculty.displayInfo());   // Faculty Member | Department: Physics | Books Borrowed: 0

        System.out.println(classifyGeneration(honors));  // Multilevel descendant (3 generations deep)
        System.out.println(classifyGeneration(faculty)); // Hierarchical sibling (independent branch)

        student.borrowBook();
        student.borrowBook();
        honors.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        System.out.println(getTotalBooksBorrowed(new LibraryMember[]{student, honors, faculty})); // 6
    }
}
