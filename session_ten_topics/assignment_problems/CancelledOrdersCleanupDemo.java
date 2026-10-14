package session_ten_topics.assignment_problems;

/**
 * Problem 2: Cancelled Orders Cleanup
 * Step 1: move head forward while the head itself matches.
 * Step 2: walk with a reference to the previous node and skip every matching next node.
 *
 * Time: O(n)   Additional space: O(1)
 */
public class CancelledOrdersCleanupDemo {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node removeAll(Node head, int code) {
        while (head != null && head.data == code) {
            head = head.next;
        }

        Node previous = head;
        while (previous != null && previous.next != null) {
            if (previous.next.data == code) {
                previous.next = previous.next.next; // skip it, stay on previous
            } else {
                previous = previous.next;
            }
        }
        return head;
    }

    static Node build(int... values) {
        Node head = null;
        Node tail = null;
        for (int v : values) {
            Node node = new Node(v);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }
        return head;
    }

    static void print(Node head) {
        StringBuilder sb = new StringBuilder();
        for (Node current = head; current != null; current = current.next) {
            sb.append(current.data).append(" -> ");
        }
        sb.append("null");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        print(removeAll(build(5, 3, 5, 8, 5), 5)); // 3 -> 8 -> null
        print(removeAll(build(5, 5, 5), 5));        // null
    }
}
