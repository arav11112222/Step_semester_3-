package session_seven_topics.class_problems;

/**
 * PROBLEM 5: Package Drop-Off Log
 * DeliveryNote is abstract with an abstract confirmDelivery() plus a concrete overload
 * confirmDelivery(String signature) that calls confirmDelivery() internally rather than
 * duplicating the core message. logAll() dispatches polymorphically even through an
 * upcast reference.
 */
public class PackageDropOffLogDemo {

    static abstract class DeliveryNote {
        public abstract String confirmDelivery();

        String confirmDelivery(String signature) {
            return confirmDelivery() + ", signed by " + signature;
        }
    }

    static class ParcelNote extends DeliveryNote {
        String trackingId;

        public ParcelNote(String trackingId) {
            this.trackingId = trackingId;
        }

        @Override
        public String confirmDelivery() {
            return "Parcel " + trackingId + " delivered";
        }
    }

    static class LetterNote extends DeliveryNote {
        String trackingId;

        public LetterNote(String trackingId) {
            this.trackingId = trackingId;
        }

        @Override
        public String confirmDelivery() {
            return "Letter " + trackingId + " delivered";
        }
    }

    static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println(p.confirmDelivery()); // Parcel TRK-1 delivered
        System.out.println(p.confirmDelivery("J. Smith")); // Parcel TRK-1 delivered, signed by J. Smith

        DeliveryNote ref = p; // upcasting: ParcelNote stored as its parent type
        logAll(new DeliveryNote[]{ref, new LetterNote("TRK-2")});
        // Parcel TRK-1 delivered
        // Letter TRK-2 delivered
    }
}
