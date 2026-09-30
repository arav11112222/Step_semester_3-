package session_eight_topics.class_problems;

/**
 * Q2: Employee Leave Request Workflow
 * LeaveRequest owns its own status transitions — approve()/reject() only work from
 * Pending, and there is no way back to Pending once decided. Employee subtypes exist
 * so a future leave-policy difference has somewhere to live without touching LeaveRequest.
 */
public class EmployeeLeaveRequestWorkflowDemo {

    static abstract class Employee {
        String name;

        Employee(String name) {
            this.name = name;
        }
    }

    static class FullTimeEmployee extends Employee {
        FullTimeEmployee(String name) {
            super(name);
        }
    }

    static class PartTimeEmployee extends Employee {
        PartTimeEmployee(String name) {
            super(name);
        }
    }

    static class Contractor extends Employee {
        Contractor(String name) {
            super(name);
        }
    }

    static class LeaveRequest {
        Employee employee;
        String startDate;
        String endDate;
        private String status = "Pending";

        LeaveRequest(Employee employee, String startDate, String endDate) {
            this.employee = employee;
            this.startDate = startDate;
            this.endDate = endDate;
            System.out.println("Leave request submitted for " + employee.name
                    + " (" + startDate + "-" + endDate + "). Status: Pending");
        }

        void approve() {
            if (!status.equals("Pending")) {
                System.out.println("Cannot change leave request status from " + status + " to Approved");
                return;
            }
            status = "Approved";
            System.out.println(employee.name + "'s leave request (" + startDate + "-" + endDate
                    + ") approved. Status: Approved");
        }

        void reject() {
            if (!status.equals("Pending")) {
                System.out.println("Cannot change leave request status from " + status + " to Rejected");
                return;
            }
            status = "Rejected";
            System.out.println(employee.name + "'s leave request (" + startDate + "-" + endDate
                    + ") rejected. Status: Rejected");
        }

        void attemptRevertToPending() {
            if (!status.equals("Pending")) {
                System.out.println("Cannot change leave request status from " + status + " to Pending");
            }
        }
    }

    public static void main(String[] args) {
        FullTimeEmployee john = new FullTimeEmployee("John");
        LeaveRequest johnRequest = new LeaveRequest(john, "Jan 1", "Jan 5");
        johnRequest.approve(); // approved by manager Alice

        PartTimeEmployee jane = new PartTimeEmployee("Jane");
        LeaveRequest janeRequest = new LeaveRequest(jane, "Feb 10", "Feb 11");
        janeRequest.reject(); // rejected by manager Bob

        johnRequest.attemptRevertToPending(); // Cannot change leave request status from Approved to Pending
    }
}
