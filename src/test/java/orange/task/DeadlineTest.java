package orange.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Deadline class.
 */
public class DeadlineTest {

    @Test
    public void constructor_validInput_success() {
        // Test that Deadline can be created with valid date (positive case)
        Deadline deadline = new Deadline("return book", "2025-03-15");

        assertEquals("return book", deadline.getDescription());
        assertFalse(deadline.isDone());
    }

    @Test
    public void constructor_invalidDate_throwsException() {
        // Test that invalid date format throws exception (negative case)
        assertThrows(Exception.class, () -> {
            new Deadline("return book", "invalid-date");
        });
    }

    @Test
    public void toString_validDeadline_correctFormat() {
        // Test that deadline displays with formatted date
        Deadline deadline = new Deadline("return book", "2025-03-15");

        String result = deadline.toString();

        assertTrue(result.contains("[D]"));
        assertTrue(result.contains("return book"));
        assertTrue(result.contains("Mar 15 2025"));
    }

    @Test
    public void toFileString_validDeadline_correctFormat() {
        // Test file format includes ISO date format
        Deadline deadline = new Deadline("return book", "2025-03-15");
        deadline.markDone();

        String result = deadline.toFileString();

        assertEquals("D | 1 | return book | 2025-03-15", result);
    }
}