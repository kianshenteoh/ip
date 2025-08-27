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

    public void printInvalidCommandError() {
        printLine();
        System.out.println(space("ERROR: Please input a valid command"));
        printLine();
    }


    public void printMarkError() {
        printLine();
        System.out.println(space("ERROR: provide a valid task number! E.g. mark 1"));
        printLine();
    }

    public void printUnmarkError() {
        printLine();
        System.out.println(space("ERROR: provide a valid task number! E.g. unmark 1"));
        printLine();
    }

    public void printDeleteError() {
        printLine();
        System.out.println(space("ERROR: provide a valid task number! E.g. delete 1"));
        printLine();
    }

    public void printTodoError() {
        printLine();
        System.out.println(space("ERROR: The description of a todo cannot be empty! Use: todo <desc>"));
        printLine();
    }

    public void printDeadlineDescError() {
        printLine();
        System.out.println(space("ERROR: The description of a deadline cannot be empty! " +
                "Use: deadline <desc> /by <time>"));
        printLine();
    }

    public void printDeadlineByError() {
        printLine();
        System.out.println(space("ERROR: A deadline needs '/by <time>'. Use: deadline <desc> /by <time>"));
        printLine();
    }

    public void printInvalidDateError() {
        printLine();
        System.out.println(space("ERROR: please use format D/MM/YYYY HHMM (e.g. 2/12/2019 1800)"));
        printLine();
    }

    public void printEventDescError() {
        printLine();
        System.out.println(space("ERROR: The description of an event cannot be empty! " +
                "Use: event <desc> /from <start> /to <end>"));
        printLine();
    }

    public void printEventStartError() {
        printLine();
        System.out.println(space("ERROR: An event needs '/from <start>. " +
                "Use: event <desc> /from <start> /to <end>"));
        printLine();
    }

    public void printEventEndError() {
        printLine();
        System.out.println(space("ERROR: An event needs a start time and end time. " +
                "Use: event <desc> /from <start> /to <end>"));
        printLine();
    }
}
