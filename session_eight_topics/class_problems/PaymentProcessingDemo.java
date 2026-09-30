package session_eight_topics.class_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Q5: Payment Processing for a Shopping System
 * PaymentMethod is an interface — Order and the payment-attempt logic depend only on
 * that abstraction, so a new method (e.g. BankTransferPayment) plugs in without any
 * change to Order or the processing workflow. A failed payment never flips the order
 * to Paid.
 */
public class PaymentProcessingDemo {

    static class Product {
        String name;
        double price;
        int qty;

        Product(String name, double price, int qty) {
            this.name = name;
            this.price = price;
            this.qty = qty;
        }
    }

    static class Order {
        String customer;
        List<Product> items = new ArrayList<>();
        private String status = "Pending";

        Order(String customer) {
            this.customer = customer;
            System.out.println("Order created for " + customer);
        }

        void addItem(Product product) {
            items.add(product);
        }

        double getTotal() {
            double total = 0;
            for (Product p : items) {
                total += p.price * p.qty;
            }
            return total;
        }

        boolean isEmpty() {
            return items.isEmpty();
        }

        void markPaid() {
            status = "Paid";
        }

        String getStatus() {
            return status;
        }
    }

    interface PaymentMethod {
        String name();

        boolean processPayment(double amount);
    }

    static class CreditCardPayment implements PaymentMethod {
        public String name() {
            return "Credit Card";
        }

        public boolean processPayment(double amount) {
            return true; // simulated success
        }
    }

    static class PayPalPayment implements PaymentMethod {
        private boolean shouldSucceed;

        PayPalPayment(boolean shouldSucceed) {
            this.shouldSucceed = shouldSucceed;
        }

        public String name() {
            return "PayPal";
        }

        public boolean processPayment(double amount) {
            return shouldSucceed;
        }
    }

    static void attemptPayment(Order order, String orderLabel, PaymentMethod method) {
        if (order.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated via " + method.name() + " for Order " + orderLabel);
        boolean success = method.processPayment(order.getTotal());

        if (success) {
            order.markPaid();
            System.out.println("Payment for Order " + orderLabel + " successful. Order status: Paid");
        } else {
            System.out.println("Payment for Order " + orderLabel + " failed. Order status: Pending");
        }
    }

    public static void main(String[] args) {
        Order orderX = new Order("Customer X");
        orderX.addItem(new Product("Product A", 10, 2));
        orderX.addItem(new Product("Product B", 15, 1));
        attemptPayment(orderX, "X", new CreditCardPayment());

        Order orderY = new Order("Customer Y");
        attemptPayment(orderY, "Y", new CreditCardPayment()); // Cannot process payment for an empty order.

        Order orderZ = new Order("Customer Z");
        orderZ.addItem(new Product("Product C", 25, 1));
        attemptPayment(orderZ, "Z", new PayPalPayment(false)); // simulated failure
    }
}
