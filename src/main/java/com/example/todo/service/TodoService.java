package com.example.todo.service;

import com.example.todo.model.Todo;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getTodoList();

    Optional<Todo> getTodo(String id);

    Todo addTask(Todo task);

    ResponseEntity<?> deleteTask(int id);

    Todo updateTask(Todo task);

}
