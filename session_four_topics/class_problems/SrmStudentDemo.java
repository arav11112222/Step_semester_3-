package session_four_topics.class_problems;

/**
 * M4. One-Time College Setup, Many Students
 * The static block runs exactly once, the first time the class is loaded, no matter
 * how many SrmStudent objects are created afterward.
 */
public class SrmStudentDemo {

    static class SrmStudent {
        static String collegeName;
        static String academicYear;

        static {
            collegeName = "SRM Institute of Science and Technology";
            academicYear = "2026-2027";
            System.out.println("College info loaded");
        }

        String name;

        SrmStudent(String name) {
            this.name = name;
            System.out.println("Student record created: " + name);
        }
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names) {
            new SrmStudent(name);
        }
    }
}
