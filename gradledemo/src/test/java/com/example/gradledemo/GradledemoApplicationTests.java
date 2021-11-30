package com.example.gradledemo;

import com.example.gradledemo.model.Todo;
import com.example.gradledemo.repo.TodoRepo;
import com.example.gradledemo.service.TodoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GradledemoApplicationTests {

    @Mock
    TodoRepo repo;

    @InjectMocks
    TodoServiceImpl todoService;

    List<Todo> data;

    @Test
    public void testAddTodo() {
        Todo todo = new Todo(1, "EAT", false);
        Mockito.when(repo.save(todo)).thenReturn(todo);

        Todo res = todoService.addTask(todo);
        Assertions.assertEquals(res.getTaskId(), todo.getTaskId());
    }

}
