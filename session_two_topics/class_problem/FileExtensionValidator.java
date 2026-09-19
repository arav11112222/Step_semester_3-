package session_two_topics.class_problems;

/**
 * Problem 3: File Extension Validator
 * Validates a filename's extension against an accepted list (pdf, docx, zip), case-insensitively.
 */
public class FileExtensionValidator {

    static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            String result = "Rejected — invalid file type";
            System.out.println(result);
            return result;
        }

        String extension = filename.substring(dotIndex + 1);

        if (extension.equalsIgnoreCase("pdf")
                || extension.equalsIgnoreCase("docx")
                || extension.equalsIgnoreCase("zip")) {
            System.out.println("Accepted");
            return "Accepted";
        } else {
            String result = "Rejected — invalid file type";
            System.out.println(result);
            return result;
        }
    }

    public static void main(String[] args) {
        validateFileExtension("Assignment1.PDF"); // Accepted
        validateFileExtension("notes.txt");       // Rejected — invalid file type
    }
}
