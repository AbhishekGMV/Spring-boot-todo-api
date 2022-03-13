package com.example.todo;

import com.example.todo.model.Todo;
import com.example.todo.repo.TodoRepo;
import com.example.todo.service.TodoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoApplicationTests {

    @Mock
    TodoRepo todoRepo;

    @InjectMocks
    TodoServiceImpl todoService;

    @Test
    public void testAddTodo() {
        Todo todo = new Todo(1, "EAT", true);
        when(todoRepo.save(Mockito.any(Todo.class))).thenReturn(todo);

        ResponseEntity<?> res = todoService.addTask(todo);
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = res.getStatusCode();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetTodoList() {
        Todo t1 = new Todo(1, "walk", false);
        Todo t2 = new Todo(2, "sleep", false);
        Todo t3 = new Todo(3, "code", true);
        Todo t4 = new Todo(4, "run", false);

        List<Todo> todoList = new ArrayList<>();
        todoList.add(t1);
        todoList.add(t2);
        todoList.add(t3);
        todoList.add(t4);

        when(todoRepo.findAll()).thenReturn(todoList); //Stub -> if I call method x, return y
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(todoList), todoService.getTodoList()); //getTodoList method depends on mock findAll method
        verify(todoRepo).findAll(); //Checks if findAll method was called exactly once
    }
}