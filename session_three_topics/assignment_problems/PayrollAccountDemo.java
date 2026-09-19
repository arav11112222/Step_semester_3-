package session_three_topics.assignment_problems;

/**
 * M2. Encapsulated Payroll Account
 * basicSalary and bonus are private; net salary is read-only via getNetSalary().
 */
public class PayrollAccountDemo {

    static class PayrollAccount {
        private double basicSalary;
        private double bonus;

        PayrollAccount(double openingBasicSalary) {
            if (openingBasicSalary < 0) {
                System.out.println("Warning: negative basic salary, starting at 0 instead.");
                this.basicSalary = 0;
            } else {
                this.basicSalary = openingBasicSalary;
            }
            this.bonus = 0;
        }

        void creditBonus(double amount) {
            if (amount <= 0) {
                System.out.println("Bonus credit rejected: amount must be positive");
                return;
            }
            bonus += amount;
            System.out.println("Bonus credited: Rs " + amount);
        }

        void deductTax(double percent) {
            if (percent < 0 || percent > 100) {
                System.out.println("Tax deduction rejected: percent must be between 0 and 100");
                return;
            }
            basicSalary -= basicSalary * (percent / 100);
            System.out.println("Tax deducted: " + (int) percent + "%");
        }

        double getNetSalary() {
            return basicSalary + bonus;
        }
    }

    public static void main(String[] args) {
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());
    }
}
