package session_two_topics.class_problems;

/**
 * Problem 1: Vowel & Consonant Counter
 * Counts vowels and consonants in a string, ignoring spaces.
 */
public class VowelConsonantCounter {

    static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == ' ') {
                continue;
            }

            char lower = Character.toLowerCase(ch);

            if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        countVowelsAndConsonants("Java Programming"); // Vowels: 5 | Consonants: 10
    }
}
