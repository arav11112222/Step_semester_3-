package session_one_topics.assignment_problems;

/**
 * Problem 3: The Traffic Signal Streak Analyzer
 * Scans a signal log and tracks the longest run of consecutive identical characters.
 */
public class TrafficSignalStreakAnalyzerDemo {

    static void findLongestStreak(String signalLog) {
        char longestChar = signalLog.charAt(0);
        int longestLength = 1;

        char currentChar = signalLog.charAt(0);
        int currentLength = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentLength++;
            } else {
                currentChar = signalLog.charAt(i);
                currentLength = 1;
            }

            if (currentLength > longestLength) {
                longestLength = currentLength;
                longestChar = currentChar;
            }
        }

        System.out.println("Longest Streak: '" + longestChar + "' repeated " + longestLength + " times");
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR"); // Longest Streak: 'G' repeated 3 times
        findLongestStreak("RRRRYYGG"); // Longest Streak: 'R' repeated 4 times
    }
}
