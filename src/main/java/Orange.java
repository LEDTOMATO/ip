import java.util.Scanner;

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

                default:
                    // for now, ignore unknown commands (handled in Level-5)
                    break;
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

    // ===== Command helpers =====
    private static String getCommandWord(String input) {
        return input.split(" ")[0];
    }

    // ===== Task actions =====
    private static void listTasks() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(LINE);
    }

    private static void markTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks[index].markDone();

        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks[index]);
        System.out.println(LINE);
    }

    private static void unmarkTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks[index].unmark();

        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks[index]);
        System.out.println(LINE);
    }

    private static void addTodo(String input) {
        String description = input.substring(5);
        tasks[taskCount++] = new Todo(description);
        printAddMessage();
    }

    private static void addDeadline(String input) {
        String[] parts = input.substring(9).split(" /by ");
        tasks[taskCount++] = new Deadline(parts[0], parts[1]);
        printAddMessage();
    }

    private static void addEvent(String input) {
        String[] parts = input.substring(6).split(" /from | /to ");
        tasks[taskCount++] = new Event(parts[0], parts[1], parts[2]);
        printAddMessage();
    }

    private static void printAddMessage() {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(LINE);
    }
}
