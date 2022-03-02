package com.example.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;
    private String title;
    private boolean complete;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Notes> notes;
}

