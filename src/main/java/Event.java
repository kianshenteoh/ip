import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h.mma");

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }
}
