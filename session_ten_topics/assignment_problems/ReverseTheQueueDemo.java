package session_ten_topics.assignment_problems;

/**
 * Problem 3: Reverse the Queue
 * Reverses the list in place with three references (previous, current, next) and turns each
 * next pointer around one node at a time. No new nodes are created.
 *
 * Time: O(n)   Additional space: O(1)
 */
public class ReverseTheQueueDemo {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverse(Node head) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            Node next = current.next; // remember the rest of the list
            current.next = previous;  // turn this pointer around
            previous = current;
            current = next;
        }
        return previous; // old tail is the new head
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
        print(reverse(build(1, 2, 3, 4))); // 4 -> 3 -> 2 -> 1 -> null
        print(reverse(build(9)));           // 9 -> null
    }
}
