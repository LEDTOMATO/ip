package orange.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Deadline class.
 */
public class DeadlineTest {

    @Test
    public void constructor_validInput_success() {
        Deadline deadline = new Deadline("return book", "2025-03-15");

        assertEquals("return book", deadline.getDescription());
        assertFalse(deadline.isDone());
    }

    @Test
    public void constructor_invalidDate_throwsException() {
        assertThrows(Exception.class, () -> {
            new Deadline("return book", "invalid-date");
        });
    }

    @Test
    public void toString_validDeadline_correctFormat() {
        Deadline deadline = new Deadline("return book", "2025-03-15");

        String result = deadline.toString();

        assertTrue(result.contains("[D]"));
        assertTrue(result.contains("return book"));
        assertTrue(result.contains("Mar 15 2025"));
    }

    @Test
    public void toFileString_validDeadline_correctFormat() {
        Deadline deadline = new Deadline("return book", "2025-03-15");
        deadline.markDone();

        String result = deadline.toFileString();

        assertEquals("D | 1 | return book | 2025-03-15", result);
    }
}