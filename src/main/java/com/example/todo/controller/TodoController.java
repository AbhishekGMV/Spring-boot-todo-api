package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoServiceImpl todoService;

    @GetMapping("")
    public ResponseEntity<?> getTodoList() {
        return todoService.getTodoList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodo(@PathVariable String id) {
        return todoService.getTodo(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody Todo task) {
        return todoService.addTask(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        return todoService.deleteTask(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTask(@RequestBody Todo task) {
        return todoService.updateTask(task);
    }

}
