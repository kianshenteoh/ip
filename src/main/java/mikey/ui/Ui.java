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
        if (t.getList().isEmpty()) {
            System.out.println(space("No tasks yet!"));
            return;
        }
        System.out.println(space("Here are the tasks in your list:"));
        for (int i = 0; i < t.getList().size(); i++) {
            int index = i + 1;
            Task currTask = t.getList().get(i);
            System.out.println("  " + index + ". " + currTask);
        }
        printLine();
    }

    public void printDeleteTask(Task t) {
        printLine();
        if (t != null) {
            System.out.println(space("Noted. I've removed this task:"));
            System.out.println(space(t.toString()));
            printLine();
        } else {
            System.out.println(space("Task does not exist!"));
            printLine();
        }
    }

    public void printAddTask(Task t, TaskList l) {
        printLine();
        System.out.println(space("Got it, I've added this task:"));
        System.out.println(space(t.toString()));
        System.out.println(space("Now you have " + l.getList().size() + " tasks in the list."));
        printLine();
    }

    public void printMarkTask(Task t) {
        printLine();
        if (t != null) {
            System.out.println(space("Nice! I've marked this task as done:"));
            System.out.println(space(t.toString()));
            printLine();
        } else {
            System.out.println(space("Task does not exist!"));
            printLine();
        }
    }

    public void printUnmarkTask(Task t) {
        printLine();
        if (t != null) {
            System.out.println(space("Ok, I've marked this task as not done yet:"));
            System.out.println(space(t.toString()));
            printLine();
        } else {
            System.out.println(space("Task does not exist!"));
            printLine();
        }
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
