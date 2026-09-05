package session_two_topics.assignment_problems;

/**
 * Problem 2: Word Reversal Encoder
 * Reverses each word in a sentence individually, keeping word order the same.
 */
public class WordReversalEncoder {

    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder reversedWord = new StringBuilder();

            for (int j = word.length() - 1; j >= 0; j--) {
                reversedWord.append(word.charAt(j));
            }

            result.append(reversedWord);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        System.out.println(result.toString());
        return result.toString();
    }

    public static void main(String[] args) {
        reverseEachWord("hello club"); // olleh bulc
    }
}
