import java.util.HashMap;
import java.util.Map;

public class Manager extends Main {
    static HashMap<Integer, Task> taskList = new HashMap<>();
    static HashMap<Integer, EpicTask> epicList = new HashMap<>();
    static HashMap<Integer, SubTask> subTaskList = new HashMap<>();

    public static void addTask(Task task) {
        id++;
        taskList.put(id, task);
    }

    public static void addEpicTask(EpicTask epicTask) {
        id++;
        epicList.put(id, epicTask);
    }

    public static void addSubTaskToEpic(SubTask subTask) {
        id++;
        subTaskList.put(id, subTask);
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

            System.out.println("Задача " + taskId + ": " + task.name + "\nОписание: " + task.description);

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
        taskList.put(epicTask.id, epicTask);
    }

    public static void updateSubtask(SubTask subTask) {
        taskList.put(subTask.id, subTask);
    }
}
