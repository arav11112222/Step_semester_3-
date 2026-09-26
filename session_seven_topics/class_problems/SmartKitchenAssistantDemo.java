package session_seven_topics.class_problems;

/**
 * PROBLEM 4: Smart Kitchen Assistant
 * KitchenTool is abstract with a validated speedLevel property (JavaBean getter/setter,
 * range 1-5). Washable is a separate, unrelated interface. Blender combines both.
 */
public class SmartKitchenAssistantDemo {

    static abstract class KitchenTool {
        private int speedLevel = 1;

        public abstract String prepare();

        int getSpeedLevel() {
            return speedLevel;
        }

        void setSpeedLevel(int speedLevel) {
            if (speedLevel >= 1 && speedLevel <= 5) {
                this.speedLevel = speedLevel;
            }
            // else: rejected silently, out of range
        }
    }

    interface Washable {
        String clean();
    }

    static class Blender extends KitchenTool implements Washable {
        public Blender() {
        }

        @Override
        public String prepare() {
            return "Blending at speed " + getSpeedLevel();
        }

        @Override
        public String clean() {
            return "Blender rinsed and dried";
        }
    }

    public static void main(String[] args) {
        Blender b = new Blender();
        b.setSpeedLevel(3);
        System.out.println(b.getSpeedLevel()); // 3

        b.setSpeedLevel(9); // rejected, out of range
        System.out.println(b.getSpeedLevel()); // 3, unchanged

        System.out.println(b.prepare()); // Blending at speed 3
        System.out.println(b.clean());   // Blender rinsed and dried
    }
}
