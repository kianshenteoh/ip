import java.util.Scanner;
import java.util.ArrayList;

public class Mikey {
    private static final String LINE = "  ___________________________________________________________";
    private static final ArrayList<String> list = new ArrayList<>();

    private static void printLine() {
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printLine();
        System.out.println("  Hello, I'm Mikey! \n  What can I do for you today?");
        printLine();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            printLine();
            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    System.out.println("  " + index + ": " + list.get(i));
                }
                printLine();
            } else {
                list.add(input);
                System.out.println("  " + input);
                printLine();
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
