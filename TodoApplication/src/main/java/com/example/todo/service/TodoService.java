package com.example.todo.service;

import com.example.todo.model.Todo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoService {
    List<Todo> getTodoList();

    Todo addTask(Todo task);

    ResponseEntity<?> deleteTask(int id);

    Todo updateTask(Todo task);
}
