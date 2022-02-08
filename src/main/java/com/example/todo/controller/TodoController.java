package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoServiceImpl todoService;

    @GetMapping("")
    public List<Todo> getTodoList() {
        return todoService.getTodoList();
    }

    @PostMapping("/add")
    public Todo addTask(@RequestBody Todo task) {
        System.out.println(task);
        return todoService.addTask(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        return todoService.deleteTask(id);
    }

    @PutMapping("/update")
    public Todo updateTask(@RequestBody Todo task) {
        return todoService.updateTask(task);
    }

}
