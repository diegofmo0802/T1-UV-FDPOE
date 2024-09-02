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
        log(concat(" ", message));
    }
    public static void log(String[] messages) {
        for (String message : messages) log(message);
    }
    public static String concat(String join, String ...items) {
        String result = "";
        for (String part : items) result += part + join;
        return result;
    }
    public static String askString(String prompt, boolean nulable) {
        boolean ask = true;
        String result = null;
        while(ask) {
            result = JOptionPane.showInputDialog(prompt);
            if (!nulable && result == null) continue;
            ask = false;
        }
        return result;
    }
    public static int askInt(String prompt) {
        boolean ask = true;
        int result = -1;
        while(ask) {
            String response = JOptionPane.showInputDialog(prompt);
            if (response == null) continue;
            try {
                result = Integer.parseInt(response);
                ask = false;
            } catch(NumberFormatException error) {
                dialog("that is not a number:" + response);
            }
        }
        return result;
    }
     public static boolean confirm(String message) {
         return confirm(null, message);
     }
    public static boolean confirm(Component parentComponent, String message) {
        int result = JOptionPane.showConfirmDialog(parentComponent, message);
        if (result == 0) return true;
        return false;
    }
}
