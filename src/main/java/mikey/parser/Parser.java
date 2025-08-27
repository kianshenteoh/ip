package mikey.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public enum Command { LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE}
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static class Arguments {
        public String description;
        public Integer index;
        public LocalDateTime byRaw;
        public LocalDateTime fromRaw;
        public LocalDateTime toRaw;
    }

    public static class ParseResult {
        public Command command;
        public Arguments arguments;
        public boolean isError;
        public String errorMessage;
    }

    private static ParseResult error(String msg) {
        ParseResult e = new ParseResult();
        e.isError = true;
        e.errorMessage = msg;
        return e;
    }

    public static ParseResult parse(String input) {
        ParseResult r = new ParseResult();
        if (input == null || input.trim().isEmpty()) {
            return error("ERROR: Please input a valid command");
        }
        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0];
        String args = (parts.length == 2) ? parts[1].trim() : "";
        if (!command.isEmpty()) {
            switch (command) {
            case "bye":
                r.command = Command.BYE;
                break;
            case "list":
                r.command = Command.LIST;
                r.arguments = new Arguments();
                break;
            case "todo":
                if (args.isEmpty()) {
                    return error("ERROR: The description of a todo cannot be empty! Use: todo <desc>");
                }
                r.command = Command.TODO;
                r.arguments = new Arguments();
                r.arguments.description = args;
                break;
            case "deadline":
                if (args.isEmpty()) {
                    return error("ERROR: The description of a deadline cannot be empty! " +
                            "Use: deadline <desc> /by <time>");
                }
                r.command = Command.DEADLINE;
                r.arguments = new Arguments();
                String[] descDeadline = args.split("\\s*/by\\s+", 2);
                if (descDeadline.length < 2) {
                    return error("ERROR: A deadline needs '/by <time>'. Use: deadline <desc> /by <time>");
                }
                try {
                    LocalDateTime by = LocalDateTime.parse(descDeadline[1].trim(), FORMATTER);
                    r.arguments.description = descDeadline[0].trim();
                    r.arguments.byRaw = by;
                } catch (DateTimeException e) {
                    return error("ERROR: please use format D/M/YYYY HHMM (e.g. 2/12/2019 1800)");
                }
                break;
            case "event":
                if (args.isEmpty()) {
                    return error("ERROR: The description of an event cannot be empty! " +
                            "Use: event <desc> /from <start> /to <end>");
                }
                r.command = Command.EVENT;
                r.arguments = new Arguments();
                String[] descEvent = args.split("\\s*/from\\s+", 2);
                if (descEvent.length < 2) {
                    return error("ERROR: An event needs '/from <start>. Use: event <desc> /from <start> /to <end>");
                }
                String[] timesEvent = descEvent[1].split("\\s*/to\\s+", 2);
                if (timesEvent.length < 2) {
                    return error("ERROR: An event needs a start time and end time. " +
                            "Use: event <desc> /from <start> /to <end>");
                }
                try {
                    LocalDateTime from = LocalDateTime.parse(timesEvent[0].trim(), FORMATTER);
                    LocalDateTime to = LocalDateTime.parse(timesEvent[1].trim(), FORMATTER);
                    r.arguments.description = descEvent[0].trim();
                    r.arguments.fromRaw = from;
                    r.arguments.toRaw = to;
                } catch (DateTimeException e) {
                    return error("ERROR: please use format D/M/YYYY HHMM (e.g. 2/12/2019 1800)");
                }
                break;
            case "mark":
                if (args.isEmpty()) {
                    return error("ERROR: provide a valid task number! E.g. mark 1");
                }
                r.command = Command.MARK;
                r.arguments = new Arguments();
                try {
                    r.arguments.index = Integer.parseInt(args);
                } catch (NumberFormatException e) {
                    return error("ERROR: provide a valid task number! E.g. mark 1");
                }
                break;
            case "unmark":
                if (args.isEmpty()) {
                    return error("ERROR: provide a valid task number! E.g. unmark 1");
                }
                r.command = Command.UNMARK;
                r.arguments = new Arguments();
                try {
                    r.arguments.index = Integer.parseInt(args);
                } catch (NumberFormatException e) {
                    return error("ERROR: provide a valid task number! E.g. mark 1");
                }
                break;
            case "delete":
                if (args.isEmpty()) {
                    return error("ERROR: provide a valid task number! E.g. delete 1");
                }
                r.command = Command.DELETE;
                r.arguments = new Arguments();
                try {
                    r.arguments.index = Integer.parseInt(args);
                } catch (NumberFormatException e) {
                    return error("ERROR: provide a valid task number! E.g. delete 1");
                }
                break;
            }
        }
        if (r.command == null) {
            return error("ERROR: Please input a valid command");
        }
        return r;
    }
}
