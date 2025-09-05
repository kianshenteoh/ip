package mikey;

import mikey.parser.Parser;
import mikey.storage.Storage;
import mikey.task.TaskList;
import mikey.task.Task;
import mikey.task.Deadline;
import mikey.task.Event;
import mikey.task.Todo;
import mikey.ui.Ui;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Mikey {
    private static final String LINE = "  ___________________________________________________________";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    private static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Initializes Mikey Object
     * @param filePath file path for data file
     */
    @SuppressWarnings("checkstyle:Regexp")
    public Mikey(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the program and asks for input
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greet();
        String input = scanner.nextLine();
        while (true) {
            Parser.ParseResult result = parser.parse(input);
            if (result.isError) {
                ui.printError(result.errorMessage);
                input = scanner.nextLine();
            } else {
                switch (result.command) {
                case LIST:
                    ui.printTasks(tasks);
                    input = scanner.nextLine();
                    break;
                case BYE:
                    ui.bye();
                    return;
                case MARK:
                    Task marked = tasks.markTask(result.arguments.index - 1);
                    ui.printMarkTask(marked);
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case UNMARK:
                    Task unmarked = tasks.unmarkTask(result.arguments.index - 1);
                    ui.printUnmarkTask(unmarked);
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case TODO:
                    Task todo = new Todo(result.arguments.description);
                    tasks.addTask(todo);
                    ui.printAddTask(todo, tasks);
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case DEADLINE:
                    Task deadline = new Deadline(result.arguments.description, result.arguments.byRaw);
                    tasks.addTask(deadline);
                    ui.printAddTask(deadline, tasks);
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case EVENT:
                    Task event = new Event(result.arguments.description, result.arguments.fromRaw,
                            result.arguments.toRaw);
                    tasks.addTask(event);
                    ui.printAddTask(event, tasks);
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case DELETE:
                    Task deleted = tasks.deleteTask(result.arguments.index - 1);
                    ui.printDeleteTask(deleted);
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case FIND:
                    TaskList foundTasks = tasks.findTasks(result.arguments.keyword);
                    ui.printFoundTasks(foundTasks);
                    input = scanner.nextLine();
                    break;
                default:
                }
            }
        }
    }

    public static void main(String[] args) {
        new Mikey("data/mikey.txt").run();
    }
}
