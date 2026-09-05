package session_four_topics.assignment_problems;

/**
 * A3. final Method — Parking Overstay Fine Calculator
 * calculateFine() and printReceipt() are final so no subclass can override the fine formula.
 */
public class ParkingTicketDemo {

    static class ParkingTicket {
        String vehicleNo;
        double ratePerMinute;

        ParkingTicket(String vehicleNo, double ratePerMinute) {
            this.vehicleNo = vehicleNo;
            this.ratePerMinute = ratePerMinute;
        }

        final double calculateFine(int overstayMinutes) {
            return overstayMinutes * ratePerMinute;
        }

        final void printReceipt(int overstayMinutes) {
            double fine = calculateFine(overstayMinutes);
            System.out.println(vehicleNo + " - Fine: Rs " + fine);
        }
    }

    public static void main(String[] args) {
        String[] vehicleNos = {"TN09AB1234", "TN22CD5678", "TN09EF9012", "TN10GH3456"};
        double[] ratePerMinute = {2, 2, 3, 2};
        int[] overstayMinutes = {15, 0, -5, 8};

        for (int i = 0; i < vehicleNos.length; i++) {
            ParkingTicket ticket = new ParkingTicket(vehicleNos[i], ratePerMinute[i]);

            if (overstayMinutes[i] > 0) {
                ticket.printReceipt(overstayMinutes[i]);
            } else {
                System.out.println(vehicleNos[i] + " - No fine, within allotted time");
            }
        }
    }
}
