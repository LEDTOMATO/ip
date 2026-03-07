package orange.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Event class.
 */
public class EventTest {

    @Test
    public void constructor_validDates_success() {
        // Test creating event with valid date range (positive case)
        Event event = new Event("project meeting", "2025-03-10", "2025-03-12");

        assertEquals("project meeting", event.getDescription());
        assertFalse(event.isDone());
    }

    @Test
    public void constructor_endBeforeStart_throwsException() {
        // Test that end date before start throws exception (negative case)
        assertThrows(Exception.class, () -> {
            new Event("meeting", "2025-03-12", "2025-03-10");
        });
    }

    @Test
    public void toString_validEvent_correctFormat() {
        // Test string representation includes both dates
        Event event = new Event("conference", "2025-05-01", "2025-05-03");

        String result = event.toString();

        assertTrue(result.contains("[E]"));
        assertTrue(result.contains("conference"));
        assertTrue(result.contains("May 01 2025"));
        assertTrue(result.contains("May 03 2025"));
    }

    @Test
    public void toFileString_markedEvent_correctFormat() {
        // Test file format for marked event
        Event event = new Event("workshop", "2025-06-10", "2025-06-12");
        event.markDone();

        String result = event.toFileString();

        assertEquals("E | 1 | workshop | 2025-06-10 | 2025-06-12", result);
    }
}