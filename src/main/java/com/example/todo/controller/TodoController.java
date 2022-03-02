package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoServiceImpl todoService;

    @GetMapping("")
    public List<Todo> getTodoList() {
//        List<Todo> todos = new ArrayList<>();
//
//        List<Notes> data = new ArrayList<>();
//        Notes n1 = new Notes(1, "Some description");
//        Notes n2 = new Notes(2, "Some other description");
//        Notes n3 = new Notes(3, "Some new description");
//        data.add(n1);
//        data.add(n2);
//        data.add(n3);
//
//        todos.add(new Todo(1,"Sleep", true ,data));
//
//        ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String res = mapper.writeValueAsString(todos);
//        System.out.println(res);
        return todoService.getTodoList();
    }

    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Todo> getFluxTodoList() {
        return todoService.getTodoFlux();
    }

    @GetMapping("/{id}")
    public Optional<Todo> getTodo(@PathVariable int id) {
        return todoService.getTodo(id);
    }

    @GetMapping("/mono/{id}")
    public Mono<Optional<Todo>> getTodoMono(@PathVariable int id) {
        return todoService.getTodoMono(id);
    }

    @PostMapping("/add")
    public Todo addTask(@RequestBody Todo task) {
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
