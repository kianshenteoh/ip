package mikey.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task instance
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon depending on whether the task is completed
     *
     * @return X if complete, blank if incomplete
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task
     *
     * @return Description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string to be stored in storage file
     *
     * @return String to be saved
     */
    public String toSaveString() {
        return "? | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
