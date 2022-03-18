package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class TodoController {
    @Autowired
    TodoServiceImpl todoService;

    @GetMapping
    public RedirectView redirectToSwaggerUI(){
        return new RedirectView("swagger-ui/index.html");
    }

    @GetMapping("/todo")
    public String rootController(){
        return "Todo application API";
    }

    @GetMapping("/todo/all")
    public ResponseEntity<?> getTodoList() {
        return todoService.getTodoList();
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<?> getTodo(@PathVariable String id) {
        return todoService.getTodo(id);
    }

    @PostMapping("/todo/add")
    public ResponseEntity<?> addTask(@RequestBody Todo task) {
        return todoService.addTask(task);
    }

    @DeleteMapping("/todo/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        return todoService.deleteTask(id);
    }

    @PutMapping("/todo/update")
    public ResponseEntity<?> updateTask(@RequestBody Todo task) {
        return todoService.updateTask(task);
    }

}
