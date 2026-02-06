package orange.storage;

import orange.task.Deadline;
import orange.task.Event;
import orange.task.Task;
import orange.task.Todo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading tasks from and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath path to the data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDataFileExists();
    }

    /**
     * Ensures that the data file and its parent directory exist.
     */
    private void ensureDataFileExists() {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to initialize storage file.");
        }
    }

    /**
     * Loads tasks from the data file.
     *
     * @return ArrayList of tasks loaded from file
     * @throws IOException if file cannot be read
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Parses a single line from the data file and reconstructs a task.
     *
     * @param line one line from the data file
     * @return the parsed Task, or null if the line is corrupted
     */
    private Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;

        switch (type) {
            case "T":
                task = new Todo(description);
                break;

            case "D":
                if (parts.length >= 4) {
                    try {
                        task = new Deadline(description, parts[3]);
                    } catch (Exception e) {
                        return null;
                    }
                }
                break;

            case "E":
                if (parts.length >= 5) {
                    try {
                        task = new Event(description, parts[3], parts[4]);
                    } catch (Exception e) {
                        return null;
                    }
                }
                break;

            default:
                return null;
        }

        if (task != null && isDone) {
            task.markDone();
        }

        return task;
    }

    /**
     * Saves all tasks to the data file.
     *
     * @param tasks ArrayList of tasks to save
     * @throws IOException if file cannot be written
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Task task : tasks) {
                writer.println(task.toFileString());
            }
        }
    }
}