package session_seven_topics.assignment_problems;

/**
 * PROBLEM 2: Gallery Description Cards
 * ArtPiece is abstract with an abstract describe(), and a final pieceId assigned once
 * via a shared static counter inside ArtPiece's own constructor, reached by every
 * subclass through super().
 */
public class GalleryDescriptionCardsDemo {

    static abstract class ArtPiece {
        private static int counter = 0;

        final String pieceId;
        String title;

        public ArtPiece(String title) {
            counter++;
            this.pieceId = "ART-" + counter;
            this.title = title;
        }

        public abstract String describe();

        String getPieceId() {
            return pieceId;
        }
    }

    static class Painting extends ArtPiece {
        public Painting(String title) {
            super(title);
        }

        @Override
        public String describe() {
            return "Painting: " + title + ", framed on canvas";
        }
    }

    static class Sculpture extends ArtPiece {
        public Sculpture(String title) {
            super(title);
        }

        @Override
        public String describe() {
            return "Sculpture: " + title + ", carved from stone";
        }
    }

    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println(p.describe()); // Painting: Sunset Fields, framed on canvas
        System.out.println(p.getPieceId()); // ART-1

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(s.describe()); // Sculpture: The Thinker II, carved from stone
        System.out.println(s.getPieceId()); // ART-2
    }
}
