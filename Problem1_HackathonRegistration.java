public class Problem1_HackathonRegistration {

    static class Participant {

        String name;
        String teamName;
        boolean registered;

        public Participant(String name, String teamName) {
            this.name = name;
            this.teamName = teamName;
            this.registered = true;
        }

        public Participant(String name) {
            this(name, "Unassigned");
        }

        public void printStatus() {
            System.out.println(
                name + " | " + teamName +
                " | Registered: " + registered
            );
        }
    }

    public static void main(String[] args) {

        String[] names = {
            "Ravi", "Meera", "Karthik", "Divya"
        };

        String[] teamNames = {
            "ByteBusters", "", "CodeCrafters", ""
        };

        Participant[] participants =
            new Participant[names.length];

        for (int i = 0; i < names.length; i++) {

            if (teamNames[i].isEmpty()) {
                participants[i] =
                    new Participant(names[i]);
            } else {
                participants[i] =
                    new Participant(names[i], teamNames[i]);
            }

            participants[i].printStatus();
        }
    }
}