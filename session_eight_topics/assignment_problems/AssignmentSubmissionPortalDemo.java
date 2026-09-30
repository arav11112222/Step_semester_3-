package session_eight_topics.assignment_problems;

/**
 * Q2: The Assignment Submission Portal
 * Assignment is abstract with its own applyLatePenalty() rule, so a new type
 * (e.g. Practical) plugs in without touching Submission's grading workflow.
 * Submission privately owns its status — grade() only works from Submitted,
 * and once Graded, resubmission is blocked.
 */
public class AssignmentSubmissionPortalDemo {

    static abstract class Assignment {
        String title;
        int maxMarks;
        int dueDay; // simplified day-of-month for late-day math

        Assignment(String title, int maxMarks, int dueDay) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDay = dueDay;
        }

        abstract double applyLatePenalty(double awardedMarks, int lateDays);
    }

    static class CodingAssignment extends Assignment {
        CodingAssignment(String title, int maxMarks, int dueDay) {
            super(title, maxMarks, dueDay);
        }

        @Override
        double applyLatePenalty(double awardedMarks, int lateDays) {
            double penaltyRate = 0.10 * lateDays;
            return awardedMarks * (1 - penaltyRate);
        }
    }

    static class WrittenAssignment extends Assignment {
        WrittenAssignment(String title, int maxMarks, int dueDay) {
            super(title, maxMarks, dueDay);
        }

        @Override
        double applyLatePenalty(double awardedMarks, int lateDays) {
            double penaltyRate = 0.20 * lateDays;
            return awardedMarks * (1 - penaltyRate);
        }
    }

    static class Submission {
        String student;
        Assignment assignment;
        int submissionDay;
        private String status = "Submitted";
        private double finalMarks = -1;

        Submission(String student, Assignment assignment, int submissionDay) {
            this.student = student;
            this.assignment = assignment;
            this.submissionDay = submissionDay;

            int lateDays = Math.max(0, submissionDay - assignment.dueDay);
            String onTimeNote = (lateDays == 0) ? "on time" : (lateDays + " days late");
            System.out.println(student + "'s submission for '" + assignment.title
                    + "' received (" + onTimeNote + "). Status: Submitted");
        }

        void grade(double awardedMarks) {
            if (!status.equals("Submitted")) {
                System.out.println("Cannot grade: '" + assignment.title + "' is not in Submitted state.");
                return;
            }

            int lateDays = Math.max(0, submissionDay - assignment.dueDay);

            if (lateDays == 0) {
                finalMarks = awardedMarks;
                status = "Graded";
                System.out.println(student + " graded: " + (int) finalMarks + "/" + assignment.maxMarks
                        + ". Status: Graded");
            } else {
                double penaltyPercent = lateDays * (assignment instanceof CodingAssignment ? 10 : 20);
                finalMarks = assignment.applyLatePenalty(awardedMarks, lateDays);
                status = "Graded";
                System.out.println(student + " graded: " + (int) finalMarks + "/" + assignment.maxMarks
                        + " after " + penaltyPercent + "% late penalty. Status: Graded");
            }
        }

        void resubmit() {
            if (status.equals("Graded")) {
                System.out.println("Cannot resubmit: '" + assignment.title + "' has already been graded.");
            }
        }
    }

    public static void main(String[] args) {
        CodingAssignment codingLab = new CodingAssignment("Linked List Lab", 50, 10);
        WrittenAssignment essay = new WrittenAssignment("Design Essay", 50, 12);

        Submission ashaSub = new Submission("Asha", codingLab, 10); // on time
        Submission raviSub = new Submission("Ravi", essay, 14);     // 2 days late

        ashaSub.grade(45); // 45/50, no penalty
        raviSub.grade(40); // 24/50 after 40% late penalty

        ashaSub.resubmit(); // Cannot resubmit: 'Linked List Lab' has already been graded.
    }
}
