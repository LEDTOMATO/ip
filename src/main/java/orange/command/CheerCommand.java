package orange.command;

import orange.storage.Storage;
import orange.task.TaskList;
import orange.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a command to display a random motivational quote.
 */
public class CheerCommand extends Command {
    private static final String CHEER_FILE = "data/cheer.txt";
    private ArrayList<String> quotes;
    private Random random;

    /**
     * Constructs a CheerCommand and loads quotes from file.
     */
    public CheerCommand() {
        this.quotes = new ArrayList<>();
        this.random = new Random();
        loadQuotes();
    }

    /**
     * Loads motivational quotes from the cheer file.
     */
    private void loadQuotes() {
        try {
            File file = new File(CHEER_FILE);
            if (!file.exists()) {
                // Add default quotes if file doesn't exist
                addDefaultQuotes();
                return;
            }

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        quotes.add(line);
                    }
                }
            }

            if (quotes.isEmpty()) {
                addDefaultQuotes();
            }
        } catch (IOException e) {
            addDefaultQuotes();
        }
    }

    /**
     * Adds default quotes if file cannot be loaded.
     */
    private void addDefaultQuotes() {
        quotes.add("Keep going – even the best programmers started out writing 'Hello World'!");
        quotes.add("Debugging is like being a detective in a crime movie where you're also the murderer.");
        quotes.add("Code never lies, comments sometimes do.");
        quotes.add("First, solve the problem. Then, write the code.");
        quotes.add("Make it work, make it right, make it fast.");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (quotes.isEmpty()) {
            ui.showError("No motivational quotes available.");
            return;
        }

        int randomIndex = random.nextInt(quotes.size());
        String quote = quotes.get(randomIndex);
        ui.showMessage(quote);
    }
}