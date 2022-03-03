package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repo.TodoRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepo todoRepo;

    public TodoServiceImpl(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @Override
    public List<Todo> getTodoList() {
        return todoRepo.findAll();
    }

    @Override
    public Optional<Todo> getTodo(String id) {
        return todoRepo.findById(Integer.parseInt(id));
    }


    @Override
    public Todo addTask(Todo task) {
        return todoRepo.save(task);
    }

    @Override
    public ResponseEntity<?> deleteTask(int id) {
        try {
            todoRepo.deleteById(id);
            return ResponseEntity.status(200).body("Task deleted");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Couldn't delete the task");
        }
    }

    @Override
    public Todo updateTask(Todo todo) {
        Optional<Todo> oldTodo = todoRepo.findById(todo.getTaskId());
        if (oldTodo.isPresent()) {
            Todo updatedTodo = oldTodo.get();
            updatedTodo.setTitle(todo.getTitle());
            updatedTodo.setComplete(updatedTodo.isComplete());
            return todoRepo.save(updatedTodo);
        } else {
            return null;
        }
    }
}
