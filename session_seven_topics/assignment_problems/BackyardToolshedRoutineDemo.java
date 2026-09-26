package session_seven_topics.assignment_problems;

/**
 * PROBLEM 3: Backyard Toolshed Routine
 * GardenTool (abstract) -> CuttingTool -> Pruner, three classes deep. Pruner's use()
 * calls super.use() (CuttingTool's concrete version) and appends its own detail.
 */
public class BackyardToolshedRoutineDemo {

    static abstract class GardenTool {
        public abstract String use();
    }

    static class CuttingTool extends GardenTool {
        public CuttingTool() {
            super();
        }

        @Override
        public String use() {
            return "Using the tool in the garden, blade sharpened first";
        }
    }

    static class Pruner extends CuttingTool {
        public Pruner() {
            super();
        }

        @Override
        public String use() {
            return super.use() + ", then trimming branches precisely";
        }
    }

    public static void main(String[] args) {
        CuttingTool c = new CuttingTool();
        System.out.println(c.use()); // Using the tool in the garden, blade sharpened first

        Pruner p = new Pruner();
        System.out.println(p.use());
        // Using the tool in the garden, blade sharpened first, then trimming branches precisely
    }
}
