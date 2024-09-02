package uv.fdpoe.t1;


import java.awt.Component;
import javax.swing.JOptionPane;

public class Utilities {
    public static void dialog(String message) { dialog(null, message); }
    public static void dialog(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }
    public static String selectWindow(String title, String message, String[] options) {
        return (String) JOptionPane.showInputDialog(
            null,
            "select an option",
            "Main menu",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
    }
    public static void log(String message) { log(message, true); }
    public static void log(String message, boolean ln) {
        System.out.print(message);
        if (ln) System.out.print("\n");
    }
    public static void logConcat(String ...message) {
        String result = "";
        for (String part : message) result += part + " ";
        log(result);
    }
    public static void log(String[] messages) {
        for (String message : messages) log(message);
    }
}
