import java.util.Scanner;

public class Orange {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        System.out.println(line);
        System.out.println("Hello! I'm Orange :)\n");
        System.out.println("What can I do for you?\n");
        System.out.println(line);

        String[] tasks = new String[100];
        int taskCount = 0;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            }

            if (input.equals("list")) {
                System.out.println(line);
                System.out.println("list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
            } else {
                tasks[taskCount] = input;
                taskCount++;

                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }

        }

    }
}
