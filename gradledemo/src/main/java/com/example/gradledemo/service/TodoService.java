package com.example.gradledemo.service;

import com.example.gradledemo.model.Todo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoService {
    public List<Todo> getTodoList();

    public Todo addTask(Todo task);

    public ResponseEntity deleteTask(int id);

    public Todo updateTask(Todo task);
}
