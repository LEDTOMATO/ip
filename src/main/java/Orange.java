import java.util.Scanner;

/**
 * Orange is a task management chatbot that helps users manage
 * todos, deadlines, and events via text command.
 * It reads and updates the task list, and displays list.
 */
public class Orange {
    private static final String LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;

    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String command = getCommandWord(input);

            switch (command) {
            case "bye":
                exit();
                return;

            case "list":
                listTasks();
                break;

            case "mark":
                markTask(input);
                break;

            case "unmark":
                unmarkTask(input);
                break;

            case "todo":
                addTodo(input);
                break;

            case "deadline":
                addDeadline(input);
                break;

            case "event":
                addEvent(input);
                break;

            case "delete":
                deleteTask(input);
                break;

            default:
                printError("What do you mean? \uD83C\uDFB5\"");

            }
        }
    }

    // ===== UI =====
    private static void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Orange");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void exit() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Extracts the command word from the user's input.
     *
     * @param input the full user input string
     * @return the command word (first word of the input)
     */
    private static String getCommandWord(String input) {
        return input.split(" ")[0];
    }

    /**
     * Displays all tasks currently stored in the task list,
     * along with their index and completion status.
     */
    private static void listTasks() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(LINE);
    }

    /**
     * Marks a task as completed based on the task number provided
     * in the user input.
     *
     * @param input full user command containing the task index
     */
    private static void markTask(String input) {
        String[] parts = input.split(" ");

        if (parts.length < 2) {
            printError("Please specify the task number to mark.");
            return;
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (!isValidIndex(index)) {
            printError("The task number provided is invalid.");
            return;
        }

        if (tasks[index].isDone) {
            printError("This task has already been marked as done.");
            return;
        }

        tasks[index].markDone();

        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[index]);
        System.out.println(LINE);
    }

    /**
     * Marks a previously completed task as not done,
     * based on the task number provided in the user input.
     *
     * @param input full user command containing the task index
     */
    private static void unmarkTask(String input) {
        String[] parts = input.split(" ");

        if (parts.length < 2) {
            printError("Please specify the task number to unmark.");
            return;
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (!isValidIndex(index)) {
            printError("The task number provided is invalid.");
            return;
        }

        if (!tasks[index].isDone) {
            printError("This task is already marked as not done.");
            return;
        }

        tasks[index].unmark();

        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks[index]);
        System.out.println(LINE);
    }

    /**
     * Adds a new todo task using the description provided
     * in the user input.
     *
     * @param input full user command containing the todo description
     */
    private static void addTodo(String input) {
        if (input.length() <= 5) {
            printError("Write details for todo task pls.");
            return;
        }
        String description = input.substring(5);
        tasks[taskCount++] = new Todo(description);
        printAddMessage();
    }

    /**
     * Adds a new deadline task using the description and deadline time
     * provided in the user input.
     *
     * @param input full user command containing the deadline details
     */
    private static void addDeadline(String input) {
        if (input.length() <= 9) {
            printError("Write details for deadline task pls.");
            return;
        }

        try {
            String[] parts = input.substring(9).split(" by ");
            if (parts.length < 2) {
                printError("Please use format: deadline [description] by [time]");
                return;
            }
            tasks[taskCount++] = new Deadline(parts[0], parts[1]);
            printAddMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            printError("Invalid format! Please use: deadline [description] by [time]");
        }
    }

    /**
     * Adds a new event task using the description, start time,
     * and end time provided in the user input.
     *
     * @param input full user command containing the event details
     */
    private static void addEvent(String input) {
        if (input.length() <= 5) {
            printError("Write details for event task pls.");
            return;
        }
        String[] parts = input.substring(6).split(" from | to ");
        if (parts.length < 3) {
            printError("Please use format: event [description] from [start] to [end]\nExample: event meeting from Monday 2pm to 3pm");
            return;
        }
        tasks[taskCount++] = new Event(parts[0], parts[1], parts[2]);
        printAddMessage();
    }

    /**
     * Prints a confirmation message after a task has been
     * successfully added to the task list.
     */
    private static void printAddMessage() {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Displays an error message to the user in a formatted manner.
     *
     * @param message the error message to be displayed
     */
    private static void printError(String message) {
        System.out.println(LINE);
        System.out.println("Haha (´ー`) " + message);
        System.out.println(LINE);
    }

    /**
     * Checks whether the given index refers to a valid task
     * in the current task list.
     *
     * @param index zero-based task index
     * @return true if the index is valid, false otherwise
     */
    private static boolean isValidIndex(int index) {
        return index >= 0 && index < taskCount;
    }

    /**
     * Deletes a task from the task list based on user input.
     *
     * @param input full user command containing the task index
     */
    private static void deleteTask(String input) {
        String[] parts = input.split(" ");

        if (parts.length < 2) {
            printError("Please specify the task number to delete.");
            return;
        }

        int index = Integer.parseInt(parts[1]) - 1;

        if (!isValidIndex(index)) {
            printError("The task number provided is invalid.");
            return;
        }

        Task removedTask = tasks[index];

        // shift tasks left
        for (int i = index; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        taskCount--;

        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }


}
