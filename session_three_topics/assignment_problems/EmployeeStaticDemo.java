package session_three_topics.assignment_problems;

/**
 * M5. Instance vs Static: Splitting an Employee Class Correctly
 * companyName and employeeCount are static (shared); empName/salary are instance fields.
 */
public class EmployeeStaticDemo {

    static class Employee {
        String empName;
        double salary;

        static String companyName = "Bright Horizon Technologies";
        static int employeeCount = 0;

        Employee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }

        static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Divya", 30000);
        Employee e2 = new Employee("Arjun", 32000);
        Employee e3 = new Employee("Karthik", 35000);

        Employee.printCompanyInfo();
    }
}
