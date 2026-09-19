package session_two_topics.class_problems;

/**
 * Problem 2: CSV Student Record Parser
 * Splits a CSV line into Name, RollNumber, Department and prints a formatted record.
 */
public class CSVStudentRecordParser {

    static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Name: " + fields[0] + " | Roll No: " + fields[1] + " | Dept: " + fields[2]);
    }

    public static void main(String[] args) {
        parseStudentRecord("Ananya Verma,RA2211003010123,CSE"); // Name: Ananya Verma | Roll No: RA2211003010123 | Dept: CSE
        parseStudentRecord("Ananya Verma,CSE");                 // Invalid Record
    }
}
