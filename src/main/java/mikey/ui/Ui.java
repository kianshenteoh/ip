package mikey.ui;

import mikey.task.Task;
import mikey.task.TaskList;

import java.time.format.DateTimeFormatter;

public class Ui {
    private static final String LINE = "  ________________________________________________________";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final String SPACING = "  ";

    public String space(String string) {
        return SPACING + string;
    }
    public String printLine() {
        return LINE;
    }
    public String format(String string) {
        return LINE + "\n" + space(string) + "\n  " + LINE;
    }

    public String greet() {
        return format("Hello, I'm Mikey! \n  What can I do for you today?");
    }

    public String bye() {
        return format("Bye. Hope to see you again soon!");
    }

    public String printError(String message) {
        return format(message);
    }

    /**
     * Print the list of tasks
     * @param t TaskList to be printed
     * @return String to be printed
     */
    public String printTasks(TaskList t) {
        assert t != null : "TaskList must not be null";

        if (t.getList().isEmpty()) {
            return format("No tasks yet!");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(LINE + space("\n  Here are the tasks in your list:"));
        for (int i = 0; i < t.getList().size(); i++) {
            int index = i + 1;
            Task currTask = t.getList().get(i);
            sb.append("\n");
            sb.append("  " + index + ". " + currTask);
        }
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Print the message upon deleting a task
     * @param t Deleted task
     * @return String to be printed
     */
    public String printDeleteTask(Task t) {
        if (t != null) {
            return format("Noted. I've removed this task: \n  " + t.toString());
        } else {
            return format("Task does not exist!");
        }
    }

    /**
     * Print the message upon adding a task
     * @param t Added task
     * @return String to be printed
     */
    public String printAddTask(Task t, TaskList l) {
        return format("Got it, I've added this task: \n  " + space(t.toString())
                + "\n  Now you have " + l.getList().size() + " tasks in the list.");
    }

    /**
     * Print the message upon marking a task as done
     * @param t Marked task
     * @return String to be printed
     */
    public String printMarkTask(Task t) {
        if (t != null) {
            return format("Nice! I've marked this task as done:\n  " + t.toString());
        } else {
            return format("Task does not exist!");
        }
    }

    /**
     * Print the message upon marking a task as not done
     * @param t Unmarked task
     * @return String to be printed
     */
    public String printUnmarkTask(Task t) {
        if (t != null) {
            return format("Ok, I've marked this task as not done yet:\n  " + t.toString());
        } else {
            return format("Task does not exist!");
        }
    }

    /**
     * Print the message upon finding tasks
     * @param l TaskList that contains the tasks
     * @return String to be printed
     */
    public String printFoundTasks(TaskList l) {
        if (l.getList().isEmpty()) {
            return format("No matching tasks found!");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(LINE + "Here are the matching tasks in your list:");
            for (Task task : l.getList()) {
                sb.append("\n  " + task.toString());
            }
            return sb.toString();
        }
    }

}
