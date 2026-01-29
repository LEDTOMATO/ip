import java.util.Scanner;

public class Orange {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        System.out.println(line);
        System.out.println("Hello! I'm Orange :)\n");
        System.out.println("What can I do for you?\n");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();

            //Exit
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }

            //Show List
            if (input.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {//Mark done task
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks[index].markDone();

                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[index]);
                System.out.println(line);
            } else if (input.startsWith("unmark ")) {//Unmark task
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks[index].unmark();

                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[index]);
                System.out.println(line);
            } else {//Add task
                tasks[taskCount] = new Task(input);
                taskCount++;

                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }

    }
}
