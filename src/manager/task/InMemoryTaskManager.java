package manager.task;

import constructor.EpicTask;
import constructor.SubTask;
import constructor.Task;
import manager.Managers;
import manager.history.HistoryManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static manager.task.FileBackedTasksManager.save;

public abstract class InMemoryTaskManager implements TaskManager {
    public static int taskId = 0;
    static HashMap<Integer, Task> taskList = new HashMap<>();
    static HashMap<Integer, EpicTask> epicList = new HashMap<>();
    static HashMap<Integer, SubTask> subTaskList = new HashMap<>();

    static HistoryManager historyManager = Managers.getDefaultHistory();


    public static int getTaskIdc() {
        return taskId;
    }

    public static void setTaskIdc(int taskId) {
        InMemoryTaskManager.taskId = taskId;
    }
    //=================================================================================\\
    @Override
    public int addTask(Task task) throws IOException {
        taskId++;
        taskList.put(taskId, task);
        save();
        return task.getId();
    }
    //=================================================================================\\
    @Override
    public int addEpicTask(EpicTask epicTask) throws IOException {
        taskId++;
        epicList.put(taskId, epicTask);
        save();
        return epicTask.getId();
    }
    //=================================================================================\\
    @Override
    public int addSubTaskToEpic(SubTask subTask) throws IOException {
        if (!Objects.equals(subTask.getStatus(), "NEW")) {
            epicList.get(subTask.getEpicId()).setStatus("IN_PROGRESS");
        }
        taskId++;
        subTaskList.put(taskId, subTask);
        save();
        return subTask.getId();
    }
    //=================================================================================\\
    @Override
    public void printTasks() {

        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            Integer id = entry.getKey();
            Task task = entry.getValue();
            System.out.println("������ " + id + ": " + task.getName() + "\n��������: " + task.getDescription());
        }

    }
    //=================================================================================\\
    @Override
    public void printEpicTasks() {

        for (Map.Entry<Integer, EpicTask> taskEntry : epicList.entrySet()) {
            Integer taskId = taskEntry.getKey();
            EpicTask task = taskEntry.getValue();

            System.out.println("���� " + taskId + ": " + task.getName() + "\n��������: " + task.getDescription());

        }
    }
    //=================================================================================\\
    @Override
    public void printSubTasks() {

        for (Map.Entry<Integer, SubTask> epicEntry : subTaskList.entrySet()) {
            SubTask subTask = epicEntry.getValue();
            System.out.println("\t��������� ����� " + subTask.getEpicId() + ": " + subTask.getName() + "\n\t��������: " + subTask.getDescription());
        }
    }
    //=================================================================================\\
    @Override
    public void removeTasks() {
        taskList.clear();
    }

    @Override
    public void removeEpicTasks() {
        epicList.clear();
    }

    @Override
    public void removeSubTasks() {
        subTaskList.clear();
    }
    //=================================================================================\\//=================================================================================\\
    @Override
    public Task getTaskById(int id) throws IOException {
        Task result = null;

        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            Task task = entry.getValue();
            if (task.getId() == id) {
                result = task;
            }
        }

        historyManager.setHistory(result);
        return result;
    }
    //=================================================================================\\
    @Override
    public EpicTask getEpicById(int id) throws IOException {
        EpicTask result = null;

        for (Map.Entry<Integer, EpicTask> entry : epicList.entrySet()) {
            EpicTask task = entry.getValue();
            if (task.getId() == id) {
                result = task;
            }
        }

        historyManager.setHistory(result);
        return result;
    }
    //=================================================================================\\
    @Override
    public SubTask getSubTaskById(int id) throws IOException {
        SubTask result = null;

        for (Map.Entry<Integer, SubTask> entry : subTaskList.entrySet()) {
            SubTask task = entry.getValue();
            if (task.getId() == id) {
                result = task;
            }
        }

        historyManager.setHistory(result);
        return result;
    }
    //=================================================================================\\
    @Override
    public void updateTask(Task task) throws IOException {
        taskList.put(task.getId(), task);
    }
    //=================================================================================\\
    @Override
    public void updateEpic(EpicTask epicTask) throws IOException {
        epicList.put(epicTask.getId(), epicTask);


    }
    //=================================================================================\\
    @Override
    public void updateSubtask(SubTask subTask) throws IOException {
        subTaskList.put(subTask.getId(), subTask);

        ArrayList<SubTask> epicSubTask = getEpicSubTasks(subTask.getEpicId());

        int doneTask = 0;

        for (SubTask task : epicSubTask) {
            if (Objects.equals(task.getStatus(), "DONE")) {
                doneTask++;
            }
        }

        if (doneTask == epicSubTask.size()) {
            epicList.get(subTask.getEpicId()).setStatus("DONE");
        }

    }
    //=================================================================================\\
    @Override
    public void removeTaskById(int id) throws IOException {
        int removeTaskId = 0;

        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            int idInMap = entry.getKey();
            Task task = entry.getValue();
            if (task.getId() == id) {
                removeTaskId = idInMap;
            }
        }

        taskList.remove(removeTaskId);
        historyManager.remove(removeTaskId);
    }
    //=================================================================================\\
    @Override
    public void removeEpicById(int id) throws IOException {
        int removeTaskId = 0;

        for (Map.Entry<Integer, EpicTask> entry : epicList.entrySet()) {
            int idInMap = entry.getKey();
            EpicTask task = entry.getValue();
            if (task.getId() == id) {
                removeTaskId = idInMap;
            }
        }

        epicList.remove(removeTaskId);
        historyManager.remove(removeTaskId);
    }
    //=================================================================================\\
    @Override
    public void removeSubTaskById(int id) throws IOException {
        int removeTaskId = 0;

        for (Map.Entry<Integer, SubTask> entry : subTaskList.entrySet()) {
            int idInMap = entry.getKey();
            SubTask task = entry.getValue();
            if (task.getId() == id) {
                removeTaskId = idInMap;
            }
        }

        subTaskList.remove(removeTaskId);
        historyManager.remove(removeTaskId);
    }
    //=================================================================================\\
    @Override
    public ArrayList<SubTask> getEpicSubTasks(int epicId) {
        ArrayList<SubTask> EpicSubTasks = new ArrayList<>();

        for (Map.Entry<Integer, SubTask> entry : subTaskList.entrySet()) {
            int idInMap = entry.getKey();
            SubTask task = entry.getValue();
            if (task.getEpicId() == epicId) {
                EpicSubTasks.add(task);
            }
        }

        return EpicSubTasks;
    }

    public abstract int add(EpicTask epic) throws IOException;

    public abstract int add(SubTask subtask) throws IOException;
}
