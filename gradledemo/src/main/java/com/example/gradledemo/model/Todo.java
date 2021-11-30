package com.example.gradledemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private int taskId;
    private String title;
    private boolean completed;

    public Todo(int taskId, String title, boolean completed) {
        this.taskId = taskId;
        this.title = title;
        this.completed = completed;
    }

    public Todo() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int userId) {
        this.taskId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "DummyToDoModel{" +
                "taskId=" + taskId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

