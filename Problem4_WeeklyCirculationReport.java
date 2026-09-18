public class Problem4_WeeklyCirculationReport {

    static class LibraryMember {

        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

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
        }

        public void borrowBook() {

            if (booksBorrowed < borrowLimit) {
                booksBorrowed++;
            }
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }

        public String displayInfo() {

            return "General | Books: " +
                   booksBorrowed;
        }
    }

    static class StudentMember
            extends LibraryMember {

        private String course;

        public StudentMember(
                String memberId,
                int borrowLimit,
                String course) {

            super(memberId, borrowLimit);
            this.course = course;
        }

        public String getCourse() {
            return course;
        }

        @Override
        public String displayInfo() {

            return "Student | Course: " +
                   course +
                   " | Books: " +
                   booksBorrowed;
        }
    }

    static String batchPrint(
            LibraryMember[] members) {

        StringBuilder report =
            new StringBuilder();

        for (int i = 0; i < members.length; i++) {

            report.append(
                members[i].displayInfo()
            );

            if (members[i] instanceof StudentMember) {

                StudentMember student =
                    (StudentMember) members[i];

                report.append(
                    " [Course via downcast: " +
                    student.getCourse() +
                    "]"
                );
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        LibraryMember general =
            new LibraryMember("LB5", 3);

        StudentMember student =
            new StudentMember(
                "STU6", 3, "ECE"
            );

        LibraryMember[] members = {
            general,
            student
        };

        System.out.println(
            batchPrint(members)
        );
    }
}