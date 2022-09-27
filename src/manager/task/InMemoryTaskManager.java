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

public class InMemoryTaskManager implements TaskManager {
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

    @Override
    public void addTask(Task task) throws IOException {
        taskId++;
        taskList.put(taskId, task);
        save();
    }

    @Override
    public void addEpicTask(EpicTask epicTask) throws IOException {
        taskId++;
        epicList.put(taskId, epicTask);
        save();
    }

    @Override
    public void addSubTaskToEpic(SubTask subTask) throws IOException {
        if (!Objects.equals(subTask.getStatus(), "NEW")) {
            epicList.get(subTask.getEpicId()).setStatus("IN_PROGRESS");
        }
        taskId++;
        subTaskList.put(taskId, subTask);
        save();
    }

    @Override
    public void printTasks() {

        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            Integer id = entry.getKey();
            Task task = entry.getValue();
            System.out.println("������ " + id + ": " + task.getName() + "\n��������: " + task.getDescription());
        }

    }

    @Override
    public void printEpicTasks() {

        for (Map.Entry<Integer, EpicTask> taskEntry : epicList.entrySet()) {
            Integer taskId = taskEntry.getKey();
            EpicTask task = taskEntry.getValue();

            System.out.println("���� " + taskId + ": " + task.getName() + "\n��������: " + task.getDescription());

        }
    }

    @Override
    public void printSubTasks() {

        for (Map.Entry<Integer, SubTask> epicEntry : subTaskList.entrySet()) {
            SubTask subTask = epicEntry.getValue();
            System.out.println("\t��������� ����� " + subTask.getEpicId() + ": " + subTask.getName() + "\n\t��������: " + subTask.getDescription());
        }
    }

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

    @Override
    public Task getTaskById(int id) {
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

    @Override
    public EpicTask getEpicById(int id) {
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

    @Override
    public SubTask getSubTaskById(int id) {
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

    @Override
    public void updateTask(Task task) {
        taskList.put(task.getId(), task);
    }

    @Override
    public void updateEpic(EpicTask epicTask) {
        epicList.put(epicTask.getId(), epicTask);
    }

    @Override
    public void updateSubtask(SubTask subTask) {
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

    @Override
    public void removeTaskById(int id) {
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

    @Override
    public void removeEpicById(int id) {
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

    @Override
    public void removeSubTaskById(int id) {
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

}
