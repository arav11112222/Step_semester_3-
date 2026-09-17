package session_six_topics.assignment_problems;

import java.util.Arrays;

/**
 * PROBLEM 3: The Premium Loyalty Discount & Late-Fee Ledger
 * PremiumMember overrides chargeLateFee(), halving the amount and reusing the parent's
 * own deduction/recording logic via super.chargeLateFee(...). getLateFeeHistory() always
 * returns a defensive copy.
 */
public class PremiumLoyaltyDiscountLedgerDemo {

    static class GymMember {
        private String memberId;
        private int monthlyFee;
        private int[] lateFeeHistory = new int[10];
        private int feeCount = 0;

        public GymMember(String memberId, int monthlyFee) {
            if (memberId == null || memberId.trim().isEmpty() || memberId.trim().length() < 4) {
                throw new IllegalArgumentException("construction rejected");
            }
            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        protected void chargeLateFee(int amount) {
            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }

        int[] getLateFeeHistory() {
            int[] copy = new int[feeCount];
            System.arraycopy(lateFeeHistory, 0, copy, 0, feeCount);
            return copy;
        }

        int getTotalLateFees() {
            int total = 0;
            for (int i = 0; i < feeCount; i++) {
                total += lateFeeHistory[i];
            }
            return total;
        }
    }

    static class PremiumMember extends GymMember {
        String trainerName;

        public PremiumMember(String memberId, int monthlyFee, String trainerName) {
            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {
        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");
        p.chargeLateFee(200);
        System.out.println(p.getTotalLateFees()); // 100

        int[] history = p.getLateFeeHistory();
        history[0] = 999; // must not touch the real internal history
        System.out.println(Arrays.toString(p.getLateFeeHistory())); // [100]
    }
}
