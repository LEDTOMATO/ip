/**
 * Represents a todo task without any date or time constraints.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
}
