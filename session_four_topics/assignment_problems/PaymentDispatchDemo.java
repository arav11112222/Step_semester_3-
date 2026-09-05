package session_four_topics.assignment_problems;

/**
 * A5. instanceof Inside a Loop — Canteen Closing-Time Payment Dispatch
 * processTransaction() uses instanceof to detect CardPayment and add a 2% processing fee.
 */
public class PaymentDispatchDemo {

    static class Payment {
        void pay(double amount) {
            System.out.println("Paid (cash): Rs " + amount);
        }
    }

    static class CardPayment extends Payment {
        double payWithProcessingFee(double amount) {
            double totalCharged = amount + (amount * 0.02);
            System.out.println("Charged (card, incl. fee): Rs " + totalCharged);
            return totalCharged;
        }
    }

    static class PaymentProcessor {
        double totalCollected = 0;

        void processTransaction(Payment payment, double amount) {
            if (payment instanceof CardPayment) {
                CardPayment cardPayment = (CardPayment) payment;
                totalCollected += cardPayment.payWithProcessingFee(amount);
            } else {
                payment.pay(amount);
                totalCollected += amount;
            }
        }

        void printTotal() {
            System.out.println("Total Collected: Rs " + totalCollected);
        }
    }

    public static void main(String[] args) {
        Payment[] payments = {
                new CardPayment(),
                new Payment(),
                new CardPayment(),
                new Payment(),
                new CardPayment()
        };
        double[] amounts = {100, 50, 200, 75, 120};

        PaymentProcessor processor = new PaymentProcessor();

        for (int i = 0; i < payments.length; i++) {
            processor.processTransaction(payments[i], amounts[i]);
        }

        processor.printTotal();
    }
}
