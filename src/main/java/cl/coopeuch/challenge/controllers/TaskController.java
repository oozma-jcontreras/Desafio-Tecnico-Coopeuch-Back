package cl.coopeuch.challenge.controllers;

import cl.coopeuch.challenge.entities.Task;
import cl.coopeuch.challenge.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/task")
public class TaskController {
    public static final String TASK_DELETED_MESSAGE = "Tarea eliminada correctamente";
    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            return new ResponseEntity<>(taskService.findAllTasks(), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Task> addTask(@Valid @RequestBody Task task) {
        try {
            return new ResponseEntity<>(taskService.addNewTask(task), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task) {
        try {
            return new ResponseEntity<>(taskService.updateTask(task), HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> removeTask(@Valid @RequestBody Task task) {
        try {
            taskService.removeTask(task);
            return new ResponseEntity<>(TASK_DELETED_MESSAGE, HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}