package manager.task;

import constructor.EpicTask;
import constructor.SubTask;
import constructor.Task;

import java.io.IOException;
import java.util.ArrayList;

public interface TaskManager {

     int addTask(Task task) throws IOException;

     int addEpicTask(EpicTask epicTask) throws IOException;

     int addSubTaskToEpic(SubTask subTask) throws IOException;

     void printTasks();

     void printEpicTasks();
//
     void printSubTasks();

     void removeTasks();

     void removeEpicTasks();

     void removeSubTasks();

    Task getTaskById(int id) throws IOException;

    EpicTask getEpicById(int id) throws IOException;

     SubTask getSubTaskById(int id) throws IOException;

     void updateTask(Task task) throws IOException;

     void updateEpic(EpicTask epicTask) throws IOException;

     void updateSubtask(SubTask subTask) throws IOException;

     void removeTaskById(int id) throws IOException;

     void removeEpicById(int id) throws IOException;

     void removeSubTaskById(int id) throws IOException;

     ArrayList<SubTask> getEpicSubTasks(int epicId);

}
