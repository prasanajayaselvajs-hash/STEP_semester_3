public class Problem1_LibraryMembership {

    static class LibraryMember {

        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(String memberId, int borrowLimit) {

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
        }

        public void borrowBook() {

            if (booksBorrowed < borrowLimit) {
                booksBorrowed++;
            }
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }
    }

    static class StudentMember extends LibraryMember {

        String course;

        public StudentMember(
                String memberId,
                int borrowLimit,
                String course) {

            super(memberId, borrowLimit);
            this.course = course;
        }
    }

    static String enrollBatch(
            String[] memberIds,
            int borrowLimit) {

        int enrolled = 0;
        int rejected = 0;

        for (int i = 0; i < memberIds.length; i++) {

            try {
                new LibraryMember(
                    memberIds[i],
                    borrowLimit
                );

                enrolled++;

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        return "Enrolled: " + enrolled +
               " | Rejected: " + rejected;
    }
    public static void main(String[] args) {

        try {
            new LibraryMember("LB1", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        StudentMember s =
            new StudentMember("STU10", 3, "CSE");

        s.borrowBook();
        s.borrowBook();

        System.out.println(
            s.getBooksBorrowed()
        );

        String[] ids = {
            "STU1", "LB1", "STU2", " ", "STU3"
        };

        System.out.println(
            enrollBatch(ids, 3)
        );
    }
}