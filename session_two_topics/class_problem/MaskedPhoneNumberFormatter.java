package session_two_topics.class_problems;

/**
 * Problem 4: Masked Phone Number Formatter
 * Validates a 10-digit phone number and masks all but the last 4 digits.
 */
public class MaskedPhoneNumberFormatter {

    static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            System.out.println("Invalid phone number");
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                System.out.println("Invalid phone number");
                return "Invalid phone number";
            }
        }

        String lastFour = phone.substring(6);

        StringBuilder sb = new StringBuilder("XXXXXX");
        sb.append("-").append(lastFour);

        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        maskPhoneNumber("9876543210"); // XXXXXX-3210
        maskPhoneNumber("98765");      // Invalid phone number
    }
}
