package cl.coopeuch.challenge.services;

import cl.coopeuch.challenge.entities.Task;
import cl.coopeuch.challenge.exceptions.DoNotExistsException;
import cl.coopeuch.challenge.exceptions.NotAllowedException;
import cl.coopeuch.challenge.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    public static final String ID_NOT_ALLOW_MESSAGE = "No puede indicar un ID a una tarea nueva, esta se autoasigna";
    public static final String ID_IS_MISSING_MESSAGE = "Debe indicar el ID de la tarea para actualizarla";
    public static final String TASK_DO_NOT_EXISTS_MESSAGE = "La tarea ID %d no existe";
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTasks() {
        List<Task> finalResult = new ArrayList<>();
        Iterable<Task> resultFromDataBase = taskRepository.findAll();
        resultFromDataBase.forEach(finalResult::add);
        return finalResult;
    }

    public Task addNewTask(Task task) throws NotAllowedException {
        if(task.getId() != 0)
            throw new NotAllowedException(ID_NOT_ALLOW_MESSAGE);
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) throws NotAllowedException, DoNotExistsException {
        if(task.getId() < 1)
            throw new NotAllowedException(ID_IS_MISSING_MESSAGE);
        if(!taskRepository.findById(task.getId()).isPresent())
            throw new DoNotExistsException(String.format(TASK_DO_NOT_EXISTS_MESSAGE, task.getId()));
        return taskRepository.save(task);
    }

    public void removeTask(Task task) throws NotAllowedException, DoNotExistsException {
        if(task.getId() < 1)
            throw new NotAllowedException(ID_IS_MISSING_MESSAGE);
        if(!taskRepository.findById(task.getId()).isPresent())
            throw new DoNotExistsException(String.format(TASK_DO_NOT_EXISTS_MESSAGE, task.getId()));
        taskRepository.deleteById(task.getId());
    }
}
