package session_ten_topics.class_problems;

/**
 * Problem 5: Payroll Register System
 * Staff is abstract with its own calculatePay() per type; toString() gives the readable
 * payslip. All staff are kept in one Staff[] array and processed uniformly (polymorphism),
 * so a new staff type needs no change to the register loop.
 */
public class PayrollRegisterDemo {

    static abstract class Staff {
        String name;

        Staff(String name) {
            this.name = name;
        }

        abstract String getType();

        abstract long calculatePay();

        @Override
        public String toString() {
            return "Payslip[name=" + name + ", type=" + getType() + ", pay=" + calculatePay() + "]";
        }
    }

    static class FullTime extends Staff {
        long salary;

        FullTime(String name, long salary) {
            super(name);
            this.salary = salary;
        }

        String getType() {
            return "FullTime";
        }

        long calculatePay() {
            return salary;
        }
    }

    static class PartTime extends Staff {
        long hours;
        long rate;

        PartTime(String name, long hours, long rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        String getType() {
            return "PartTime";
        }

        long calculatePay() {
            return hours * rate;
        }
    }

    static class Intern extends Staff {
        long stipend;

        Intern(String name, long stipend) {
            super(name);
            this.stipend = stipend;
        }

        String getType() {
            return "Intern";
        }

        long calculatePay() {
            return stipend;
        }
    }

    static void process(String[] lines) {
        Staff[] register = new Staff[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String[] t = lines[i].trim().split("\\s+");
            switch (t[0]) {
                case "FullTime":
                    register[i] = new FullTime(t[1], Long.parseLong(t[2]));
                    break;
                case "PartTime":
                    register[i] = new PartTime(t[1], Long.parseLong(t[2]), Long.parseLong(t[3]));
                    break;
                case "Intern":
                    register[i] = new Intern(t[1], Long.parseLong(t[2]));
                    break;
                default:
                    break;
            }
        }

        long total = 0;
        Staff top = null;

        for (Staff staff : register) {
            if (staff == null) {
                continue;
            }
            System.out.println(staff);
            total += staff.calculatePay();
            if (top == null || staff.calculatePay() > top.calculatePay()) {
                top = staff;
            }
        }

        System.out.println("Total " + total);
        System.out.println("top earner " + (top == null ? "none" : top.name));
    }

    public static void main(String[] args) {
        String[] input = {
                "FullTime Asha 50000",
                "PartTime Ravi 80 300",
                "Intern Neha 15000"
        };
        process(input);
        // Payslip[name=Asha, type=FullTime, pay=50000]
        // Payslip[name=Ravi, type=PartTime, pay=24000]
        // Payslip[name=Neha, type=Intern, pay=15000]
        // Total 89000
        // top earner Asha
    }
}
