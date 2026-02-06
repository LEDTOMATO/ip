package orange.command;

import orange.storage.Storage;
import orange.task.Task;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showMessage((i + 1) + ". " + tasks.getTask(i));
        }
    }
}