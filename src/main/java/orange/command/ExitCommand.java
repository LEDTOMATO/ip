package orange.command;

import orange.storage.Storage;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showGoodbye();
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}