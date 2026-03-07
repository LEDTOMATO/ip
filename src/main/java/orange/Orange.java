package orange;

import orange.command.Command;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.TaskList;
import orange.ui.Ui;

/**
 * Orange is a task management chatbot.
 */
public class Orange {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            return "Haha  (´ー`)  " + e.getMessage() + "\nLet's try that again!";
        }
    }
}
