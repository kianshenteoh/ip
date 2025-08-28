package mikey.ui;

import mikey.task.Task;
import mikey.task.TaskList;

import java.time.format.DateTimeFormatter;

public class Ui {
    private static final String LINE = "  ___________________________________________________________";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final String SPACING = "  ";

    public String space(String string) {
        return SPACING + string;
    }
    public void printLine() {
        System.out.println(LINE);
    }

    public void greet() {
        printLine();
        System.out.println(space("Hello, I'm Mikey!"));
        System.out.println(space("What can I do for you today?"));
        printLine();
    }

    public void bye() {
        printLine();
        System.out.println(space("Bye. Hope to see you again soon!"));
        printLine();
    }

    public void printError(String message) {
        printLine();
        System.out.println(space(message));
        printLine();
    }

    public void printTasks(TaskList t) {
        printLine();
        t.printTasks();
        printLine();
    }

    public void printFoundTasks(TaskList l) {
        printLine();
        if (l.getList().isEmpty()) {
            System.out.println(space("No matching tasks found!"));
            printLine();
        } else {
            System.out.println(space("Here are the matching tasks in your list:"));
            for (Task task : l.getList()) {
                System.out.println(space(task.toString()));
            }
            printLine();
        }
    }
}
