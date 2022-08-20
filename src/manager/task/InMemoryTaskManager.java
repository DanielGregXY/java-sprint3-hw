package manager.task;

import constructor.EpicTask;
import constructor.SubTask;
import constructor.Task;
import manager.Managers;
import manager.history.HistoryManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryTaskManager implements TaskManager {
    public int epicId = 0;
    public int subTasksId = 0;
    int tasksId = 0;
    HashMap<Integer, Task> taskList = new HashMap<>();
    HashMap<Integer, EpicTask> epicList = new HashMap<>();
    HashMap<Integer, SubTask> subTaskList = new HashMap<>();

    HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public void addTask(Task task) {
        tasksId++;
        taskList.put(tasksId, task);
    }

    @Override
    public void addEpicTask(EpicTask epicTask) {
        epicId++;
        epicList.put(epicId, epicTask);
    }

    @Override
    public void addSubTaskToEpic(SubTask subTask) {
        if (!Objects.equals(subTask.getStatus(), "NEW")) {
            epicList.get(subTask.getEpicId()).setStatus("IN_PROGRESS");
        }
        subTasksId++;
        subTaskList.put(subTasksId, subTask);
    }

    @Override
    public void printTasks() {

        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            Integer id = entry.getKey();
            Task task = entry.getValue();
            System.out.println("Задача " + id + ": " + task.getName() + "\nОписание: " + task.getDescription());
        }

    }

    @Override
    public void printEpicTasks() {

        for (Map.Entry<Integer, EpicTask> taskEntry : epicList.entrySet()) {
            Integer taskId = taskEntry.getKey();
            EpicTask task = taskEntry.getValue();

            System.out.println("Эпик " + taskId + ": " + task.getName() + "\nОписание: " + task.getDescription());

        }
    }

    @Override
    public void printSubTasks() {

        for (Map.Entry<Integer, SubTask> epicEntry : subTaskList.entrySet()) {
            SubTask subTask = epicEntry.getValue();
            System.out.println("\tПодзадача эпика " + subTask.getEpicId() + ": " + subTask.getName() + "\n\tОписание: " + subTask.getDescription());
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
