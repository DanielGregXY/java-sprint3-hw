package manager.task;

import constructor.EpicTask;
import constructor.SubTask;
import constructor.Task;
import constructor.status.Status;
import manager.Managers;
import manager.history.HistoryManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//=================================================================================\\

public class FileBackedTasksManager extends InMemoryTaskManager {
    public static void main(String[] args) throws IOException {
        TaskManager task = Managers.getDefaultTask();

        InMemoryTaskManager taskManager = (InMemoryTaskManager) task;
//=================================================================================\\
        taskManager.addTask(new Task("Купить продукты", "сходить в магазин", Status.NEW, 1));
        taskManager.addTask(new Task("Съездить в барбер", "завести машину", Status.IN_PROGRESS, 2));
        taskManager.addEpicTask(new EpicTask("Сходить в душ", "срочно", 3));
        taskManager.addSubTaskToEpic(new SubTask("Написать спринт", "Очень тяжело", Status.NEW, 3, 4));
        taskManager.addSubTaskToEpic(new SubTask("Выкопать картошку", "Взять лопату", Status.NEW, 3, 5));
        taskManager.addSubTaskToEpic(new SubTask("Купить хомяка", "Выбрать хомяка", Status.NEW, 3, 6));
        taskManager.addEpicTask(new EpicTask("Погладить собаку", "Сказать - Хороший мальчик", 7));
        taskManager.addSubTaskToEpic(new SubTask("Сходить в зоопарк", "купить билеты", Status.NEW, 7, 8));
        taskManager.addSubTaskToEpic(new SubTask("Сходить в зоопарк", "купить билеты", Status.NEW, 7, 9));
        taskManager.getTaskById(1);
        taskManager.getTaskById(1);
        taskManager.getTaskById(2);
        taskManager.getEpicById(3);
        taskManager.getEpicById(3);
        taskManager.getSubTaskById(4);
        taskManager.getSubTaskById(5);
        taskManager.getSubTaskById(6);
        taskManager.getEpicById(7);
        taskManager.getSubTaskById(8);
        taskManager.getEpicById(3);
        taskManager.removeTaskById(1);
        taskManager.addTask(new Task("Купить таблетки", "Выбрать таблетки", Status.NEW, 9));
        taskManager.removeEpicById(7);
        taskManager.removeEpicById(3);
        taskManager.removeSubTaskById(4);
        taskManager.removeSubTaskById(5);
        taskManager.removeSubTaskById(6);
        System.out.println(taskManager);
    }

    //=================================================================================\\

    static File file;

    public FileBackedTasksManager(File file) {
        FileBackedTasksManager.file = file;
    }
    //=================================================================================\\
    public enum TaskEnum {
        EPICTASK,
        SUBTASK,
        TASK
    }

    private final static String PATH = "C:\\Users\\Daniel Greg\\Desktop\\task.csv";
    //=================================================================================\\
    @Override
    public int addTask(Task task) throws IOException {
        int id = super.addTask(task);
        save();
        return id;
    }
    @Override
    public int add(EpicTask epic) throws IOException {
        int id = super.addEpicTask(epic);
        save();
        return id;
    }

    @Override
    public int add(SubTask subtask) throws IOException {
        int id = super.addSubTaskToEpic(subtask);
        save();
        return id;
    }

    @Override
    public void updateTask(Task task) throws IOException {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(EpicTask epicTask) throws IOException {
        super.updateEpic(epicTask);
        save();

    }

    @Override
    public void updateSubtask(SubTask subtask) throws IOException {
        super.updateSubtask(subtask);
        save();
    }


    @Override
    public Task getTaskById(int id) throws IOException {
        Task task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public EpicTask getEpicById(int id) throws IOException {
        EpicTask epicTask = super.getEpicById(id);
        save();
        return epicTask;
    }

    @Override
    public SubTask getSubTaskById(int id) throws IOException {
        SubTask subtask = super.getSubTaskById(id);
        save();
        return subtask;
    }

    @Override
    public void removeEpicById(int id) throws IOException {
        super.removeEpicById(id);
        save();
    }

    @Override
    public void removeSubTaskById(int id) throws IOException {
        super.removeSubTaskById(id);
        save();
    }

    @Override
    public void removeTaskById(int id) throws IOException {
        super.removeTaskById(id);
        save();

    }

    //=================================================================================\\
    private static String taskToString(Task task) {
        return String.join(",",
                String.valueOf(task.getId()),
                task.getClass().getSimpleName().toUpperCase(),
                task.getName(),
                task.getStatus(),
                task.getDescription(),
                String.valueOf(task instanceof SubTask ? ((SubTask) task).getEpicId() : ""));
    }
    //=================================================================================\\
    private static String allTasksToString() {

        StringBuilder builder = new StringBuilder();
        List<Task> allTasks = new ArrayList<>();

        allTasks.addAll(taskList.values());

        allTasks.addAll(epicList.values());

        allTasks.addAll(subTaskList.values());
        for (Task task : allTasks) {
            builder.append(taskToString(task)).append('\n');
        }

        return builder.toString();
    }

    //=================================================================================\\
    public static void save() throws IOException {
        try {
            Path path = Path.of(PATH);
            final String head = "id,type,name,status,description,epic" + System.lineSeparator();

            String data = head +
                    allTasksToString() + System.lineSeparator() +
                    HistoryManager.historyToString(historyManager);
            Files.writeString(path, data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //=================================================================================\\

    public void tasksFromFile() {

        Map<Integer, Task> allTasks = new HashMap<>();

        try {
            String[] lines = Files.readString(Path.of(PATH), StandardCharsets.ISO_8859_1).split(System.lineSeparator());

            if (lines.length < 2) return;

            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] lineContents = line.split(",");

                if (lineContents.length >= 5) {

                    if (lineContents[1].equals("TASK")) {

                        int id = Integer.parseInt(lineContents[0]);

                        String name = lineContents[2];

                        Status status = Enum.valueOf(Status.class, lineContents[3]);
                        String description = lineContents[4];
                        taskList.put(id, new Task(name, description, status, id));
                        if (getTaskIdc() <= id) setTaskIdc(++id);
                    }

                    if (lineContents[1].equals("EPICTASK")) {

                        int id = Integer.parseInt(lineContents[0]);

                        String name = lineContents[2];

                        Status status = Enum.valueOf(Status.class, lineContents[3]);
                        String description = lineContents[4];
                        epicList.put(id, new EpicTask(name, description, id));
                        if (getTaskIdc() <= id) setTaskIdc(++id);
                    }

                    if (lineContents[1].equals("SUBTASK")) {

                        int id = Integer.parseInt(lineContents[0]);

                        String name = lineContents[2];

                        Status status = Enum.valueOf(Status.class, lineContents[3]);
                        String description = lineContents[4];
                        int epicId = Integer.parseInt(lineContents[5]);

                        subTaskList.put(id, new SubTask(name, description, status, epicId, id));
                    }
                }
            }

            allTasks.putAll(taskList);

            allTasks.putAll(epicList);

            allTasks.putAll(subTaskList);


            if (lines[lines.length - 2].isBlank() && lines.length >= 4) {


                String line = lines[lines.length - 1];
                String[] historyLine = line.split(",");


                for (String s : historyLine) {
                    historyManager.setHistory(allTasks.get(Integer.parseInt(s)));
                }
            }

        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

    }

}
