package session_nine_topics.assignment_problems;

/**
 * Problem 1: Class Topper Finder
 * Nested loops: total each student's row, and replace the best only when a total is
 * STRICTLY greater, so ties keep the smallest row index.
 *
 * Time: O(m * n)   Additional space: O(1)
 */
public class ClassTopperFinderDemo {

    // Returns {rowIndex, total}
    static int[] findTopper(int[][] marks) {
        int bestRow = 0;
        int bestTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int total = 0;
            for (int j = 0; j < marks[i].length; j++) {
                total += marks[i][j];
            }
            if (total > bestTotal) {
                bestTotal = total;
                bestRow = i;
            }
        }
        return new int[]{bestRow, bestTotal};
    }

    public static void main(String[] args) {
        int[][] marks = {
                {78, 85, 90},
                {88, 92, 79},
                {65, 70, 95}
        };

        int[] topper = findTopper(marks);
        System.out.println("(" + topper[0] + ", " + topper[1] + ")"); // (1, 259)
    }
}
