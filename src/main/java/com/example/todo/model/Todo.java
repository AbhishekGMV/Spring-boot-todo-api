package com.example.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;
    private String title;
    private boolean isCompleted;

    public Todo(int taskId, String title, boolean isCompleted) {
        this.taskId = taskId;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    public Todo() {
    }

    public int getTaskId() { //getter method name reflects in field names of JSON response body
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}

