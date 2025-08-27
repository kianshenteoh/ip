package mikey.storage;

import mikey.task.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @TempDir Path tmp;

    @Test
    void firstRun_returnsEmptyList_whenFileMissing() {
        Path save = tmp.resolve("mikey.txt");
        Storage storage = new Storage(save.toString());
        assertTrue(storage.load().isEmpty());
    }

    @Test
    void save_then_load_roundTripsTasks() {
        Path save = tmp.resolve("mikey.txt");
        Storage storage = new Storage(save.toString());

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        tasks.add(new Deadline("submit report", LocalDateTime.of(2025, 8, 25, 14, 0)));
        tasks.add(new Event("CS lecture",
                LocalDateTime.of(2025, 8, 25, 9, 0),
                LocalDateTime.of(2025, 8, 25, 11, 0)));

        storage.save(tasks);
        List<Task> loaded = storage.load();

        assertEquals(tasks.size(), loaded.size());
        storage.save(loaded);
    }
}
