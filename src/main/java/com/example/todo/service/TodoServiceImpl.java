package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repo.TodoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class.getName());
    private final TodoRepo todoRepo;

    //Constructor injection
    public TodoServiceImpl(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @Override
    public ResponseEntity<?> getTodoList() {
        try {
            List<Todo> todoList = todoRepo.findAll();
            logger.info("Successfully fetched task list");
            return ResponseEntity.status(HttpStatus.OK).body(todoList);
        } catch (Exception e) {
            logger.error("Error fetching task list: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
    }

    @Override
    public ResponseEntity<?> getTodo(String id) {
        try {
            Optional<Todo> task = todoRepo.findById(Integer.parseInt(id));

            if (task.isEmpty()) {
                logger.error("Error fetching task with id: " + id);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No task found for given id");
            }
            logger.info("Task fetched: " + task);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (Exception e) {
            logger.error("Error fetching the task: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error fetching the task:" + e);
        }
    }


    @Override
    public ResponseEntity<?> addTask(Todo todo) {
        try {
            Todo task = todoRepo.save(todo);
            logger.info("Task created: " + task);
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (Exception e) {
            logger.error("Error creating the task " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't create the task");
        }
    }

    @Override
    public ResponseEntity<?> deleteTask(int id) {
        try {
            todoRepo.deleteById(id);
            logger.info("Successfully deleted task: " + id);
            return ResponseEntity.status(HttpStatus.OK).body("Task deleted");
        } catch (Exception e) {
            logger.error("Error deleting task: " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't delete task");
        }
    }

    @Override
    public ResponseEntity<?> updateTask(Todo todo) {
        try {
            Optional<Todo> oldTodo = todoRepo.findById(todo.getTaskId());
            if (oldTodo.isPresent()) {
                Todo tempTodo = oldTodo.get();
                tempTodo.setTitle(todo.getTitle());
                tempTodo.setComplete(tempTodo.isComplete());
                Todo updatedTodo = todoRepo.save(tempTodo);
                logger.info("Task updated " + updatedTodo);
                return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
            } else {
                logger.error("Error updating the task");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task not found!");
            }
        } catch (Exception e) {
            logger.error("Error updating the task: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
