package orange.command;

import orange.storage.Storage;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage handler
     * @throws Exception if an error occurs during execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Checks if this command should exit the program.
     *
     * @return true if the program should exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}