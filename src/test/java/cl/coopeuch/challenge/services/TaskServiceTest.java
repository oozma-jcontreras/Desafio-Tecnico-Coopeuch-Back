package cl.coopeuch.challenge.services;

import cl.coopeuch.challenge.entities.Task;
import cl.coopeuch.challenge.entities.TaskMock;
import cl.coopeuch.challenge.exceptions.DoNotExistsException;
import cl.coopeuch.challenge.exceptions.NotAllowedException;
import cl.coopeuch.challenge.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @Test
    public void getAllTask() {
        List<Task> expected = new ArrayList<>();
        expected.add(TaskMock.getWithId());
        when(taskRepository.findAll()).thenReturn(expected);
        assertEquals(expected, taskService.findAllTasks());
    }

    @Test
    public void addTaskGivenANewTask() throws NotAllowedException {
        when(taskRepository.save(TaskMock.getWithoutId())).thenReturn(TaskMock.getWithId());
        assertEquals(TaskMock.getWithId(), taskService.addNewTask(TaskMock.getWithoutId()));
    }

    @Test
    public void throwNotAllowedExceptionWhenAddNewTaskWithAnID() throws NotAllowedException {
        NotAllowedException exception = assertThrows(NotAllowedException.class, () -> {
            taskService.addNewTask(TaskMock.getWithId());
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    public void updateTaskGivenAnExistedTask() throws NotAllowedException, DoNotExistsException {
        Task taskWithId = TaskMock.getWithId();
        Optional<Task> optionalTask = Optional.of(taskWithId);
        when(taskRepository.findById(taskWithId.getId())).thenReturn(optionalTask);
        when(taskRepository.save(TaskMock.getWithId())).thenReturn(TaskMock.getWithId());
        taskService.updateTask(taskWithId);
    }

    @Test
    public void throwNotAllowedExceptionWhenUpdateTaskWithEmptyID() throws NotAllowedException {
        NotAllowedException exception = assertThrows(NotAllowedException.class, () -> {
            taskService.updateTask(TaskMock.getWithoutId());
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    public void removeTaskGivenAnExistedTask() throws NotAllowedException, DoNotExistsException {
        Task taskWithId = TaskMock.getWithId();
        Optional<Task> optionalTask = Optional.of(taskWithId);
        when(taskRepository.findById(taskWithId.getId())).thenReturn(optionalTask);
        doNothing().when(taskRepository).deleteById(taskWithId.getId());
        taskService.removeTask(taskWithId);
    }

    @Test
    public void throwNotAllowedExceptionWhenDeleteTaskWithEmptyID() throws NotAllowedException {
        NotAllowedException exception = assertThrows(NotAllowedException.class, () -> {
            taskService.removeTask(TaskMock.getWithoutId());
        });
        assertNotNull(exception.getMessage());
    }
}
