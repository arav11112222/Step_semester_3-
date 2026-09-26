package session_seven_topics.class_problems;

/**
 * PROBLEM 1: The Talking Toy Box
 * Toy is abstract with an abstract makeSound() — "new Toy(...)" cannot compile, only
 * a concrete subclass can ever be constructed. toyId is final, assigned once via a
 * shared static counter inside Toy's own constructor, reached by every subclass via super().
 */
public class TalkingToyBoxDemo {

    static abstract class Toy {
        private static int counter = 1000;

        final String toyId;
        String name;

        public Toy(String name) {
            counter++;
            this.toyId = "TOY-" + counter;
            this.name = name;
        }

        public abstract String makeSound();

        String getToyId() {
            return toyId;
        }
    }

    // new Toy("x") would not compile here — Toy is abstract, so only ToyCar/ToyRobot can be built.

    static class ToyCar extends Toy {
        public ToyCar(String name) {
            super(name);
        }

        @Override
        public String makeSound() {
            return name + ": Vroom vroom!";
        }
    }

    static class ToyRobot extends Toy {
        public ToyRobot(String name) {
            super(name);
        }

        @Override
        public String makeSound() {
            return name + ": Beep boop!";
        }
    }

    public static void main(String[] args) {
        ToyCar c = new ToyCar("Speedster");
        System.out.println(c.makeSound()); // Speedster: Vroom vroom!

        ToyRobot r = new ToyRobot("Bolt");
        System.out.println(r.makeSound()); // Bolt: Beep boop!

        System.out.println(c.getToyId()); // TOY-1001
        System.out.println(r.getToyId()); // TOY-1002
    }
}
