package session_one_topics.class_problems;

/**
 * Problem 2: Palindrome Checker (3 Approaches)
 * Verifies palindrome detection three independent ways: iterative, recursive,
 * and array reversal — all three must always agree.
 */
public class PalindromeCheckerDemo {

    static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    static boolean isPalindromeRecursive(String text) {
        return isPalindromeRecursiveHelper(text, 0, text.length() - 1);
    }

    private static boolean isPalindromeRecursiveHelper(String text, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }
        return isPalindromeRecursiveHelper(text, left + 1, right - 1);
    }

    static boolean isPalindromeArrayReversal(String text) {
        char[] chars = text.toCharArray();
        char[] reversed = new char[chars.length];

        for (int i = 0; i < chars.length; i++) {
            reversed[i] = chars[chars.length - 1 - i];
        }

        return new String(reversed).equals(text);
    }

    private static void printAllApproaches(String text) {
        String iterative = isPalindromeIterative(text) ? "Palindrome" : "Not Palindrome";
        String recursive = isPalindromeRecursive(text) ? "Palindrome" : "Not Palindrome";
        String arrayReversal = isPalindromeArrayReversal(text) ? "Palindrome" : "Not Palindrome";

        System.out.println("Iterative: " + iterative + " | Recursive: " + recursive
                + " | Array Reversal: " + arrayReversal);
    }

    public static void main(String[] args) {
        printAllApproaches("madam"); // all Palindrome
        printAllApproaches("hello"); // all Not Palindrome
    }
}
