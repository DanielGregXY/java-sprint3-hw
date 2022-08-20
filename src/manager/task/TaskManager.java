package manager.task;

import constructor.EpicTask;
import constructor.SubTask;
import constructor.Task;

import java.util.ArrayList;

public interface TaskManager {

    public void addTask(Task task);

    public void addEpicTask(EpicTask epicTask);

    public void addSubTaskToEpic(SubTask subTask);

    public void printTasks();

    public void printEpicTasks();

    public void printSubTasks();

    public void removeTasks();

    public void removeEpicTasks();

    public void removeSubTasks();

    public Task getTaskById(int id);

    public EpicTask getEpicById(int id);

    public SubTask getSubTaskById(int id);

    public void updateTask(Task task);

    public void updateEpic(EpicTask epicTask);

    public void updateSubtask(SubTask subTask);

    public void removeTaskById(int id);

    public void removeEpicById(int id);

    public void removeSubTaskById(int id);

    public ArrayList<SubTask> getEpicSubTasks(int epicId);

}
