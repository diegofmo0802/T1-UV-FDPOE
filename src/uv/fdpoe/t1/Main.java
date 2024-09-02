/*
Proposito: proporciona los menus al usuario
Autor: Diego Fernando Moreno - 202363205
Autor: Oscar David Cadavid Ramirez - 202363243
Fecha: 01-09-2024
Version: 1.1
*/
package uv.fdpoe.t1;

public class Main {
    public PeopleManager peopleManager = new PeopleManager();
    public void addItemsToDenug() {
        People p1 = new People("1234");
        People p2 = new People("5678");
        People p3 = new People("9011");
        People p4 = new People("1213");
        p1.setFirstName("pedro");
        p2.setFirstName("juan");
        p3.setFirstName("maria");
        p4.setFirstName("carlos");
        p1.setLastName("test");
        p2.setLastName("test");
        p3.setLastName("test");
        p4.setLastName("test");
        p1.setGender("male");
        p2.setGender("male");
        p3.setGender("female");
        p4.setGender("male");
        p1.setAge(20);
        p2.setAge(21);
        p3.setAge(19);
        p4.setAge(16);
        peopleManager.peoples.add(p1);
        peopleManager.peoples.add(p2);
        peopleManager.peoples.add(p3);
        peopleManager.peoples.add(p4);
    }
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
            case "add" -> { peopleManager.addPeople(); }
            case "delete" -> { peopleManager.delPeople(); }
            case "modify" -> { peopleManager.editPeople(); }
            default -> { Utilities.dialog("invalid option: " + selected); }

        }
    }
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
