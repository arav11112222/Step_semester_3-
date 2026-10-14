package session_ten_topics.assignment_problems;

/**
 * Problem 4: Train Coaches Both Ways
 * Doubly linked list with head and tail references.
 *
 * Finding a coach by name: O(n)
 * Unlinking it once found:  O(1)  (just fix its two neighbours, with care at head and tail)
 */
public class TrainCoachesBothWaysDemo {

    static class Coach {
        String name;
        Coach prev;
        Coach next;

        Coach(String name) {
            this.name = name;
        }
    }

    static class Train {
        Coach head;
        Coach tail;

        void addLast(String name) {
            Coach coach = new Coach(name);
            if (head == null) {
                head = coach;
                tail = coach;
            } else {
                coach.prev = tail;
                tail.next = coach;
                tail = coach;
            }
        }

        boolean remove(String name) {
            Coach current = head;
            while (current != null && !current.name.equals(name)) {
                current = current.next;
            }
            if (current == null) {
                return false;
            }

            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                head = current.next; // removing the head
            }

            if (current.next != null) {
                current.next.prev = current.prev;
            } else {
                tail = current.prev; // removing the tail
            }
            return true;
        }

        void printForward() {
            StringBuilder sb = new StringBuilder("Forward: ");
            for (Coach c = head; c != null; c = c.next) {
                sb.append(c.name);
                if (c.next != null) {
                    sb.append(" <-> ");
                }
            }
            System.out.println(sb);
        }

        void printBackward() {
            StringBuilder sb = new StringBuilder("Backward: ");
            for (Coach c = tail; c != null; c = c.prev) {
                sb.append(c.name);
                if (c.prev != null) {
                    sb.append(" <-> ");
                }
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        Train train = new Train();
        for (String name : new String[]{"Engine", "A1", "B1", "B2", "Guard"}) {
            train.addLast(name);
        }

        train.remove("B1");
        train.printForward();  // Forward: Engine <-> A1 <-> B2 <-> Guard
        train.printBackward(); // Backward: Guard <-> B2 <-> A1 <-> Engine
    }
}
