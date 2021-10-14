package cl.coopeuch.challenge.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void testGetAndSetFieldsInTask() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 10, 13, 10, 10);
        Task task = TaskMock.getWithId();
        assertEquals(10, task.getId());
        assertEquals("blablabla", task.getDescription());
        assertEquals(localDateTime, task.getCreationDate());
        assertTrue(task.isActive());
    }
}
