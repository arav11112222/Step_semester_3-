package session_three_topics.class_problems;

/**
 * M1. From Parallel Arrays to a Class
 * Rebuilds a parallel-array placement tracker as a proper class with objects.
 */
public class PlacementRecordDemo {

    static class PlacementRecord {
        String studentName;
        String company;
        double packageLpa;

        PlacementRecord(String studentName, String company, double packageLpa) {
            this.studentName = studentName;
            this.company = company;
            this.packageLpa = packageLpa;
        }

        void printRecord() {
            System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
        }
    }

    public static void main(String[] args) {
        PlacementRecord[] records = new PlacementRecord[3];
        records[0] = new PlacementRecord("Ravi", "TCS", 4.5);
        records[1] = new PlacementRecord("Anitha", "Zoho", 6.2);
        records[2] = new PlacementRecord("Karthik", "Infosys", 4.0);

        for (PlacementRecord record : records) {
            record.printRecord();
        }
    }
}
