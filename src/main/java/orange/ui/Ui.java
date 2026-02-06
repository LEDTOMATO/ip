package orange.ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs a Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Orange");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the separator line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println("Haha (´ー`) " + message);
    }

    /**
     * Displays a loading error message.
     */
    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    /**
     * Reads a command from the user.
     *
     * @return the user's input command
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}