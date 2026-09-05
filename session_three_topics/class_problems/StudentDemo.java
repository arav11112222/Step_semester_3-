package session_three_topics.class_problems;

/**
 * M5. Instance vs Static: Splitting a Class Correctly
 * collegeName and studentCount are static (shared), name/attendance are instance fields.
 */
public class StudentDemo {

    static class Student {
        String name;
        int attendance;

        static String collegeName = "SRM Institute of Science and Technology";
        static int studentCount = 0;

        Student(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            studentCount++;
        }

        static void printCollegeInfo() {
            System.out.println(collegeName);
            System.out.println("Students created: " + studentCount);
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("Ravi", 90);
        Student s2 = new Student("Anitha", 85);

        Student.printCollegeInfo();
    }
}
