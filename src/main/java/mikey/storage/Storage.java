package mikey.storage;

import mikey.task.Deadline;
import mikey.task.Event;
import mikey.task.Task;
import mikey.task.Todo;

import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Storage {
    private final Path savePath;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h.mma");

    /**
     * Initializes a Storage instance
     * @param filePath File path to be used as data file
     */
    public Storage(String filePath) {
        assert filePath != null : "File path must not be null";

        this.savePath = Paths.get(filePath);
        System.out.println("Using data file at: " + savePath.toAbsolutePath());
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
    }

    /**
     * Loads the data from savePath
     *
     * @return a list of tasks that are stored in savePath
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            //First run
            if (Files.notExists(savePath)) {
                return tasks;
            }
            List<String> lines = Files.readAllLines(savePath, StandardCharsets.UTF_8);
            for (String raw : lines) {
                String line = raw.trim();
                if (line.isEmpty()) {
                    continue;
                }

                //Split lines by spacer
                String[] p = line.split("\\s*\\|\\s*");
                if (p.length < 3) {
                    continue;
                }
                String type = p[0];
                boolean done = "1".equals(p[1]);
                String desc = p.length >= 3 ? p[2] : "";

                Task t;
                switch (type) {
                case "T":
                    t = new Todo(desc);
                    if (p.length > 3) {
                        handleTag(t, p[3]);
                    }
                    break;
                case "D":
                    String by = p[3];
                    LocalDateTime deadline = LocalDateTime.parse(by, FORMATTER);
                    t = new Deadline(desc, deadline);
                    if (p.length > 4) {
                        handleTag(t, p[4]);
                    }
                    break;
                case "E":
                    String from = p[3];
                    String to = p[4];
                    t = new Event(desc, LocalDateTime.parse(from, FORMATTER), LocalDateTime.parse(to, FORMATTER));
                    if (p.length > 5) {
                        handleTag(t, p[5]);
                    }
                    break;
                default:
                    //Skip unknown rows
                    continue;
                }
                if (done) {
                    t.markDone();
                }
                tasks.add(t);
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return tasks;
    }

    private void handleTag(Task t, String label) {
        t.markTagged();
        t.setTag(label);
    }

    /**
     * Saves tasks into the savePath file
     *
     * @param tasks List of tasks to be saved
     */
    public void save(List<Task> tasks) {
        assert tasks != null : "Task list must not be null";

        try {
            if (Files.notExists(savePath.getParent())) {
                Files.createDirectories(savePath.getParent());
            }
            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                lines.add(t.toSaveString());
            }
            Files.write(savePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return;
        }
    }
}
