package mikey.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h.mma");

    /**
     * Initializes an Event instance
     * @param description Description of task
     * @param start start of event in "D/M/YYYY HHMM" format
     * @param end end of event in "D/M/YYYY HHMM" format
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + this.start.format(FORMATTER) + " | " +
                this.end.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(FORMATTER) + " to: " + end.format(FORMATTER) + ")";
    }
}
