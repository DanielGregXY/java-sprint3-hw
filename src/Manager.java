import java.util.HashMap;
import java.util.Map;

public class Manager extends Main {
    static HashMap<Integer, Task> taskList = new HashMap<>();
    static HashMap<Integer, EpicTask> epicList = new HashMap<>();
    static HashMap<Integer, SubTask> subTaskList = new HashMap<>();
    static int id = 0;

    public static void addTask() {
        String taskName = "Переезд";
        String taskDescription = "собрать вещи";

        Task task = new Task(taskName, taskDescription, "NEW");
        id++;
        taskList.put(id, task);
    }

    public static void addEpicTask() {
        String taskName = "Епик таск";
        String taskDescription = "это точно эпик";

        EpicTask task = new EpicTask(taskName, taskDescription, "NEW");
        id++;
        epicList.put(id, task);
    }

    public static void addSubTaskToEpic(int epicId) {
        String subTaskName = "подзадача купить морковку";
        String subTaskDescription = "дойти до прилавка с морковкой";

        SubTask task = new SubTask(subTaskName, subTaskDescription, "NEW", epicId);
        id++;
        subTaskList.put(id, task);
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
            Integer taskId = entry.getKey();
            Task task = entry.getValue();
            if (taskId == id) {
                result = task;
            }
        }
        return result;
    }

    public static EpicTask getEpicById(int id) {
        EpicTask result = null;

        for (Map.Entry<Integer, EpicTask> entry : epicList.entrySet()) {
            Integer taskId = entry.getKey();
            EpicTask task = entry.getValue();
            if (taskId == id) {
                result = task;
            }
        }
        return result;
    }

    public static SubTask getSubTaskById(int id) {
        SubTask result = null;

        for (Map.Entry<Integer, SubTask> entry : subTaskList.entrySet()) {
            Integer taskId = entry.getKey();
            SubTask task = entry.getValue();
            if (taskId == id) {
                result = task;
            }
        }
        return result;
    }
}
