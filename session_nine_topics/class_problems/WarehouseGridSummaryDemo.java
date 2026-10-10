package session_nine_topics.class_problems;

/**
 * Problem 2: Warehouse Grid Summary
 * One pass over the grid: running total plus the first maximum found (scanning row by row,
 * left to right). Only a strictly greater value replaces the current max, so ties keep the
 * first coordinate.
 *
 * Time: O(m * n)   Additional space: O(1)
 */
public class WarehouseGridSummaryDemo {

    // Returns {totalItems, maxRow, maxCol}
    static int[] warehouseSummary(int[][] grid) {
        int total = 0;
        int maxValue = -1;
        int maxRow = -1;
        int maxCol = -1;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                total += grid[r][c];
                if (grid[r][c] > maxValue) {
                    maxValue = grid[r][c];
                    maxRow = r;
                    maxCol = c;
                }
            }
        }
        return new int[]{total, maxRow, maxCol};
    }

    public static void main(String[] args) {
        int[][] grid = {
                {4, 9, 2},
                {7, 1, 6},
                {3, 12, 5}
        };

        int[] result = warehouseSummary(grid);
        System.out.println("(" + result[0] + ", (" + result[1] + ", " + result[2] + "))"); // (49, (2, 1))
    }
}
