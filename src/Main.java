import manager.TaskManager;
import manager.impl.TaskManagerImpl;
import repository.impl.RepositoryImpl;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManagerImpl(RepositoryImpl.getRepository());
    }
}