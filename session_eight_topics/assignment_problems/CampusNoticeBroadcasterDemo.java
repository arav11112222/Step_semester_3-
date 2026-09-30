package session_eight_topics.assignment_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Q5: The Campus Notice Broadcaster
 * NotificationChannel is an interface — NoticeBoard depends only on that abstraction,
 * so a new channel (e.g. WhatsApp) plugs in without touching posting/delivery logic.
 * Notice validates itself (title + at least one department) before NoticeBoard ever
 * tries to deliver it.
 */
public class CampusNoticeBroadcasterDemo {

    interface NotificationChannel {
        String name();

        String send(String recipient, String message);
    }

    static class EmailChannel implements NotificationChannel {
        public String name() {
            return "Email";
        }

        public String send(String recipient, String message) {
            return "[Email \u2192 " + recipient + "] " + message;
        }
    }

    static class SmsChannel implements NotificationChannel {
        public String name() {
            return "SMS";
        }

        public String send(String recipient, String message) {
            return "[SMS \u2192 " + recipient + "] " + message;
        }
    }

    static class AppChannel implements NotificationChannel {
        public String name() {
            return "App";
        }

        public String send(String recipient, String message) {
            return "[App \u2192 " + recipient + "] " + message;
        }
    }

    static class Student {
        String name;
        String department;
        List<NotificationChannel> preferredChannels;

        Student(String name, String department, NotificationChannel... channels) {
            this.name = name;
            this.department = department;
            this.preferredChannels = new ArrayList<>();
            for (NotificationChannel c : channels) {
                preferredChannels.add(c);
            }
        }
    }

    static class Notice {
        String title;
        List<String> targetDepartments;

        Notice(String title, String... targetDepartments) {
            this.title = title;
            this.targetDepartments = new ArrayList<>();
            for (String d : targetDepartments) {
                this.targetDepartments.add(d);
            }
        }

        boolean isValid() {
            return title != null && !title.trim().isEmpty() && !targetDepartments.isEmpty();
        }
    }

    static class NoticeBoard {
        List<Student> students;

        NoticeBoard(List<Student> students) {
            this.students = students;
        }

        void post(Notice notice) {
            if (notice.title == null || notice.title.trim().isEmpty()) {
                System.out.println("Cannot post notice: A title is required.");
                return;
            }
            if (notice.targetDepartments.isEmpty()) {
                System.out.println("Cannot post notice: At least one target department is required.");
                return;
            }

            System.out.println("Notice '" + notice.title + "' posted to " + String.join(", ", notice.targetDepartments));

            for (Student student : students) {
                if (notice.targetDepartments.contains(student.department)) {
                    for (NotificationChannel channel : student.preferredChannels) {
                        System.out.println(channel.send(student.name, notice.title));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        EmailChannel email = new EmailChannel();
        SmsChannel sms = new SmsChannel();
        AppChannel app = new AppChannel();

        Student asha = new Student("Asha", "CSE", email, app);
        Student ravi = new Student("Ravi", "ECE", sms);

        NoticeBoard board = new NoticeBoard(List.of(asha, ravi));

        board.post(new Notice("Lab Closed Tomorrow", "CSE"));
        // Notice 'Lab Closed Tomorrow' posted to CSE
        // [Email → Asha] Lab Closed Tomorrow
        // [App → Asha] Lab Closed Tomorrow

        board.post(new Notice("Fee Deadline Extended", "CSE", "ECE"));
        // Notice 'Fee Deadline Extended' posted to CSE, ECE
        // [Email → Asha] Fee Deadline Extended
        // [App → Asha] Fee Deadline Extended
        // [SMS → Ravi] Fee Deadline Extended

        board.post(new Notice("Sports Day"));
        // Cannot post notice: At least one target department is required.
    }
}
