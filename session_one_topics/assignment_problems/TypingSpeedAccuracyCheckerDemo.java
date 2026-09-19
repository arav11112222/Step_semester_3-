package session_one_topics.assignment_problems;

/**
 * Problem 2: The Typing Speed Test Accuracy Checker
 * Compares two equal-length strings position by position, reporting accuracy % and
 * the position of the first mismatch.
 */
public class TypingSpeedAccuracyCheckerDemo {

    static void checkTypingAccuracy(String original, String typed) {
        int total = original.length();
        int matched = 0;
        int firstMismatchPos = -1;
        char originalMismatchChar = ' ';
        char typedMismatchChar = ' ';

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // report as a 1-based position
                originalMismatchChar = original.charAt(i);
                typedMismatchChar = typed.charAt(i);
            }
        }

        double accuracy = (matched / (double) total) * 100;

        if (firstMismatchPos == -1) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches%n", matched, total, accuracy);
        } else {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')%n",
                    matched, total, accuracy, firstMismatchPos, originalMismatchChar, typedMismatchChar);
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        // Matched: 10/11 | Accuracy: 90.91% | First Mismatch at position 11 ('d' vs 't')

        checkTypingAccuracy("coding", "coding");
        // Matched: 6/6 | Accuracy: 100.00% | No Mismatches
    }
}
