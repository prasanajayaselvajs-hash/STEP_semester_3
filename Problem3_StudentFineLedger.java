public class Problem3_StudentFineLedger {

    static class LibraryMember {

        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        private int[] fineHistory;
        private int fineCount;

        public LibraryMember(
                String memberId,
                int borrowLimit) {

            if (memberId == null ||
                memberId.trim().isEmpty() ||
                memberId.length() < 4) {

                throw new IllegalArgumentException(
                    "Invalid member ID"
                );
            }

            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;

            fineHistory = new int[10];
            fineCount = 0;
        }

        protected void chargeFine(int amount) {

            if (fineCount < fineHistory.length) {
                fineHistory[fineCount] = amount;
                fineCount++;
            }
        }

        public int[] getFineHistory() {

            int[] copy = new int[fineCount];

            for (int i = 0; i < fineCount; i++) {
                copy[i] = fineHistory[i];
            }

            return copy;
        }

        public int getTotalFine() {

            int total = 0;

            for (int i = 0; i < fineCount; i++) {
                total += fineHistory[i];
            }

            return total;
        }
    }

    static class StudentMember
            extends LibraryMember {

        String course;

        public StudentMember(
                String memberId,
                int borrowLimit,
                String course) {

            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        protected void chargeFine(int amount) {

            super.chargeFine(amount / 2);
        }
    }

    public static void main(String[] args) {

        StudentMember s =
            new StudentMember(
                "STU5", 3, "CSE"
            );

        s.chargeFine(100);

        System.out.println(
            s.getTotalFine()
        );

        int[] history =
            s.getFineHistory();

        history[0] = 999;

        System.out.println(
            java.util.Arrays.toString(
                s.getFineHistory()
            )
        );
    }
}