package session_four_topics.class_problems;

/**
 * M3. Late Fees — Skip the On-Time Accounts
 * calculateLateFee() and printSummary() are final so the fee formula can never be overridden.
 * Late fee = totalFee * (daysLate / 100.0), skipped entirely when daysLate <= 0.
 */
public class LateFeeDemo {

    static class Account {
        String regNo;
        double totalFee;

        Account(String regNo, double totalFee) {
            this.regNo = regNo;
            this.totalFee = totalFee;
        }

        final double calculateLateFee(int daysLate) {
            return totalFee * (daysLate / 100.0);
        }

        final void printSummary(int daysLate) {
            if (daysLate <= 0) {
                System.out.println(regNo + " - On time, no late fee");
                return;
            }
            double lateFee = calculateLateFee(daysLate);
            System.out.println(regNo + " | Total Fee: Rs " + totalFee + " | Late Fee: Rs " + lateFee);
        }
    }

    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000, 150000, 180000, 220000};
        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < regNos.length; i++) {
            Account account = new Account(regNos[i], totalFees[i]);
            account.printSummary(daysLate[i]);
        }
    }
}
