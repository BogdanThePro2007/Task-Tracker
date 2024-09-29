package repository.impl;

import manager.history.CustomLinkedList;
import model.Epic;
import model.Subtask;
import model.Task;
import repository.HistoryRepository;
import repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryImpl<T extends Task> implements Repository<T>, HistoryRepository {

    private long id = 0;
    Map<Long, T> taskStorage = new HashMap<>();
    CustomLinkedList<Task> taskHistory = new CustomLinkedList<>();

    private static Repository repository = new RepositoryImpl();

    private static HistoryRepository historyRepository = new RepositoryImpl<>();

    private RepositoryImpl() {
    }

    public static Repository getRepository() {
        if (repository == null) repository = new RepositoryImpl();
        return repository;
    }

    public static HistoryRepository getHistoryRepository() {
        if (historyRepository == null) historyRepository = new RepositoryImpl();
        return historyRepository;
    }

    @Override
    public List<T> getAll() {
        if (!taskStorage.isEmpty()) {
            return new ArrayList<>(taskStorage.values());
        } else {
            throw new RuntimeException("Хранилище пусто!");
        }
    }
    @Override
    public T saveTask(T task) {
        task.setId(id++);
        taskStorage.put(task.getId(), task);
        taskStorage.put(task.getId(), task);
        return task;
    }

    @Override
    public T getTaskById(long taskId) {
        if (!taskStorage.containsKey(taskId)) {
            throw new RuntimeException("Задача " + taskId + " не найдена");
        }
        return taskStorage.get(taskId);
    }

    @Override
    public List<Task> getAllTask() {
        if (!taskStorage.isEmpty()) {
            List<Task> taskList = new ArrayList<>();
            for (T task : taskStorage.values()) {
                if (!(task instanceof Epic) && !(task instanceof Subtask)) {
                    taskList.add(task);
                }
            }
            return taskList;
        } else {
            throw new RuntimeException("Нет ни одной задачи");
        }
    }

    @Override
    public void deleteTask(long taskId) {
        if (taskStorage.containsKey(taskId)) {
            taskStorage.remove(taskId);
        } else throw new RuntimeException("Задача " + taskId + " не найдена");
    }

    @Override
    public void deleteAllTasks() {
        if (!taskStorage.isEmpty()) {
            taskStorage.clear();
        } else {
            throw new RuntimeException("Нет ни одной задачи");
        }
    }

    @Override
    public List<Subtask> getAllSubtaskByEpic(long epicId) {
        if (taskStorage.get(epicId) instanceof Epic) {
            return ((Epic) taskStorage.get(epicId)).getSubtaskList();
        } else {
            throw new RuntimeException("Эпик " + epicId + " не найден");
        }
    }

    @Override
    public List<Epic> getAllEpics() {
        if (!taskStorage.isEmpty()) {
            List<Epic> epicList = new ArrayList<>();
            for (T epic : taskStorage.values()) {
                if (epic instanceof Epic) {
                    epicList.add((Epic) epic);
                }
            }
            return epicList;
        } else {
            throw new RuntimeException("Нет ни одного эпика");
        }
    }

    @Override
    public void deleteAllSubtasksByEpic(long epicId) {
        if (taskStorage.get(epicId) instanceof Epic) {
            ((Epic) taskStorage.get(epicId))
                    .getSubtaskList()
                    .clear();
        }
    }

    @Override
    public void deleteAllEpics() {
        if (!getAllEpics().isEmpty()) {
            for (Epic epic : getAllEpics()) {
                taskStorage.remove(epic.getId());
            }
        } else {
            throw new RuntimeException("Нет ни одного эпика");
        }
    }

    @Override
    public void addTaskToHistory(Task task) {
        if (taskHistory.size() >= 10) {
            taskHistory.removeHead();
        }
        taskHistory.linkLast(task);
    }

    @Override
    public List<Task> getHistory() {
        return taskHistory.getTasks();
    }

    @Override
    public void removeTaskFromHistory(long taskId) {
        if (taskHistory.contains(taskId)) {
            taskHistory.removeTaskById(taskId);
        } else throw new RuntimeException("Задача " + taskId + " не найдена");
    }

    @Override
    public void clearHistory() {
        taskHistory.clear();
    }
}