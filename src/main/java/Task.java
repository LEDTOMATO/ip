/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return type.getSymbol() + getStatusIcon() + " " + description;
    }

    /**
     * Converts the task into a string suitable for file storage.
     */
    public String toFileString() {
        return type.getSymbol().charAt(1) + " | "
                + (isDone ? "1" : "0") + " | "
                + description;
    }

}
