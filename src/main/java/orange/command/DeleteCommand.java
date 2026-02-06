package orange.command;

import orange.storage.Storage;
import orange.task.Task;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber the task number (1-based)
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        int index = taskNumber - 1;

        if (index < 0 || index >= tasks.getSize()) {
            throw new Exception("The task number provided is invalid.");
        }

        Task removedTask = tasks.deleteTask(index);
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage("  " + removedTask);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        storage.save(tasks.getTasks());
    }
}