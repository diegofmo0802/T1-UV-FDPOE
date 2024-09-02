/*
WithOut Header cause this faile is reusable to other projects
*/

package uv.fdpoe.t1;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Utilities {

    /**
     * Displays a simple message dialog.
     * @param message The message to be displayed in the dialog.
     */
    public static void dialog(String message) {
        dialog(null, message);
    }

    /**
     * Displays a simple message dialog with an optional parent component.
     * @param parentComponent The parent component of the dialog (can be null).
     * @param message The message to be displayed in the dialog.
     */
    public static void dialog(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }

    /**
     * Displays a selection window with a list of options.
     * @param title The title of the dialog window.
     * @param message The message to be displayed in the window.
     * @param options The available options for selection.
     * @return The option selected by the user as a String.
     */
    public static String selectWindow(String title, String message, String[] options) {
        return (String) JOptionPane.showInputDialog(
            null,
            message,
            title,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
    }

    /**
     * Displays an option dialog with custom buttons.
     * @param title The title of the dialog window.
     * @param message The message to be displayed in the window.
     * @param options The available options as buttons.
     * @return The index of the option selected by the user.
     */
    public static int optionWindow(String title, String message, String[] options) {
        return JOptionPane.showOptionDialog(
            null,
            message,
            title,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );
    }

    /**
     * Logs a message to the console.
     * @param message The message to be logged to the console.
     */
    public static void log(String message) {
        log(message, true);
    }

    /**
     * Logs a message to the console with an option for a newline.
     * @param message The message to be logged to the console.
     * @param ln If true, adds a newline after the message.
     */
    public static void log(String message, boolean ln) {
        System.out.print(message);
        if (ln) System.out.print("\n");
    }

    /**
     * Concatenates multiple messages and logs them to the console.
     * @param message The messages to be concatenated and logged to the console.
     */
    public static void logConcat(String ...message) {
        log(concat(" ", message));
    }

    /**
     * Logs each message in an array of messages to the console.
     * @param messages An array of messages to log to the console.
     */
    public static void log(String[] messages) {
        for (String message : messages) log(message);
    }

    /**
     * Concatenates multiple strings with a specified delimiter.
     * @param join The delimiter used to concatenate the strings.
     * @param items The strings to concatenate.
     * @return A string resulting from concatenating all input strings.
     */
    public static String concat(String join, String ...items) {
        String result = "";
        for (String part : items) result += part + join;
        return result;
    }

    /**
     * Prompts the user for a string input through a dialog box.
     * @param prompt The message to display to the user.
     * @param nullable If false, the dialog will reappear if the user does not provide a value.
     * @return The string input by the user, or null if allowed.
     */
    public static String askString(String prompt, boolean nullable) {
        boolean ask = true;
        String result = null;
        while(ask) {
            result = JOptionPane.showInputDialog(prompt);
            if (!nullable && result == null) continue;
            ask = false;
        }
        return result;
    }

    /**
     * Prompts the user for an integer input through a dialog box.
     * @param prompt The message to display to the user.
     * @return The integer input by the user.
     */
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
                dialog("That is not a number: " + response);
            }
        }
        return result;
    }

    /**
     * Displays a confirmation dialog to the user.
     * @param message The message to display in the confirmation dialog.
     * @return true if the user selects "Yes", false otherwise.
     */
    public static boolean confirm(String message) {
        return confirm(null, message);
    }

    /**
     * Displays a confirmation dialog to the user with an optional parent component.
     * @param parentComponent The parent component of the dialog (can be null).
     * @param message The message to display in the confirmation dialog.
     * @return true if the user selects "Yes", false otherwise.
     */
    public static boolean confirm(Component parentComponent, String message) {
        int result = JOptionPane.showConfirmDialog(parentComponent, message);
        return result == JOptionPane.YES_OPTION;
    }
}
