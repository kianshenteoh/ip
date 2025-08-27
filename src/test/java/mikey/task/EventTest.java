package mikey.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {

    @Test
    void event_toSaveString_usesFormatter() {
        Event e = new Event("mtg",
                LocalDateTime.of(2025, 8, 25, 9, 0),
                LocalDateTime.of(2025, 8, 25, 10, 0));
        String s = e.toSaveString();
        assertTrue(s.startsWith("E | 0 | mtg | "));
        assertTrue(s.contains("Aug")); // not ISO like 2025-08-25T09:00
    }
}
