package session_nine_topics.class_problems;

/**
 * Problem 1: Library Catalog Lookup
 * Binary search on a catalog sorted by ISBN. ISBNs are fixed-length numeric strings,
 * so String.compareTo() orders them the same way as their numeric value.
 *
 * Time: O(log n)   Additional space: O(1)
 */
public class LibraryCatalogLookupDemo {

    static String findBook(String[][] catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = catalog[mid][0].compareTo(targetIsbn);

            if (cmp == 0) {
                return catalog[mid][1];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[][] catalog = {
                {"0001112223", "Introduction to Algebra"},
                {"0002223334", "Beginning Python"},
                {"0003334445", "Classic Mythology"},
                {"0004445556", "Data and Society"},
                {"0005556667", "European History"}
        };

        System.out.println(findBook(catalog, "0003334445")); // Classic Mythology
        System.out.println(findBook(catalog, "0009998887")); // Not Found
    }
}
