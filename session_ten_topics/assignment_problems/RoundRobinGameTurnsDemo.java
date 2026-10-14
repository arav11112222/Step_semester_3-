package session_ten_topics.assignment_problems;

/**
 * Problem 5: Round-Robin Game Turns
 * Circular singly linked list that keeps only a tail reference. The head is tail.next, so
 * walking with next wraps around by itself.
 *
 * addLast:    O(1)   (new node goes between tail and head, then becomes the tail)
 * printTurns: O(turns)
 */
public class RoundRobinGameTurnsDemo {

    static class Player {
        String name;
        Player next;

        Player(String name) {
            this.name = name;
        }
    }

    static class TurnCircle {
        Player tail;

        void addLast(String name) {
            Player player = new Player(name);
            if (tail == null) {
                player.next = player; // a single node points to itself
            } else {
                player.next = tail.next; // new node points to the head
                tail.next = player;
            }
            tail = player;
        }

        void printTurns(int turns) {
            if (tail == null) {
                System.out.println();
                return;
            }

            StringBuilder sb = new StringBuilder();
            Player current = tail.next; // head
            for (int i = 0; i < turns; i++) {
                sb.append(current.name);
                if (i < turns - 1) {
                    sb.append(" ");
                }
                current = current.next;
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        TurnCircle circle = new TurnCircle();
        circle.addLast("Asha");
        circle.addLast("Ravi");
        circle.addLast("Neha");

        circle.printTurns(7); // Asha Ravi Neha Asha Ravi Neha Asha
    }
}
