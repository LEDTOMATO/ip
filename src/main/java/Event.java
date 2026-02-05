import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs within a date range.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return super.toString()
                + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | "
                + description + " | "
                + from.toString() + " | " + to.toString();
    }
}
