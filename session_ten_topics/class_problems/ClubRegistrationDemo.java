package session_ten_topics.class_problems;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem 3: Student Club Registration Management
 * Student overrides equals() and hashCode() using ONLY the roll number, so a HashSet treats
 * two different Student objects with the same roll number as the same member.
 * All ADD operations run first, then the member count is printed, then CONTAINS operations.
 */
public class ClubRegistrationDemo {

    static class Student {
        String rollNumber;
        String name;

        Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Student)) {
                return false;
            }
            return rollNumber.equals(((Student) o).rollNumber);
        }

        @Override
        public int hashCode() {
            return rollNumber.hashCode();
        }
    }

    static void process(String[] operations) {
        Set<Student> members = new HashSet<>();

        for (String op : operations) {
            String[] t = op.trim().split("\\s+", 3);
            if (t[0].equals("ADD")) {
                boolean added = members.add(new Student(t[1], t.length > 2 ? t[2] : ""));
                System.out.println(added ? "Added" : "duplicate rejected");
            }
        }

        System.out.println("member count " + members.size());

        for (String op : operations) {
            String[] t = op.trim().split("\\s+", 3);
            if (t[0].equals("CONTAINS")) {
                boolean found = members.contains(new Student(t[1], t.length > 2 ? t[2] : ""));
                System.out.println("contains: " + found);
            }
        }
    }

    public static void main(String[] args) {
        String[] input = {
                "ADD 21CS01 Asha",
                "ADD 21CS01 Asha",
                "ADD 21CS02 Ravi",
                "CONTAINS 21CS02 Ravi",
                "CONTAINS 21CS03 Priya"
        };
        process(input);
        // Added
        // duplicate rejected
        // Added
        // member count 2
        // contains: true
        // contains: false
    }
}
