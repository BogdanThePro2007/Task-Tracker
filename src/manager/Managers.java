package manager;

import manager.history.HistoryManager;

public interface Managers {
    TaskManager getDefault();

    HistoryManager getDefaultHistory();
}
