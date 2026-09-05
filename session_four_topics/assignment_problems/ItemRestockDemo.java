package session_four_topics.assignment_problems;

/**
 * A2. this Keyword for Canteen Inventory — Batch Restock
 * Both the constructor and restock() resolve a field/parameter naming clash using this.
 */
public class ItemRestockDemo {

    static class Item {
        String itemName;
        int stock;

        Item(String itemName, int stock) {
            this.itemName = itemName;
            this.stock = stock;
        }

        void restock(int stock) {
            this.stock = this.stock + stock;
        }
    }

    public static void main(String[] args) {
        String[] names = {"Samosa", "Tea Powder", "Bread", "Biscuit Packs"};
        int[] startingStock = {15, 40, 8, 25};

        Item[] items = new Item[names.length];
        for (int i = 0; i < names.length; i++) {
            items[i] = new Item(names[i], startingStock[i]);
        }

        for (Item item : items) {
            item.restock(20);
            System.out.println(item.itemName + " | Final Stock: " + item.stock);
        }
    }
}
