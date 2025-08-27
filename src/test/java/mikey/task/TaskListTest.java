package mikey.task;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    void add_and_size_work() {
        TaskList list = new TaskList(List.of());
        list.addTask(new Todo("read book"));
        list.addTask(new Todo("write code"));
        assertEquals(2, list.size());
    }

    @Test
    void delete_removesItem() {
        TaskList list = new TaskList(List.of());
        list.addTask(new Todo("a"));
        list.addTask(new Todo("b"));
        list.deleteTask(0); // zero-based
        assertEquals(1, list.size());
    }

    @Test
    void mark_and_unmark_doNotCrash() {
        TaskList list = new TaskList(List.of());
        list.addTask(new Todo("task"));
        list.markTask(0);
        list.unmarkTask(0);
        assertEquals(1, list.size());
    }
}
