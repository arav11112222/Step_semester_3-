package session_four_topics.class_problems;

/**
 * M5. Account Batch Payments
 * processPayment() uses instanceof to dispatch between HostelFeeAccount and a plain FeeAccount.
 */
public class AccountBatchPaymentsDemo {

    static class FeeAccount {
    }

    static class HostelFeeAccount extends FeeAccount {
    }

    static class PaymentProcessor {
        int hostelCount = 0;
        int dayScholarCount = 0;

        void processPayment(FeeAccount account, double amount) {
            if (account instanceof HostelFeeAccount) {
                hostelCount++;
                System.out.println("Paid in two installments (hostel account)");
            } else {
                dayScholarCount++;
                System.out.println("Paid in one go (day-scholar account)");
            }
        }

        void printCounts() {
            System.out.println("Hostel accounts processed: " + hostelCount
                    + " | Day-scholar accounts processed: " + dayScholarCount);
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
                new HostelFeeAccount(),
                new HostelFeeAccount(),
                new FeeAccount(),
                new FeeAccount()
        };

        PaymentProcessor processor = new PaymentProcessor();

        for (FeeAccount account : accounts) {
            processor.processPayment(account, 60000);
        }

        processor.printCounts();
    }
}
