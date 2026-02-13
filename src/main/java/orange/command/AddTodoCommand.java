package orange.command;

import orange.storage.Storage;
import orange.task.TaskList;
import orange.task.Todo;
import orange.ui.Ui;

/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructs an AddTodoCommand with the specified description.
     *
     * @param description the todo description
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        storage.save(tasks.getTasks());

        return "Got it. I've added this task:\n  " + todo + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.";
    }
}