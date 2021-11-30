package com.example.gradledemo.repo;

import com.example.gradledemo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo, Integer> {
}
