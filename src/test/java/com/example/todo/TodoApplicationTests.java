package com.example.todo;

import com.example.todo.model.Todo;
import com.example.todo.repo.TodoRepo;
import com.example.todo.service.TodoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GradledemoApplicationTests {

    @Mock
    TodoRepo repo;

    @InjectMocks
    TodoServiceImpl todoService;

    @Test
    public void testAddTodo() {
        Todo todo = new Todo(1, "EAT", false);
        Mockito.when(repo.save(todo)).thenReturn(todo);

        Todo res = todoService.addTask(todo);
        Assertions.assertEquals(res.getTaskId(), todo.getTaskId());
    }

}
