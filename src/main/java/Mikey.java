import java.time.DateTimeException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mikey {
    private static final String LINE = "  ___________________________________________________________";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

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
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage.load());
        greet();

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            printLine();
            if (input.equals("list")) {
                taskList.printTasks();
                printLine();
            } else if (input.equals("mark") || input.startsWith("mark ")) {
                try {
                    if (input.equals("mark")) {
                        System.out.println("  ERROR: provide a valid task number! E.g. mark 1");
                    } else {
                        String[] words = input.split("\\s+");
                        if (words.length > 1) {
                            int index = Integer.parseInt(words[1]);
                            taskList.markTask(index - 1);
                            storage.save(taskList.getList());
                        } else {
                            System.out.println(" ERROR: provide a valid task number! E.g. mark 1");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  ERROR: provide a valid task number! E.g. mark 1");
                } finally {
                    printLine();
                }
            } else if (input.equals("unmark") || input.startsWith("unmark ")) {
                try {
                    if (input.equals("unmark")) {
                        System.out.println("  ERROR: provide a valid task number! E.g. unmark 1");
                    } else {
                        String[] words = input.split(" ");
                        if (words.length > 1) {
                            int index = Integer.parseInt(words[1]);
                            taskList.unmarkTask(index - 1);
                            storage.save(taskList.getList());
                        } else {
                            System.out.println("  ERROR: provide a valid task number! E.g. unmark 1");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  ERROR: provide a valid task number! E.g. unmark 1");
                } finally {
                    printLine();
                }
            }
            else if (input.equals("todo") || input.startsWith("todo ")) {
                String description = input.substring(4).trim();
                if (description.isEmpty()) {
                    System.out.println("  ERROR: The description of a todo cannot be empty! Use: todo <desc>");
                } else {
                    String[] words = input.split("todo ");
                    taskList.addTask(new Todo(words[1]));
                    storage.save(taskList.getList());
                }
                printLine();
            } else if (input.equals("deadline") || input.startsWith("deadline ")){
                String[] t = input.split("\\s+", 2);
                String params = (t.length > 1) ? t[1].trim() : "";

                if (params.isEmpty()) {
                    System.out.println("  ERROR: The description of a deadline cannot be empty! Use: deadline <desc> /by <time>");
                } else {
                    String[] d = params.split("\\s*/by\\s+", 2);
                    if (d.length < 2 || d[0].isBlank() || d[1].isBlank()) {
                        System.out.println("  ERROR: A deadline needs '/by <time>'. Use: deadline <desc> /by <time>");
                    } else {
                        try {
                            LocalDateTime by = LocalDateTime.parse(d[1], formatter);
                            taskList.addTask(new Deadline(d[0], by));
                            storage.save(taskList.getList());
                        } catch (DateTimeException e) {
                            System.out.println("  ERROR: please use format D/MM/YYYY HHMM (e.g. 2/12/2019 1800)");
                        }
                    }
                }
                printLine();
            } else if (input.equals("event") || input.startsWith("event ")) {
                String[] t = input.split("\\s+", 2);
                String params = (t.length > 1) ? t[1].trim() : "";

                if (params.isEmpty()) {
                    System.out.println("  ERROR: The description of an event cannot be empty! Use: event <desc> /from <start> /to <end>");
                } else {
                    String[] d = params.split("\\s*/from\\s+", 2);
                    if (d.length < 2 || d[0].isBlank()) {
                        System.out.println("  ERROR: An event needs '/from <start>. Use: event <desc> /from <start> /to <end>");
                    } else {
                        String[] times = d[1].split("\\s*/to\\s+", 2);
                        if (times.length < 2 || times[0].isBlank() || times[1].isBlank()) {
                            System.out.println("  ERROR: An event needs a start time and end time. Use: event <desc> /from <start> /to <end>");
                        } else {
                            try {
                                LocalDateTime from = LocalDateTime.parse(times[0], formatter);
                                LocalDateTime to = LocalDateTime.parse(times[1], formatter);
                                taskList.addTask(new Event(d[0], from, to));
                                storage.save(taskList.getList());
                            } catch (DateTimeException e) {
                                System.out.println(" ERROR: please use format D/MM/YYYY HHMM (e.g. 2/12/2019 1800)");
                            }
                        }
                    }
                }
                printLine();
            } else if (input.equals("delete") || input.startsWith("delete ")) {
                try {
                    if (input.equals("delete")) {
                        System.out.println("  ERROR: provide a valid task number! E.g. delete 1");
                    } else {
                        String[] words = input.split("\\s+");
                        if (words.length > 1) {
                            int index = Integer.parseInt(words[1]);
                            taskList.deleteTask(index - 1);
                            storage.save(taskList.getList());
                        } else {
                            System.out.println("  ERROR: provide a valid task number! E.g. delete 1");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  ERROR: provide a valid task number! E.g. delete 1");
                } finally {
                    printLine();
                }

            } else {
                System.out.println("  ERROR: Please input a valid command");
                printLine();
            }
            input = scanner.nextLine();
        }
        bye();
        scanner.close();
    }
}
