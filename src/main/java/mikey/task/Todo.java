package mikey.task;

public class Todo extends Task {

    /**
     * Initializes a Todo instance
     * @param description Description of task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
