package uv.fdpoe.t1;

public class Main {
    public PeopleManager peoples = new PeopleManager();
    public void mainMenu() {
        boolean running = true;
        String[] options = {
            "entity managment",
            "list menu",
            "statistics",
            "about us",
            "close app"
        };
        while (running) {
            String selected = Utilities.selectWindow("Main menu", "select an option", options);
            Utilities.logConcat("option selected:", selected);
            switch (selected) {
                case  "entity managment" -> {
                    entityMenu();
                }
                case "list menu" -> { listMenu(); }
                case "about us"  -> { aboutUs(); }
                case "close app" -> { running = false; }
                default -> { Utilities.dialog("invalid option: " + selected); }
            }
        }
    }
    public void entityMenu() {
        String[] options = {
            "add",
            "delete",
            "modify",
        };
        String selected = Utilities.selectWindow("entity menu", "select an option", options);
        switch(selected) {
            case "add" -> { peoples.addPeople(); }
            case "delete" -> { peoples.delPeople(); }
            case "modify" -> { peoples.editPeople(); }
            default -> { Utilities.dialog("invalid option: " + selected); }

        }
    }
    public void listMenu() {
        String[] options = {
            "general list",
            "query by id",
            "query by age",
        };
        String selected = Utilities.selectWindow("list menu", "select an option", options);
        switch (selected) {
                case "list menu" -> { listMenu(); }
                default -> { Utilities.dialog("invalid option: " + selected); }
            }
    }
    public void aboutUs() {
        String message = Utilities.concat("\n",
            "Diego Fernando Moreno     - 202363205",
            "Veruzka Katriana Guapacha - 202378085",
            "Oscar David Cadavid       - 202363228"
        );
        Utilities.dialog(message);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.mainMenu();
    }
}
