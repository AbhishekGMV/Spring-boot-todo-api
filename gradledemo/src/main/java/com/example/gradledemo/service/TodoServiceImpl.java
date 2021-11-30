package com.example.gradledemo.service;

import com.example.gradledemo.model.Todo;
import com.example.gradledemo.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepo todoRepo;

    @Override
    public List<Todo> getTodoList() {
        return todoRepo.findAll();
    }

    @Override
    public Todo addTask(Todo task) {
        return todoRepo.save(task);
    }

    @Override
    public ResponseEntity deleteTask(int id) {
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
            updatedTodo.setCompleted(todo.getCompleted());
            return todoRepo.save(todo);
        } else {
            return null;
        }
    }
}
