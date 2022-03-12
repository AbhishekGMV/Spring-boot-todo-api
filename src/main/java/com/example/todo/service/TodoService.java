package com.example.todo.service;

import com.example.todo.model.Todo;
import org.springframework.http.ResponseEntity;

public interface TodoService {
    ResponseEntity<?> getTodoList();

    ResponseEntity<?> getTodo(String id);

    ResponseEntity<?> addTask(Todo task);

    ResponseEntity<?> deleteTask(int id);

    ResponseEntity<?> updateTask(Todo task);

}
