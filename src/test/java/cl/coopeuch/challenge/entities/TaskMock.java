package cl.coopeuch.challenge.entities;

import java.time.LocalDateTime;

public class TaskMock {
    public static Task getWithId() {
        Task task = getWithoutId();
        task.setId(10);
        return task;
    }

    public static Task getWithoutId() {
        Task task = new Task();
        task.setDescription("blablabla");
        task.setCreationDate(LocalDateTime.of(2021, 10, 13, 10, 10));
        task.setActive(true);
        return task;
    }
}
