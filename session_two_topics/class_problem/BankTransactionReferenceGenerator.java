package session_two_topics.class_problems;

/**
 * Problem 5: Bank Transaction Reference Generator & Validator
 * Normalizes and validates a 14-char reference: 3 letters (bank code) + 6 digits (ddMMyy) + 5 digits (sequence).
 */
public class BankTransactionReferenceGenerator {

    static String normalizeReference(String raw) {
        String trimmed = raw.trim();

        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }

        String bankCodePart = trimmed.substring(0, 3).toUpperCase();
        String remainder = trimmed.substring(3);

        return bankCodePart + remainder;
    }

    static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        String bankCode = reference.substring(0, 3);
        for (int i = 0; i < bankCode.length(); i++) {
            if (!Character.isLetter(bankCode.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        String body = reference.substring(3);
        for (int i = 0; i < body.length(); i++) {
            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: reference body must be digits";
            }
        }

        String date = reference.substring(3, 9);
        String sequence = reference.substring(9, 14);

        String dd = date.substring(0, 2);
        String mm = date.substring(2, 4);
        String yy = date.substring(4, 6);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ")
          .append(dd).append("/").append(mm).append("/").append(yy)
          .append(" | SEQ: ").append(sequence);

        return sb.toString();
    }

    public static void main(String[] args) {
        String raw1 = " hdf03022600042 ";
        String normalized1 = normalizeReference(raw1);
        System.out.println(validateAndFormat(normalized1)); // [HDF] DATE: 03/02/26 | SEQ: 00042

        String raw2 = "12F03022600042";
        String normalized2 = normalizeReference(raw2);
        System.out.println(validateAndFormat(normalized2)); // Invalid: bank code must be 3 letters
    }
}
