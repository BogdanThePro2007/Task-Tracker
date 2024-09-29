package manager.impl;

import exception.FileCreateException;
import exception.FileReadException;
import exception.FileWriteException;
import manager.TaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import model.enums.TaskStatus;
import repository.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManagerImpl <T extends  Task> extends TaskManagerImpl  {

    private Path userData;

    public FileBackedTasksManagerImpl(Path path, Repository repository) throws IOException, InterruptedException {
        super(repository);
        if (Files.exists(path)) load(path);
    }

    protected void save(Path path) throws IOException, InterruptedException {

    }

    public void load(Path path) throws IOException, InterruptedException {

    }

    private void createFile(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new FileCreateException("Не удалость создать файл.");
            }
        }
    }

    private void writeDataInFile(List<String> taskList, String viewHistory) {
        try (BufferedWriter fr = new BufferedWriter(new FileWriter(this.userData.toFile(), StandardCharsets.UTF_8, true))) {
            for (String task : taskList) {
                fr.write(task + "\n");
            }
            fr.write("\n\n" + viewHistory);
            fr.flush();
        } catch (IOException e) {
            throw new FileWriteException("Не удалость записать файл.");
        }
    }

    private List<String> readDataFromFile(Path path) {
        final List<String> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.userData.toFile(), StandardCharsets.UTF_8))) {
            while (br.ready()) {
                String line = br.readLine();
                dataList.add(line);
            }
            dataList.remove(0);
            return dataList;
        } catch (IOException e) {
            throw new FileReadException("Не удалось прочитать файл");
        }
    }

    @Override
    public Task createTask(String name, String description, String status) {
        return null;
    }

    @Override
    public Epic createEpic(String name, String description) {
        return null;
    }

    @Override
    public Subtask createSubtask(String name, String description, TaskStatus status, long epicId) {
        return null;
    }

    @Override
    public Task updateTask(String name, String description, String status, long taskId) {
        return null;
    }

    @Override
    public Epic updateEpic(String name, String description, long epicId) {
        return null;
    }

    @Override
    public Subtask updateSubtask(String name, String description, TaskStatus status, long subtaskId) {
        return null;
    }

    @Override
    public Task getTaskById(int taskId) {
        return null;
    }

    @Override
    public List<Subtask> getAllSubtaskByEpic(long epicId) {
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public List<Epic> getAllEpics() {
        return null;
    }

    @Override
    public void deleteTask(int taskId) {

    }

    @Override
    public void deleteAllSubtasksByEpic(long epicId) {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteAllEpics() {

    }
}
