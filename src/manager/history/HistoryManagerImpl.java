package manager.history;

import manager.history.HistoryManager;
import model.Task;
import repository.HistoryRepository;

import java.util.List;

public class HistoryManagerImpl implements HistoryManager {

    private HistoryRepository repository;

    public HistoryManagerImpl(HistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addToHistory(Task task) {
        repository.addTaskToHistory(task);
    }

    @Override
    public List<Task> getHistory() {
        return repository.getHistory();
    }
}
