package session_eight_topics.class_problems;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Q3: Online Examination System
 * Question is abstract with its own evaluate()/getPoints(), so a new question type
 * (e.g. ShortAnswerQuestion) plugs in without changing Attempt's submission logic.
 * Attempt guards its own state — answers can't change once Submitted.
 */
public class OnlineExaminationSystemDemo {

    static abstract class Question {
        String label;
        int points;

        Question(String label, int points) {
            this.label = label;
            this.points = points;
        }

        abstract boolean evaluate(String answer);

        int getPoints() {
            return points;
        }
    }

    static class MultipleChoiceQuestion extends Question {
        String correctOption;

        MultipleChoiceQuestion(String label, int points, String correctOption) {
            super(label, points);
            this.correctOption = correctOption;
        }

        @Override
        boolean evaluate(String answer) {
            return correctOption.equals(answer);
        }
    }

    static class TrueFalseQuestion extends Question {
        boolean correctValue;

        TrueFalseQuestion(String label, int points, boolean correctValue) {
            super(label, points);
            this.correctValue = correctValue;
        }

        @Override
        boolean evaluate(String answer) {
            return String.valueOf(correctValue).equalsIgnoreCase(answer);
        }
    }

    static class Attempt {
        String student;
        String examName;
        private String status = "InProgress";
        private Map<Question, String> answers = new LinkedHashMap<>();

        Attempt(String student, String examName) {
            this.student = student;
            this.examName = examName;
            System.out.println(examName + " started by " + student);
        }

        void recordAnswer(Question question, String answer) {
            if (!status.equals("InProgress")) {
                System.out.println("Cannot change answers for a submitted examination.");
                return;
            }
            answers.put(question, answer);
            System.out.println("Answer recorded for " + question.label);
        }

        void submit() {
            status = "Submitted";
            System.out.println(examName + " submitted by " + student);

            int total = 0;
            int maxTotal = 0;
            StringBuilder sb = new StringBuilder("Result: ");
            boolean first = true;

            for (Map.Entry<Question, String> entry : answers.entrySet()) {
                Question q = entry.getKey();
                boolean correct = q.evaluate(entry.getValue());
                int earned = correct ? q.getPoints() : 0;
                total += earned;
                maxTotal += q.getPoints();

                if (!first) {
                    sb.append(", ");
                }
                sb.append(q.label).append(": ").append(correct ? "Correct" : "Incorrect")
                        .append(" (").append(earned).append(" points)");
                first = false;
            }
            sb.append(". Total score: ").append(total).append("/").append(maxTotal);
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        MultipleChoiceQuestion q1 = new MultipleChoiceQuestion("Question 1", 5, "C");
        TrueFalseQuestion q2 = new TrueFalseQuestion("Question 2", 5, false);

        Attempt attempt = new Attempt("Student 1", "Exam A");
        attempt.recordAnswer(q1, "C");
        attempt.recordAnswer(q2, "True");
        attempt.submit();
        // Result: Question 1: Correct (5 points), Question 2: Incorrect (0 points). Total score: 5/10

        attempt.recordAnswer(q1, "B"); // Cannot change answers for a submitted examination.
    }
}
