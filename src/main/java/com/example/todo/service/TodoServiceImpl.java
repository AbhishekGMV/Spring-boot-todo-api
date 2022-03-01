package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repo.TodoRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<Todo> getTodoFlux() {
        return Flux.fromIterable(todoRepo.findAll());
    }

//    @Override
//    public Flux<Todo> getTodoFlux() {
//        WebClient client = WebClient.create("http://localhost:8081");
//        Flux<Todo> todoFlux = client.get()
//                .uri("/todo")
//                .retrieve()
//                .bodyToFlux(Todo.class)
//                .log();
//        return todoFlux.delayElements(Duration.ofSeconds(1)).doOnNext(todo -> System.out.println("Got todo item: " + todo));
//    }

    @Override
    public Optional<Todo> getTodo(int id) {
        return todoRepo.findById(id);
    }

    @Override
    public Mono<Optional<Todo>> getTodoMono(int id) {
        return Mono.just(todoRepo.findById(id));
    }

//    @Override
//    public Mono<Todo> getTodoMono(int id) {
//        WebClient client = WebClient.create("http://localhost:8081");
//        Mono<Todo> todoMono = client.get()
//                .uri("/todo/{id}", id)
//                .retrieve()
//                .bodyToMono(Todo.class)
//                .log();
//
//        todoMono.subscribe(System.out::println);
//        return todoMono;
//    }

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
