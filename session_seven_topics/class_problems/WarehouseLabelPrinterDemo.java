package session_seven_topics.class_problems;

/**
 * PROBLEM 2: Warehouse Label Printer
 * PackageBox and Invoice share nothing else — Printable is the only thing that lets
 * one method (printAll) work on both.
 */
public class WarehouseLabelPrinterDemo {

    interface Printable {
        String printLabel();
    }

    static class PackageBox implements Printable {
        String trackingId;

        public PackageBox(String trackingId) {
            this.trackingId = trackingId;
        }

        @Override
        public String printLabel() {
            return "Package label: " + trackingId;
        }
    }

    static class Invoice implements Printable {
        String invoiceNumber;

        public Invoice(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        @Override
        public String printLabel() {
            return "Invoice label: " + invoiceNumber;
        }
    }

    static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }

    public static void main(String[] args) {
        PackageBox p = new PackageBox("TRK-88");
        System.out.println(p.printLabel()); // Package label: TRK-88

        Invoice i = new Invoice("INV-42");
        System.out.println(i.printLabel()); // Invoice label: INV-42

        printAll(new Printable[]{p, i});
        // Package label: TRK-88
        // Invoice label: INV-42
    }
}
