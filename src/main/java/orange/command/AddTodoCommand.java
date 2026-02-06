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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage("  " + todo);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}