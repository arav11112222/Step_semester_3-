package session_one_topics.class_problems;

/**
 * Problem 5: Reverse Customer Name
 * Returns the reversed name without modifying the original string.
 */
public class ReverseCustomerNameDemo {

    static String reverseCustomerName(String customerName) {
        StringBuilder reversed = new StringBuilder();

        for (int i = customerName.length() - 1; i >= 0; i--) {
            reversed.append(customerName.charAt(i));
        }

        return reversed.toString();
    }

    public static void main(String[] args) {
        String original = "Sunil";
        String reversedName = reverseCustomerName(original);

        System.out.println("Original Name: " + original);
        System.out.println("Reversed Name: " + reversedName);
    }
}
