package session_one_topics.class_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 4: First Non-Repeating Character
 * Counts character frequency, then scans left-to-right for the first char with frequency 1.
 */
public class FirstNonRepeatingCharDemo {

    private static final char NOT_FOUND = '\0';

    static char findFirstNonRepeatingChar(String text) {
        Map<Character, Integer> frequency = new HashMap<>();

        for (char c : text.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        for (char c : text.toCharArray()) {
            if (frequency.get(c) == 1) {
                return c;
            }
        }

        return NOT_FOUND;
    }

    public static void main(String[] args) {
        char result1 = findFirstNonRepeatingChar("swiss");
        if (result1 != NOT_FOUND) {
            System.out.println("First Non-Repeating Character: '" + result1 + "'"); // 'w'
        } else {
            System.out.println("No Non-Repeating Character Found");
        }

        char result2 = findFirstNonRepeatingChar("aabbcc");
        if (result2 != NOT_FOUND) {
            System.out.println("First Non-Repeating Character: '" + result2 + "'");
        } else {
            System.out.println("No Non-Repeating Character Found"); // this one
        }
    }
}
