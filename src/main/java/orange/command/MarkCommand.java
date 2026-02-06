package orange.command;

import orange.storage.Storage;
import orange.task.Task;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a MarkCommand with the specified task number.
     *
     * @param taskNumber the task number (1-based)
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        int index = taskNumber - 1;

        if (index < 0 || index >= tasks.getSize()) {
            throw new Exception("The task number provided is invalid.");
        }

        Task task = tasks.getTask(index);

        if (task.isDone()) {
            throw new Exception("This task has already been marked as done.");
        }

        task.markDone();
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage("  " + task);
        storage.save(tasks.getTasks());
    }
}