package session_two_topics.assignment_problems;

/**
 * Problem 3: Product Inventory CSV Parser
 * Splits a CSV line into ProductName, SKU, Quantity and prints a formatted record.
 */
public class ProductInventoryCSVParser {

    static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        System.out.println("Product: " + fields[0] + " | SKU: " + fields[1] + " | Qty: " + fields[2]);
    }

    public static void main(String[] args) {
        parseInventoryRecord("Wireless Mouse,WM-2201,150"); // Product: Wireless Mouse | SKU: WM-2201 | Qty: 150
        parseInventoryRecord("Wireless Mouse,150");          // Invalid Record
    }
}
