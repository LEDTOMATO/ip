package orange.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for TaskList class.
 */
public class TaskListTest {

    @Test
    public void addTask_singleTask_success() {
        // Test adding a task increases size by 1 (positive case)
        TaskList tasks = new TaskList();
        Todo todo = new Todo("test task");
        tasks.addTask(todo);

        assertEquals(1, tasks.getSize());
        assertEquals(todo, tasks.getTask(0));
    }

    @Test
    public void deleteTask_validIndex_success() {
        // Test deleting a valid task removes it from list (positive case)
        TaskList tasks = new TaskList();
        Todo todo = new Todo("test task");
        tasks.addTask(todo);

        Task deleted = tasks.deleteTask(0);

        assertEquals(0, tasks.getSize());
        assertEquals(todo, deleted);
    }

    @Test
    public void deleteTask_invalidIndex_throwsException() {
        // Test deleting invalid index throws exception (negative case)
        TaskList tasks = new TaskList();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            tasks.deleteTask(0);
        });
    }

    @Test
    public void findTasks_matchingKeyword_returnsMatches() {
        // Test finding tasks with matching keyword (positive case)
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read book"));
        tasks.addTask(new Todo("return book"));
        tasks.addTask(new Todo("buy food"));

        var results = tasks.findTasks("book");

        assertEquals(2, results.size());
    }

    @Test
    public void findTasks_noMatch_returnsEmpty() {
        // Test finding with non-matching keyword returns empty (negative case)
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("read book"));

        var results = tasks.findTasks("xyz");

        assertEquals(0, results.size());
    }
}