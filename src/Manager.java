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
        if (!Objects.equals(subTask.getStatus(), "NEW")) {
            epicList.get(subTask.getEpicId()).status = "IN_PROGRESS";
        }
        subTasksId++;
        subTaskList.put(subTasksId, subTask);
    }

    public static void printTasks() {

        for (Map.Entry<Integer, Task> entry : taskList.entrySet()) {
            Integer id = entry.getKey();
            Task task = entry.getValue();
            System.out.println("Задача " + id + ": " + task.getName() + "\nОписание: " + task.getDescription());
        }

    }

    public static void printEpicTasks() {

        for (Map.Entry<Integer, EpicTask> taskEntry : epicList.entrySet()) {
            Integer taskId = taskEntry.getKey();
            EpicTask task = taskEntry.getValue();

            System.out.println("Эпик " + taskId + ": " + task.getName() + "\nОписание: " + task.getDescription());

        }
    }

    public static void printSubTasks() {

        for (Map.Entry<Integer, SubTask> epicEntry : subTaskList.entrySet()) {
            SubTask subTask = epicEntry.getValue();
            System.out.println("\tПодзадача эпика " + subTask.getEpicId() + ": " + subTask.getName() + "\n\tОписание: " + subTask.getDescription());
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
            if (task.getId() == id) {
                result = task;
            }
        }
        return result;
    }

    public static EpicTask getEpicById(int id) {
        EpicTask result = null;

        for (Map.Entry<Integer, EpicTask> entry : epicList.entrySet()) {
            EpicTask task = entry.getValue();
            if (task.getId() == id) {
                result = task;
            }
        }
        return result;
    }

    public static SubTask getSubTaskById(int id) {
        SubTask result = null;

        for (Map.Entry<Integer, SubTask> entry : subTaskList.entrySet()) {
            SubTask task = entry.getValue();
            if (task.getId() == id) {
                result = task;
            }
        }
        return result;
    }


    public static void updateTask(Task task) {
        taskList.put(task.getId(), task);
    }

    public static void updateEpic(EpicTask epicTask) {
        epicList.put(epicTask.getId(), epicTask);
    }

    public static void updateSubtask(SubTask subTask) {
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

    public static void removeTaskById(int id) {
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

    public static void removeEpicById(int id) {
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


    public static void removeSubTaskById(int id) {
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

    public static ArrayList<SubTask> getEpicSubTasks(int epicId) {
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
