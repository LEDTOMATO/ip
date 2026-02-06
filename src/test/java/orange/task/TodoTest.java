package orange.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Todo class.
 */
public class TodoTest {

    @Test
    public void constructor_validDescription_success() {
        Todo todo = new Todo("test task");

        assertEquals("test task", todo.getDescription());
        assertFalse(todo.isDone());
    }

    @Test
    public void markDone_unmarkedTask_marksAsDone() {
        Todo todo = new Todo("test task");

        todo.markDone();

        assertTrue(todo.isDone());
        assertEquals("[X]", todo.getStatusIcon());
    }

    @Test
    public void toString_unmarkedTodo_correctFormat() {
        Todo todo = new Todo("test task");

        String result = todo.toString();

        assertEquals("[T][ ] test task", result);
    }

    @Test
    public void toString_markedTodo_correctFormat() {
        Todo todo = new Todo("test task");
        todo.markDone();

        String result = todo.toString();

        assertEquals("[T][X] test task", result);
    }

    @Test
    public void toFileString_unmarkedTodo_correctFormat() {
        Todo todo = new Todo("test task");

        String result = todo.toFileString();

        assertEquals("T | 0 | test task", result);
    }

    @Test
    public void toFileString_markedTodo_correctFormat() {
        Todo todo = new Todo("test task");
        todo.markDone();

        String result = todo.toFileString();

        assertEquals("T | 1 | test task", result);
    }
}