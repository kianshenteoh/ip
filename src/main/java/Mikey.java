import java.util.Scanner;
import java.util.ArrayList;

public class Mikey {
    private static final String LINE = "  ___________________________________________________________";
    private static final TaskList taskList = new TaskList();

    private static void printLine() {
        System.out.println(LINE);
    }

    private static void greet() {
        printLine();
        System.out.println("  Hello, I'm Mikey!");
        System.out.println("  What can I do for you today?");
        printLine();
    }

    private static void bye() {
        printLine();
        System.out.println("  Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            printLine();
            if (input.equals("list")) {
                taskList.printTasks();
                printLine();
            } else if (input.startsWith("mark ")) {
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1]);
                taskList.markTask(index - 1);
                printLine();
            } else if (input.startsWith("unmark ")) {
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1]);
                taskList.unmarkTask(index - 1);
                printLine();
            }
            else if (input.startsWith("todo ")) {
                String[] words = input.split("todo ");
                taskList.addTask(new Todo(words[1]));
                printLine();
            } else if (input.startsWith("deadline ")){
                String[] words = input.split("deadline | /by ");
                taskList.addTask(new Deadline(words[1], words[2]));
                printLine();
            } else if (input.startsWith("event ")) {
                String[] words = input.split("event | /from | /to ");
                taskList.addTask(new Event(words[1], words[2], words[3]));
                printLine();
            } else {
                System.out.println("  Please input a valid command");
                printLine();
            }
            input = scanner.nextLine();
        }
        bye();
        scanner.close();
    }
}
