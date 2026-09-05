package session_four_topics.class_problems;

/**
 * M2. Payroll Batch Bonus Round
 * this.salary vs the parameter salary is resolved with this inside raiseSalary().
 */
public class EmployeeBonusDemo {

    static class Employee {
        String empId;
        double salary;

        Employee(String empId, double salary) {
            this.empId = empId;
            this.salary = salary;
        }

        void raiseSalary(double salary) {
            this.salary = this.salary + salary;
        }

        void printFinalSalary() {
            System.out.println(empId + " | Final Salary: Rs " + salary);
        }
    }

    public static void main(String[] args) {
        String[] ids = {"E-101", "E-102", "E-103", "E-104"};
        double[] startingSalaries = {40000, 55000, 62000, 48000};

        Employee[] employees = new Employee[ids.length];
        for (int i = 0; i < ids.length; i++) {
            employees[i] = new Employee(ids[i], startingSalaries[i]);
        }

        for (Employee employee : employees) {
            employee.raiseSalary(5000);
            employee.printFinalSalary();
        }
    }
}
