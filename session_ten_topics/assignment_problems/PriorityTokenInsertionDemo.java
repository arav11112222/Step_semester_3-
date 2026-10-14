package session_ten_topics.assignment_problems;

/**
 * Problem 1: Priority Token Insertion
 * Own Node class and a singly linked list with only a head reference (no java.util.LinkedList).
 *
 * addFirst:  O(1)
 * addLast:   O(n)   must walk to the last node (O(1) if a tail reference were kept)
 * insertAt:  O(n)   walk to the node just before the index, then relink
 */
public class PriorityTokenInsertionDemo {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class TokenList {
        Node head;
        int size = 0;

        void addFirst(int token) {
            Node node = new Node(token);
            node.next = head;
            head = node;
            size++;
        }

        void addLast(int token) {
            Node node = new Node(token);
            if (head == null) {
                head = node;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = node;
            }
            size++;
        }

        void insertAt(int index, int token) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("index " + index + ", size " + size);
            }
            if (index == 0) {
                addFirst(token);
                return;
            }

            Node previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }

            Node node = new Node(token);
            node.next = previous.next; // set the new node's next first...
            previous.next = node;      // ...then link the previous node to it
            size++;
        }

        void print() {
            StringBuilder sb = new StringBuilder();
            for (Node current = head; current != null; current = current.next) {
                sb.append(current.data).append(" -> ");
            }
            sb.append("null");
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        TokenList list = new TokenList();
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        list.print(); // 100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null
    }
}
