package repository;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.List;

public interface HistoryRepository {

    void addTaskToHistory(Task task);

    List<Task> getHistory();

    void removeTaskFromHistory(long taskId);

    void clearHistory();
}
