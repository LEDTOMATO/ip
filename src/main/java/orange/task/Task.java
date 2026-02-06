package orange.task;

/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a Task with the given description and type.
     *
     * @param description the task description
     * @param type the task type
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Gets the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return type.getSymbol() + getStatusIcon() + " " + description;
    }

    /**
     * Converts the task into a string suitable for file storage.
     *
     * @return the file format string
     */
    public String toFileString() {
        return type.getSymbol().charAt(1) + " | "
                + (isDone ? "1" : "0") + " | "
                + description;
    }
}