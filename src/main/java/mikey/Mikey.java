package mikey;

import mikey.parser.Parser;
import mikey.storage.Storage;
import mikey.task.Deadline;
import mikey.task.Event;
import mikey.task.TaskList;
import mikey.task.Todo;
import mikey.ui.Ui;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Mikey {
    private static final String LINE = "  ___________________________________________________________";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private static void printLine() {
        System.out.println(LINE);
    }

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
                    input = scanner.nextLine();
                    return;
                case MARK:
                    tasks.markTask(result.arguments.index - 1);
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case UNMARK:
                    tasks.unmarkTask(result.arguments.index - 1);
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case TODO:
                    tasks.addTask(new Todo(result.arguments.description));
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case DEADLINE:
                    tasks.addTask(new Deadline(result.arguments.description, result.arguments.byRaw));
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case EVENT:
                    tasks.addTask(new Event(result.arguments.description, result.arguments.fromRaw,
                            result.arguments.toRaw));
                    storage.save(tasks.getList());
                    input = scanner.nextLine();
                    break;
                case DELETE:
                    tasks.deleteTask(result.arguments.index - 1);
                    storage.save(tasks.getList());
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
