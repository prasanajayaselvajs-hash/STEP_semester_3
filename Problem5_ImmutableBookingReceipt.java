import java.util.Arrays;

public class Problem5_ImmutableBookingReceipt {

    static class BookingReceipt {

        private final String bookingId;
        private final String[] seatNumbers;

        public BookingReceipt(String bookingId, String[] seatNumbers) {
            this.bookingId = bookingId;
            this.seatNumbers = Arrays.copyOf(
                seatNumbers,
                seatNumbers.length
            );
        }

        public String[] getSeatNumbers() {
            return Arrays.copyOf(
                seatNumbers,
                seatNumbers.length
            );
        }

        public BookingReceipt withUpdatedSeat(
                int index,
                String newSeat) {

            String[] updatedSeats = getSeatNumbers();

            if (index >= 0 && index < updatedSeats.length) {
                updatedSeats[index] = newSeat;
            }

            return new BookingReceipt(
                bookingId,
                updatedSeats
            );
        }
    }

    static class GroupBookingReceipt extends BookingReceipt {

        private final int groupSize;

        public GroupBookingReceipt(
                String bookingId,
                String[] seatNumbers,
                int groupSize) {

            super(bookingId, seatNumbers);
            this.groupSize = groupSize;
        }

        public int getGroupSize() {
            return groupSize;
        }
    }

    static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipts[i] instanceof GroupBookingReceipt) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               groupCount + " group | " +
               individualCount + " individual";
    }

    public static void main(String[] args) {

        BookingReceipt b =
            new BookingReceipt(
                "CH-1001",
                new String[]{"A1", "A2"}
            );

        String[] seats = b.getSeatNumbers();

        seats[0] = "X";

        System.out.println(
            "Original after external change: " +
            b.getSeatNumbers()[0]
        );

        BookingReceipt updated =
            b.withUpdatedSeat(1, "A3");

        System.out.println(
            "Original: " +
            Arrays.toString(b.getSeatNumbers())
        );

        System.out.println(
            "Updated: " +
            Arrays.toString(updated.getSeatNumbers())
        );

        BookingReceipt[] receipts = {

            new GroupBookingReceipt(
                "CH-2002",
                new String[]{"B1", "B2"},
                2
            ),

            null,

            new BookingReceipt(
                "CH-3003",
                new String[]{"C1"}
            )
        };

        System.out.println(
            processNightlySettlement(receipts)
        );
    }
}