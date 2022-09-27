import constructor.EpicTask;
import constructor.SubTask;
import constructor.status.Status;
import manager.Managers;
import manager.history.HistoryManager;
import manager.task.FileBackedTasksManager;
import manager.task.InMemoryTaskManager;
import manager.task.TaskManager;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        TaskManager task = Managers.getDefaultTask();
        HistoryManager history = Managers.getDefaultHistory();
        InMemoryTaskManager taskManager = (InMemoryTaskManager) task;
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(new File("C:\\Users\\Daniel Greg\\Desktop\\task.csv"));
        fileBackedTasksManager.tasksFromFile();


        history.getHistory();
    }
}