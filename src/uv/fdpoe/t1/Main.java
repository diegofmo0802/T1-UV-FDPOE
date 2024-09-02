/*
Purpose: Provides the main menus and user interface for the application.
Authors: 
  - Diego Fernando Moreno - 202363205
  - Oscar David Cadavid Ramirez - 202363243
  - Veruzka Guapacha Quiroz - 202378085
Date: 01-09-2024
Version: 1.1
*/

package uv.fdpoe.t1;

public class Main {

    public PeopleManager peopleManager = new PeopleManager();

    /**
     * Adds sample data to the PeopleManager for debugging purposes.
     */
    public void addItemsToDenug() {
        People p1 = new People("1234");
        People p2 = new People("5678");
        People p3 = new People("9011");
        People p4 = new People("1213");
        People p5 = new People("1593");

        p1.setFirstName("jennifer");
        p2.setFirstName("marcos");
        p3.setFirstName("giselle");
        p4.setFirstName("ryan");
        p5.setFirstName("Luis");

        p1.setLastName("test");
        p2.setLastName("test");
        p3.setLastName("test");
        p4.setLastName("test");
        p5.setLastName("test");

        p1.setGender("female");
        p2.setGender("male");
        p3.setGender("female");
        p4.setGender("male");
        p5.setGender("male");

        p1.setAge(20);
        p2.setAge(21);
        p3.setAge(19);
        p4.setAge(16);
        p5.setAge(28);

        peopleManager.peoples.add(p1);
        peopleManager.peoples.add(p2);
        peopleManager.peoples.add(p3);
        peopleManager.peoples.add(p4);
        peopleManager.peoples.add(p5);
    }

    /**
     * Displays the main menu and handles user interactions.
     */
    public void mainMenu() {
        addItemsToDenug();
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
                case "entity managment" -> { entityMenu(); }
                case "list menu" -> { listMenu(); }
                case "statistics" -> { analitycsMenu(); }
                case "about us"  -> { aboutUs(); }
                case "close app" -> { running = false; }
                default -> { Utilities.dialog("invalid option: " + selected); }
            }
        }
    }

    /**
     * Displays the entity management menu and handles user interactions.
     */
    public void entityMenu() {
        String[] options = {
            "add",
            "delete",
            "modify",
        };
        String selected = Utilities.selectWindow("entity menu", "select an option", options);
        switch(selected) {
            case "add" -> { peopleManager.addPeople(); }
            case "delete" -> { peopleManager.delPeople(); }
            case "modify" -> { peopleManager.editPeople(); }
            default -> { Utilities.dialog("invalid option: " + selected); }
        }
    }

    /**
     * Displays the analytics menu and handles user interactions.
     */
    public void analitycsMenu() {
        String[] options = {
            "media de amigos",
            "age media",
            "gender count",
        };
        String selected = Utilities.selectWindow("entity menu", "select an option", options);
        switch(selected) {
            case "media de amigos" -> {
                Utilities.dialog("media of Friends per people:" + peopleManager.getFriendsMedia());
            }
            case "age media" -> {
                Utilities.dialog("media of Friends per people:" + peopleManager.getAgeMedia());
            }
            case "gender count" -> {
                Utilities.dialog(Utilities.concat("\n", new String[] {
                    "total girls: " + peopleManager.getGenderCount("female"),
                    "total boys: " + peopleManager.getGenderCount("male"),
                    "total others: " + peopleManager.getGenderCount("other"),
                }));
            }
            default -> { Utilities.dialog("invalid option: " + selected); }
        }
    }

    /**
     * Displays the list menu and handles user interactions for listing and querying people.
     */
    public void listMenu() {
        String[] options = {
            "general list",
            "query by age >= ?",
            "query by age <= ?",
            "friend count >= ?",
            "friend count <= ?",
        };
        int selected = Utilities.optionWindow("list menu", "select an option", options);
        Utilities.logConcat("list menu: selected option ->", ""+selected);
        int reference = 0;
        if (selected > 0) reference = Utilities.askInt("write a number");
        switch (selected) {
            case  0 -> { peopleManager.listAll(); }
            case  1 -> { peopleManager.listByAge(">=", reference); }
            case  2 -> { peopleManager.listByAge("<=", reference); }
            case  3 -> { peopleManager.listByFriendCount(">=", reference); }
            case  4 -> { peopleManager.listByFriendCount("<=", reference); }
            default -> { Utilities.dialog("invalid option: " + selected); }
        }
    }

    /**
     * Displays information about the authors of the application.
     */
    public void aboutUs() {
        String message = Utilities.concat("\n",
            "Fundamentos de programacion orientada a eventos",
            "Grupo 50",
            "Diego Fernando Moreno     - 202363205",
            "Veruzka Katriana Guapacha - 202378085",
            "Oscar David Cadavid       - 202363228"
        );
        Utilities.dialog(message);
    }

    /**
     * Main method that initializes the application and starts the main menu.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.mainMenu();
    }
}
