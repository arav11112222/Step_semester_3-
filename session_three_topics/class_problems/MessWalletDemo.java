package session_three_topics.class_problems;

/**
 * M2. Encapsulated Mess-Card Wallet
 * Balance is private and can only change through validated methods.
 */
public class MessWalletDemo {

    static class MessWallet {
        private double balance;

        MessWallet(double openingBalance) {
            if (openingBalance < 0) {
                System.out.println("Warning: negative opening balance, starting at 0 instead.");
                this.balance = 0;
            } else {
                this.balance = openingBalance;
            }
        }

        void topUp(double amount) {
            if (amount <= 0) {
                System.out.println("Top-up rejected: amount must be positive");
                return;
            }
            balance += amount;
            System.out.println("Balance after top-up: " + balance);
        }

        void deduct(double amount) {
            if (amount > balance) {
                System.out.println("Deduct rejected: insufficient balance");
                return;
            }
            balance -= amount;
            System.out.println("Balance after deduction: " + balance);
        }

        double getBalance() {
            return balance;
        }
    }

    public static void main(String[] args) {
        MessWallet wallet = new MessWallet(500);
        wallet.topUp(200);
        wallet.deduct(1000);
        System.out.println("Final balance: " + wallet.getBalance());
    }
}
