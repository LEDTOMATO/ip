package orange.command;

import orange.storage.Storage;
import orange.task.Task;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     *
     * @param taskNumber the task number (1-based)
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        int index = taskNumber - 1;

        if (index < 0 || index >= tasks.getSize()) {
            throw new Exception("The task number provided is invalid.");
        }

        Task task = tasks.getTask(index);

        if (!task.isDone()) {
            throw new Exception("This task is already marked as not done.");
        }

        task.unmark();
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage("  " + task);
        storage.save(tasks.getTasks());
    }
}