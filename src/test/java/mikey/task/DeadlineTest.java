package mikey.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    void deadline_toSaveString_usesFormatter() {
        Deadline d = new Deadline("x", LocalDateTime.of(2025, 8, 25, 14, 0));
        String s = d.toSaveString();
        assertTrue(s.startsWith("D | 0 | x | "));
        assertTrue(s.contains("Aug")); // "MMM dd yyyy, h.mma"
    }
}
