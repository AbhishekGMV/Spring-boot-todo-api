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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoApplicationTests {
    @Mock
    TodoRepo todoRepo;

    @InjectMocks //Inject the mocked services to this class
    TodoServiceImpl todoService;

    @Test
    public void addTodoTest() {
        Todo todo = new Todo(1, "EAT", true);
        when(todoRepo.save(any(Todo.class))).thenReturn(todo);

        ResponseEntity<?> res = todoService.addTask(todo);
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = res.getStatusCode();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addTodoTestException() {
        Todo todo = new Todo(1, "EAT", true);
        when(todoRepo.save(any(Todo.class))).thenThrow(new RuntimeException("Couldn't create task"));
        todoService.addTask(todo);
    }

    @Test
    public void testGetTodoList() {
        Todo t1 = new Todo(1, "walk", false);
        Todo t2 = new Todo(2, "sleep", false);

        List<Todo> todoList = new ArrayList<>();
        todoList.add(t1);
        todoList.add(t2);

        when(todoRepo.findAll()).thenReturn(todoList); //Stub -> if I call method x, return y
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(todoList), todoService.getTodoList()); //getTodoList method depends on mock findAll method
        verify(todoRepo).findAll(); //Checks if findAll method was called exactly once
    }

    @Test
    public void getTodoListTestException() {    //To reach catch block for code coverage
        when(todoRepo.findAll()).thenThrow(new RuntimeException("Couldn't fetch data"));
        todoService.getTodoList();
    }

    @Test
    public void getTodoTest() {    //To reach catch block for code coverage
        Todo todo = new Todo(1, "dummy", true);
        when(todoRepo.findById(any())).thenReturn(Optional.of(todo));
        todoService.getTodo("1");
    }

    @Test
    public void getTodoNotFoundException() {
        doReturn(null).when(todoRepo).findById(Mockito.any());
        todoService.getTodo("1");
    }

    @Test
    public void getTodoTestException() {
        Mockito.lenient().when(todoRepo.findById(any())).thenThrow(new RuntimeException("Couldn't fetch task"));
        todoService.getTodo("1");
    }

    @Test
    public void deleteTodoTest() {    //To reach catch block for code coverage
        todoService.deleteTask(1);
        verify(todoRepo).deleteById(any());
    }

    @Test
    public void deleteTodoException() {    //To reach catch block for code coverage
        doThrow(new RuntimeException("Something went wrong")).when(todoRepo).deleteById(Mockito.any());
        todoService.deleteTask(1);
    }

    @Test
    public void updateTodoTest() {    //To reach catch block for code coverage
        Todo todo = new Todo(1, "dummy", true);
        when(todoRepo.findById(1)).thenReturn(Optional.of(todo));
        todoService.updateTask(todo);
        verify(todoRepo).save(todo);
    }

    @Test
    public void updateTodoException() {    //To reach catch block for code coverage
        Todo todo = new Todo(1, "dummy", true);
        when(todoRepo.findById(1)).thenThrow(new RuntimeException("Couldn't update task"));
        todoService.updateTask(todo);
    }
}