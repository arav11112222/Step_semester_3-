package session_ten_topics.class_problems;

import java.util.Locale;

/**
 * Problem 4: Class Marks Grid Analysis
 * Marks are stored in a 2D array (one row per student, 3 subject columns).
 * Row sums give each student's total, column sums give subject averages, and the topper is
 * the first row with the strictly greatest total.
 * Time: O(m * n)   Additional space: O(m + n)
 */
public class ClassMarksGridAnalysisDemo {

    static void analyze(String[] lines) {
        int students = lines.length;
        int subjects = 3;

        String[] names = new String[students];
        int[][] marks = new int[students][subjects];

        for (int i = 0; i < students; i++) {
            int open = lines[i].indexOf('[');
            int close = lines[i].indexOf(']');
            names[i] = lines[i].substring(0, open).trim();

            String[] parts = lines[i].substring(open + 1, close).split(",");
            for (int j = 0; j < subjects; j++) {
                marks[i][j] = Integer.parseInt(parts[j].trim());
            }
        }

        int[] totals = new int[students];
        int[] subjectSums = new int[subjects];
        int topperIndex = 0;

        for (int i = 0; i < students; i++) {
            for (int j = 0; j < subjects; j++) {
                totals[i] += marks[i][j];
                subjectSums[j] += marks[i][j];
            }
            if (totals[i] > totals[topperIndex]) {
                topperIndex = i;
            }
        }

        StringBuilder totalLine = new StringBuilder("Totals ");
        for (int i = 0; i < students; i++) {
            totalLine.append(names[i]).append(" ").append(totals[i]);
            if (i < students - 1) {
                totalLine.append(", ");
            }
        }
        System.out.println(totalLine);

        StringBuilder avgLine = new StringBuilder("averages ");
        for (int j = 0; j < subjects; j++) {
            avgLine.append(String.format(Locale.US, "%.2f", subjectSums[j] / (double) students));
            if (j < subjects - 1) {
                avgLine.append(", ");
            }
        }
        System.out.println(avgLine);

        System.out.println("topper " + names[topperIndex] + " (" + totals[topperIndex] + ")");
    }

    public static void main(String[] args) {
        String[] input = {
                "Asha [78, 85, 90]",
                "Ravi [88, 92, 79]",
                "Neha [65, 70, 95]"
        };
        analyze(input);
        // Totals Asha 253, Ravi 259, Neha 230
        // averages 77.00, 82.33, 88.00
        // topper Ravi (259)
    }
}
