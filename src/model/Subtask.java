package model;

import model.enums.TaskStatus;

public class Subtask extends Task {
    private final long epicId;
    public Subtask(String name, String description, TaskStatus status, long epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }
}