import java.util.Scanner;

public class Orange {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String greeting = line +
                "Hello! I'm Orange\n" +
                "What can I do for you?\n" + line;
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            }
            System.out.println(line + input +"\n" + line);

        }

    }
}
