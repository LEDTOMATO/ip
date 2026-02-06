package orange.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index the index of the task to delete (0-based)
     * @return the deleted task
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index the index of the task (0-based)
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets all tasks in the list.
     *
     * @return ArrayList of all tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword the keyword to search for
     * @return ArrayList of matching tasks
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}