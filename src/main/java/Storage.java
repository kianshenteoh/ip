import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Storage {
    private final Path savePath = Paths.get("data", "mikey.txt");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h.mma");

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
                String type = p[0];
                boolean done = "1".equals(p[1]);
                String desc = p.length >= 3 ? p[2] : "";

                Task t;
                switch (type) {
                case "T":
                    t = new Todo(desc);
                    break;
                case "D":
                    String by = p[3];
                    LocalDateTime deadline = LocalDateTime.parse(by, FORMATTER);
                    t = new Deadline(desc, deadline);
                    break;
                case "E":
                    String from = p[3];
                    String to = p[4];
                    t = new Event(desc, LocalDateTime.parse(from, FORMATTER), LocalDateTime.parse(to, FORMATTER));
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

    public void save(List<Task> tasks) {
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

        }
    }
}
