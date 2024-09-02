package uv.fdpoe.t1;
import java.util.ArrayList;
public class Main {
    public ArrayList<People> peoples;
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
                case "about us" -> {
                    aboutUs();
                }
                case "close app" ->  { running = false; }
                default -> { Utilities.dialog("invalid option: " + selected); }
            }
        }
    }
    public void aboutUs() {
        String message = Utilities.concat("\n",
            "Diego Fernando Moreno     - 202363205",
            "Veruzka Katriana Guapache - 202378085",
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
