package session_four_topics.class_problems;

/**
 * M1. Library Book Cataloguing
 * Single-arg constructor chains to the two-arg constructor, defaulting isbn to "PENDING".
 */
public class LibraryBookDemo {

    static class LibraryBook {
        String title;
        String isbn;
        boolean catalogued;

        LibraryBook(String title, String isbn) {
            this.title = title;
            this.isbn = isbn;
            this.catalogued = true;
        }

        LibraryBook(String title) {
            this(title, "PENDING");
        }

        void printStatus() {
            System.out.println(title + " | " + isbn + " | Catalogued: " + catalogued);
        }
    }

    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        for (int i = 0; i < titles.length; i++) {
            LibraryBook book;
            if (isbns[i] == null || isbns[i].isEmpty()) {
                book = new LibraryBook(titles[i]);
            } else {
                book = new LibraryBook(titles[i], isbns[i]);
            }
            book.printStatus();
        }
    }
}
