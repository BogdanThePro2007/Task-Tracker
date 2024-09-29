package manager.impl;

import manager.history.HistoryManager;
import manager.Managers;
import manager.TaskManager;
import manager.history.HistoryManagerImpl;
import repository.impl.RepositoryImpl;

public class ManagersImpl implements Managers {

    @Override
    public TaskManager getDefault() {
        return new TaskManagerImpl(RepositoryImpl.getRepository());
    }

    @Override
    public HistoryManager getDefaultHistory() {
        return new HistoryManagerImpl(RepositoryImpl.getHistoryRepository());
    }
}
