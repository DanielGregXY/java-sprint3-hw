import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Manager extends Main {
    static HashMap<Integer, Task> taskList = new HashMap<>();
    static HashMap<Integer, EpicTask> epicList = new HashMap<>();
    static HashMap<Integer, SubTask> subTaskList = new HashMap<>();

    public static void addTask(Task task) {
        tasksId++;
        taskList.put(tasksId, task);
    }

    public static void addEpicTask(EpicTask epicTask) {
        epicId++;
        epicList.put(epicId, epicTask);
    }

    public static void addSubTaskToEpic(SubTask subTask) {
        if (!Objects.equals(subTask.status, "NEW")) {
            epicList.get(subTask.epicId).status = "IN_PROGRESS";
        }
        subTasksId++;
        subTaskList.put(subTasksId, subTask);
    }

    public static void printTasks() {

        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            Integer id = entry.getKey();
            Task task = entry.getValue();
            System.out.println("Задача " + id + ": " + task.name + "\nОписание: " + task.description);
        }

    }

    public static void printEpicTasks() {

        for (Map.Entry<Integer, EpicTask> taskEntry : epicList.entrySet()) {
            Integer taskId = taskEntry.getKey();
            EpicTask task = taskEntry.getValue();

            System.out.println("Эпик " + taskId + ": " + task.name + "\nОписание: " + task.description);

        }
    }

    public static void printSubTasks() {

        for (Map.Entry<Integer, SubTask> epicEntry : subTaskList.entrySet()) {
            SubTask subTask = epicEntry.getValue();
            System.out.println("\tПодзадача эпика " + subTask.epicId + ": " + subTask.name + "\n\tОписание: " + subTask.description);
        }
    }


    public static void removeTasks() {
        taskList.clear();
    }

    public static void removeEpicTasks() {
        epicList.clear();
    }

    public static void removeSubTasks() {
        subTaskList.clear();
    }

    public static Task getTaskById(int id) {
        Task result = null;

        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            Task task = entry.getValue();
            if (task.id == id) {
                result = task;
            }
        }
        return result;
    }

    public static EpicTask getEpicById(int id) {
        EpicTask result = null;

        for (Map.Entry<Integer, EpicTask> entry : epicList.entrySet()) {
            EpicTask task = entry.getValue();
            if (task.id == id) {
                result = task;
            }
        }
        return result;
    }

    public static SubTask getSubTaskById(int id) {
        SubTask result = null;

        for (Map.Entry<Integer, SubTask> entry : subTaskList.entrySet()) {
            SubTask task = entry.getValue();
            if (task.id == id) {
                result = task;
            }
        }
        return result;
    }


    public static void updateTask(Task task) {
        taskList.put(task.id, task);
    }

    public static void updateEpic(EpicTask epicTask) {
        epicList.put(epicTask.id, epicTask);
    }

    public static void updateSubtask(SubTask subTask) {
        subTaskList.put(subTask.id, subTask);

        ArrayList<SubTask> epicSubTask = getEpicSubTasks(subTask.epicId);

        int doneTask = 0;

        for (SubTask task : epicSubTask) {
            if (Objects.equals(task.status, "DONE")) {
                doneTask++;
            }
        }

        if (doneTask == epicSubTask.size()) {
            epicList.get(subTask.epicId).status = "DONE";
        }

    }

    public static void removeTaskById(int id) {
        int removeTaskId = 0;

        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            int idInMap = entry.getKey();
            Task task = entry.getValue();
            if (task.id == id) {
                removeTaskId = idInMap;
            }
        }

        taskList.remove(removeTaskId);
    }

    public static void removeEpicById(int id) {
        int removeTaskId = 0;

        for (Map.Entry<Integer, EpicTask> entry : epicList.entrySet()) {
            int idInMap = entry.getKey();
            EpicTask task = entry.getValue();
            if (task.id == id) {
                removeTaskId = idInMap;
            }
        }

        epicList.remove(removeTaskId);
    }


    public static void removeSubTaskById(int id) {
        int removeTaskId = 0;

        for (Map.Entry<Integer, SubTask> entry : subTaskList.entrySet()) {
            int idInMap = entry.getKey();
            SubTask task = entry.getValue();
            if (task.id == id) {
                removeTaskId = idInMap;
            }
        }

        subTaskList.remove(removeTaskId);
    }

    public static ArrayList<SubTask> getEpicSubTasks(int epicId) {
        ArrayList<SubTask> EpicSubTasks = new ArrayList<>();

        for (Map.Entry<Integer, SubTask> entry : subTaskList.entrySet()) {
            int idInMap = entry.getKey();
            SubTask task = entry.getValue();
            if (task.epicId == epicId) {
                EpicSubTasks.add(task);
            }
        }

        return EpicSubTasks;
    }

}
