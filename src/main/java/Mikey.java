import java.util.Scanner;

public class Mikey {
    private static final String LINE = "  ___________________________________________________________";

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
            System.out.println("  " + input);
            printLine();
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
