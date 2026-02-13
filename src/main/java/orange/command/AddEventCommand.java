package orange.command;

import orange.storage.Storage;
import orange.task.Event;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an AddEventCommand with the specified description, start date, and end date.
     *
     * @param description the event description
     * @param from the start date
     * @param to the end date
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            Event event = new Event(description, from, to);
            tasks.addTask(event);
            storage.save(tasks.getTasks());

            return "Got it. I've added this task:\n  " + event + "\n"
                    + "Now you have " + tasks.getSize() + " tasks in the list.";
        } catch (Exception e) {
            throw new Exception("Invalid date format! Use yyyy-MM-dd (e.g. 2025-02-03)");
        }
    }
}