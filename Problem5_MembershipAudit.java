public class Problem5_MembershipAudit {

    static class LibraryMember {

        private static int memberCounter = 100;

        protected int borrowLimit;
        protected int booksBorrowed;

        private final String memberNumber;

        private String[] genres;
        private int genreCount;

        public LibraryMember(int borrowLimit) {

            this.borrowLimit = borrowLimit;
            this.booksBorrowed = 0;

            memberCounter++;

            this.memberNumber =
                "LIB-" + memberCounter;

            genres = new String[10];
            genreCount = 0;
        }

        public void borrowBook() {

            if (booksBorrowed < borrowLimit) {
                booksBorrowed++;
            }
        }

        public void borrowBook(String genre) {

            if (genreCount < genres.length) {
                genres[genreCount] = genre;
                genreCount++;
            }

            borrowBook();
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }

        public static boolean isValidRenewalCode(
                String code) {

            if (code == null ||
                code.length() != 4) {

                return false;
            }

            return code.charAt(0) == 'R' &&
                   Character.isDigit(code.charAt(1)) &&
                   Character.isDigit(code.charAt(2)) &&
                   Character.isUpperCase(code.charAt(3));
        }

        public static int getMembersEnrolled() {
            return memberCounter - 100;
        }
    }

    static class FacultyMember
            extends LibraryMember {

        private String department;

        public FacultyMember(
                int borrowLimit,
                String department) {

            super(borrowLimit);
            this.department = department;
        }
    }

    static String processNightlyAudit(
            LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (int i = 0; i < members.length; i++) {

            if (members[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (members[i] instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed +
               " processed | " +
               nullSkipped +
               " null skipped | " +
               faculty +
               " faculty | " +
               regular +
               " regular";
    }

    public static void main(String[] args) {

        LibraryMember m1 =
            new LibraryMember(3);

        System.out.println(
            m1.memberNumber
        );

        System.out.println(
            LibraryMember.getMembersEnrolled()
        );

        System.out.println(
            LibraryMember.isValidRenewalCode("R12A")
        );

        System.out.println(
            LibraryMember.isValidRenewalCode("R1A")
        );

        System.out.println(
            LibraryMember.isValidRenewalCode("X12A")
        );

        m1.borrowBook();
        m1.borrowBook("Fiction");

        System.out.println(
            m1.getBooksBorrowed()
        );

        LibraryMember[] members = {

            new FacultyMember(
                5, "Physics"
            ),

            null,

            new LibraryMember(3)
        };

        System.out.println(
            processNightlyAudit(members)
        );
    }
}