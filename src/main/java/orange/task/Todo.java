package orange.task;

/**
 * Represents a todo task without any date or time constraints.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with the given description.
     *
     * @param description the todo description
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
}