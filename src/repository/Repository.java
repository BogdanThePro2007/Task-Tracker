package repository;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface Repository<T extends Task> {

    List<T> getAll();

    ////////////////////////////////////////////////// TASKS

    T saveTask(T task);

    T getTaskById(long taskId);

    List<Task> getAllTask();

    void deleteTask(long taskId);

    void deleteAllTasks();

    ////////////////////////////////////////////////// EPICS

    List<Subtask> getAllSubtaskByEpic(long epicId);

    List<Epic> getAllEpics();

    void deleteAllSubtasksByEpic(long epicId);

    void deleteAllEpics();
}