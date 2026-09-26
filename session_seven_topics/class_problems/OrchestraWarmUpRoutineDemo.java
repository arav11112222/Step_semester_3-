package session_seven_topics.class_problems;

/**
 * PROBLEM 3: Orchestra Warm-Up Routine
 * Instrument (abstract) -> StringInstrument -> Violin, three classes deep. Violin's
 * play() calls super.play() (StringInstrument's concrete version) and appends its own
 * detail, rather than rebuilding the whole message from scratch.
 */
public class OrchestraWarmUpRoutineDemo {

    static abstract class Instrument {
        public abstract String play();
    }

    static class StringInstrument extends Instrument {
        public StringInstrument() {
            super();
        }

        @Override
        public String play() {
            return "Strumming the strings";
        }
    }

    static class Violin extends StringInstrument {
        public Violin() {
            super();
        }

        @Override
        public String play() {
            return super.play() + ", with a bow drawn across four strings";
        }
    }

    public static void main(String[] args) {
        StringInstrument s = new StringInstrument();
        System.out.println(s.play()); // Strumming the strings

        Violin v = new Violin();
        System.out.println(v.play()); // Strumming the strings, with a bow drawn across four strings
    }
}
