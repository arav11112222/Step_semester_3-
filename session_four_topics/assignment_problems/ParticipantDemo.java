package session_four_topics.assignment_problems;

/**
 * A1. Overloaded Constructors for Hackathon Registration
 * Solo entries chain to the two-arg constructor with "Unassigned" via this(...).
 */
public class ParticipantDemo {

    static class Participant {
        String name;
        String teamName;
        boolean registered;

        Participant(String name, String teamName) {
            this.name = name;
            this.teamName = teamName;
            this.registered = true;
        }

        Participant(String name) {
            this(name, "Unassigned");
        }

        void printStatus() {
            System.out.println(name + " | " + teamName + " | Registered: " + registered);
        }
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya"};
        String[] teamNames = {"ByteBusters", "", "CodeCrafters", ""};

        for (int i = 0; i < names.length; i++) {
            Participant participant;
            if (teamNames[i] == null || teamNames[i].isEmpty()) {
                participant = new Participant(names[i]);
            } else {
                participant = new Participant(names[i], teamNames[i]);
            }
            participant.printStatus();
        }
    }
}
