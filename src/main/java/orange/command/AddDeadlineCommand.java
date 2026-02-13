package orange.command;

import orange.storage.Storage;
import orange.task.Deadline;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs an AddDeadlineCommand with the specified description and due date.
     *
     * @param description the task description
     * @param by the due date
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            Deadline deadline = new Deadline(description, by);
            tasks.addTask(deadline);
            storage.save(tasks.getTasks());

            return "Got it. I've added this task:\n  " + deadline + "\n"
                    + "Now you have " + tasks.getSize() + " tasks in the list.";
        } catch (Exception e) {
            throw new Exception("Invalid date format! Use yyyy-MM-dd (e.g. 2025-02-03)");
        }
    }
}