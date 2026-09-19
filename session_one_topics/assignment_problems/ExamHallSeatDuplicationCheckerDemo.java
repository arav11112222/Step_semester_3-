package session_one_topics.assignment_problems;

/**
 * Problem 1: The Exam Hall Seat Duplication Checker
 * Compares every seat against every other seat (arrays and loops only, no Collections).
 */
public class ExamHallSeatDuplicationCheckerDemo {

    static void checkDuplicateSeats(int[] seatNumbers) {
        boolean foundDuplicate = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    foundDuplicate = true;
                }
            }
        }

        if (!foundDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        checkDuplicateSeats(new int[]{101, 102, 103, 102, 105}); // Duplicate Seat Number Found: 102
        checkDuplicateSeats(new int[]{101, 102, 103, 104, 105}); // No Duplicate Seats Found
    }
}
