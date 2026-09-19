package session_one_topics.assignment_problems;

/**
 * Problem 5: The Movie Review Word Length Profiler
 * Splits a review into words and classifies each as Short (1-4), Medium (5-8), or Long (9+).
 */
public class MovieReviewWordLengthProfilerDemo {

    static void classifyWordLengths(String review) {
        String[] words = review.split("\\s+");

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            int len = word.length();
            if (len >= 1 && len <= 4) {
                shortCount++;
            } else if (len <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }

        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    public static void main(String[] args) {
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
        // Short: 3 | Medium: 1 | Long: 3
    }
}
