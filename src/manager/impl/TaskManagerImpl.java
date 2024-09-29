package manager.impl;

import manager.history.HistoryManager;
import manager.TaskManager;
import manager.history.HistoryManagerImpl;
import model.Epic;
import model.Subtask;
import model.Task;
import model.enums.TaskStatus;
import repository.HistoryRepository;
import repository.Repository;
import repository.impl.RepositoryImpl;

import java.util.List;

public class TaskManagerImpl<T extends Task> implements TaskManager {

    private Repository repository;

    public TaskManagerImpl(Repository repository) {
        this.repository = repository;
    }

    HistoryManager historyManager = new HistoryManagerImpl((HistoryRepository) RepositoryImpl.getRepository());

    @Override
    public Task createTask(String name, String description, String status) {
        Task newTask = new Task(name, description, TaskStatus.valueOf(status));
        Task task = repository.saveTask(newTask);
        historyManager.addToHistory(task);
        return task;
    }

    @Override
    public Epic createEpic(String name, String description) {
        Epic newEpic = new Epic(name, description);
        setEpicStatus(newEpic);
        Epic epic = (Epic) repository.saveTask(newEpic);
        historyManager.addToHistory(epic);
        return epic;
    }

    @Override
    public Subtask createSubtask(String name, String description, TaskStatus status, long epicId) {
        Subtask newSubtask = new Subtask(name, description, status, epicId);
        Subtask subtask = (Subtask) repository.saveTask(newSubtask);
        historyManager.addToHistory(subtask);
        return subtask;
    }

    @Override
    public Task updateTask(String name, String description, String status, long taskId) {
        Task oldTask = repository.getTaskById(taskId);
        if (oldTask != null) {
            oldTask.setName(name);
            oldTask.setDescription(description);
            Task task = repository.saveTask(oldTask);
            historyManager.addToHistory(task);
            return task;
        } else throw new RuntimeException("Задача " + taskId + " не найдена");
    }

    @Override
    public Epic updateEpic(String name, String description, long epicId) {
        Epic oldEpic = (Epic) repository.getTaskById(epicId);
        if (oldEpic != null) {
            oldEpic.setName(name);
            oldEpic.setDescription(description);
            Epic epic = (Epic) repository.saveTask(oldEpic);
            historyManager.addToHistory(epic);
            return epic;
        } else throw new RuntimeException("Эпик " + epicId + " не найден");
    }

    @Override
    public Subtask updateSubtask(String name, String description, TaskStatus status, long subtaskId) {
        Subtask oldSubtask = (Subtask) repository.getTaskById(subtaskId);
        if (oldSubtask != null) {
            oldSubtask.setName(name);
            oldSubtask.setDescription(description);
            Subtask subtask = (Subtask) repository.saveTask(oldSubtask);
            historyManager.addToHistory(subtask);
            return subtask;
        } else throw new RuntimeException("Подзадача " + subtaskId + " не найдена");
    }

    @Override
    public Task getTaskById(int taskId) {
        try {
            Task task = repository.getTaskById(taskId);
            historyManager.addToHistory(task);
            return task;
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Subtask> getAllSubtaskByEpic(long epicId) {
        return repository.getAllSubtaskByEpic(epicId);
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.getAllTask();
    }

    @Override
    public List<Epic> getAllEpics() {
        List<Epic> epics = repository.getAllEpics();
        for (Epic epic : epics) {
            setEpicStatus(epic);
        }
        return epics;
    }

    @Override
    public void deleteTask(int taskId) {
        repository.deleteTask(taskId);
    }

    @Override
    public void deleteAllSubtasksByEpic(long epicId) {
        repository.deleteAllSubtasksByEpic(epicId);
    }

    @Override
    public void deleteAllTasks() {
        repository.deleteAllTasks();
    }

    @Override
    public void deleteAllEpics() {
        repository.deleteAllEpics();
    }

    private boolean isAllSubtaskStatusInProgress(List<Subtask> subtasks) {
        if (!subtasks.isEmpty()) {
            for (Subtask subtask : subtasks) {
                if (subtask.getStatus() == TaskStatus.IN_PROGRESS) {
                    return true;
                }
            }
        } return false;
    }

    private boolean isAllSubtaskStatusDone(List<Subtask> subtasks) {
        if (!subtasks.isEmpty()) {
            for (Subtask subtask : subtasks) {
                if (subtask.getStatus() != TaskStatus.DONE) return false;
            } return true;
        } return false;
    }

    private void setEpicStatus(Epic epic) {
        List<Subtask> subtasks = getAllSubtaskByEpic(epic.getId());
        if (!isAllSubtaskStatusInProgress(subtasks)) {
            if (!isAllSubtaskStatusDone(subtasks)) {
                epic.setStatus(TaskStatus.NEW);
            } else epic.setStatus(TaskStatus.DONE);
        } else epic.setStatus(TaskStatus.IN_PROGRESS);
    }
}