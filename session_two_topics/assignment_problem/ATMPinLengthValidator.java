package session_two_topics.assignment_problems;

/**
 * Problem 1: ATM PIN Length Validator
 * Checks that a PIN is exactly 4 digits long (length check only, no loop).
 */
public class ATMPinLengthValidator {

    static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        checkPinLength("482");  // Invalid PIN — must be exactly 4 digits.
        checkPinLength("4820"); // PIN length OK.
    }
}
