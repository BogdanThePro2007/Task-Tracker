package manager;

import model.Epic;
import model.Subtask;
import model.Task;
import model.enums.TaskStatus;

import java.util.List;

public interface TaskManager {

    Task createTask(String name, String description, String status);

    Task createEpic(String name, String description);

    Subtask createSubtask(String name, String description, TaskStatus status, long epicId);

    Task updateTask(String name, String description, String status, long taskId);

    Epic updateEpic(String name, String description, long epicId);

    Subtask updateSubtask(String name, String description, TaskStatus status, long subtaskId);

    Task getTaskById(int taskId);

    List<Subtask> getAllSubtaskByEpic(long epicId);

    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    void deleteTask(int taskId);

    void deleteAllSubtasksByEpic(long epicId);

    void deleteAllTasks();

    void deleteAllEpics();
}
