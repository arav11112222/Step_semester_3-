package session_ten_topics.class_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 1: Bank Account Withdrawal System
 * Account is abstract: each account type only supplies its own lowest allowed balance and
 * its own rejection message, so withdraw() is written once and a new account type needs no
 * change to it.
 *   Savings: balance must stay >= 1000 after withdrawal
 *   Current: balance may go down to -5000 (overdraft)
 */
public class BankAccountWithdrawalDemo {

    static abstract class Account {
        String id;
        int balance;

        Account(String id, int balance) {
            this.id = id;
            this.balance = balance;
        }

        abstract int minAllowedBalance();

        abstract String rejectionReason();

        String withdraw(int amount) {
            if (balance - amount < minAllowedBalance()) {
                return id + " rejected: " + rejectionReason();
            }
            balance -= amount;
            return id + " balance " + balance;
        }
    }

    static class SavingsAccount extends Account {
        SavingsAccount(String id, int balance) {
            super(id, balance);
        }

        int minAllowedBalance() {
            return 1000;
        }

        String rejectionReason() {
            return "minimum balance 1000";
        }
    }

    static class CurrentAccount extends Account {
        CurrentAccount(String id, int balance) {
            super(id, balance);
        }

        int minAllowedBalance() {
            return -5000;
        }

        String rejectionReason() {
            return "overdraft limit 5000";
        }
    }

    static void process(String[] operations) {
        Map<String, Account> accounts = new HashMap<>();

        for (String op : operations) {
            String[] t = op.trim().split("\\s+");
            switch (t[0]) {
                case "Savings":
                    accounts.put(t[1], new SavingsAccount(t[1], Integer.parseInt(t[2])));
                    break;
                case "Current":
                    accounts.put(t[1], new CurrentAccount(t[1], Integer.parseInt(t[2])));
                    break;
                case "WITHDRAW": {
                    Account account = accounts.get(t[1]);
                    if (account == null) {
                        System.out.println("Account not found");
                    } else {
                        System.out.println(account.withdraw(Integer.parseInt(t[2])));
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        String[] input = {
                "Savings S1 5000",
                "WITHDRAW S1 4500",
                "Current C1 2000",
                "WITHDRAW C1 6000",
                "WITHDRAW S1 3000",
                "WITHDRAW C1 2000"
        };
        process(input);
        // S1 rejected: minimum balance 1000
        // C1 balance -4000
        // S1 balance 2000
        // C1 rejected: overdraft limit 5000
    }
}
