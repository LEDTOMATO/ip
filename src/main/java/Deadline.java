import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific date.
 */
public class Deadline extends Task {
    private LocalDate by;


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
//
///**
// * Represents a task that must be completed by a specific date.
// */
//public class Deadline extends Task {
//    protected String by;
//
//    public Deadline(String description, String by) {
//        super(description, TaskType.DEADLINE);
//        this.by = by;
//    }
//
//    @Override
//    public String toString() {
//        return super.toString() + " (by: " + by + ")";
//    }
//
//    @Override
//    public String toFileString() {
//        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
//    }
//
//}
