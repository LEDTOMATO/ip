package orange;

import orange.command.Command;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Orange is a task management chatbot that helps users manage
 * todos, deadlines, and events via text command.
 */
public class Orange {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an Orange instance with the specified file path.
     *
     * @param filePath path to the data file
     */
    public Orange(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main entry point of the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Orange("data/orange.txt").run();
    }
}