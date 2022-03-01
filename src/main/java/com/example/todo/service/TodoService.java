package com.example.todo.service;

import com.example.todo.model.Todo;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getTodoList();

    Optional<Todo> getTodo(int id);

    Mono<Optional<Todo>> getTodoMono(int id);

    Todo addTask(Todo task);

    ResponseEntity<?> deleteTask(int id);

    Todo updateTask(Todo task);

    Flux<Todo> getTodoFlux();
}
