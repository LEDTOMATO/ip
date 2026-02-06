package orange.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline with the given description and due date.
     *
     * @param description the task description
     * @param by the due date in yyyy-MM-dd format
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | "
                + description + " | " + by.toString();
    }
}