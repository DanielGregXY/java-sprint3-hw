import constructor.EpicTask;
import constructor.SubTask;
import constructor.status.Status;
import manager.Managers;
import manager.history.HistoryManager;
import manager.task.InMemoryTaskManager;
import manager.task.TaskManager;

public class Main {

    public static void main(String[] args) {

        TaskManager task = Managers.getDefaultTask();
        HistoryManager history = Managers.getDefaultHistory();
        InMemoryTaskManager taskManager = (InMemoryTaskManager) task;

        taskManager.addEpicTask(new EpicTask("Сьездить в отпуск", "На бали первая остановка", taskManager.epicId + 1));
        taskManager.addSubTaskToEpic(new SubTask("Купить снаряжение", "Для дайвинга", Status.NEW, 1, taskManager.subTasksId + 1));
        taskManager.addSubTaskToEpic(new SubTask("Сьесть омара", "в ресторане", Status.NEW, 1, taskManager.subTasksId + 1));

        taskManager.addEpicTask(new EpicTask("Сходить в магазин", "купить яиц", taskManager.epicId + 1));
        taskManager.addSubTaskToEpic(new SubTask("Заглянуть на базар", "Купить свинины", Status.NEW, 2, taskManager.subTasksId + 1));

        taskManager.printEpicTasks();
        taskManager.printTasks();
        taskManager.printSubTasks();

        taskManager.updateSubtask(new SubTask("Это Подзадача", "их можно удалять", Status.DONE, 1, 1));
        taskManager.updateSubtask(new SubTask("Это Подзадача", "их можно удалять", Status.DONE, 1, 2));
        history.getHistory();

        taskManager.removeSubTaskById(1);
        taskManager.removeEpicById(2);

        taskManager.printEpicTasks();
        taskManager.printTasks();
        taskManager.printSubTasks();

        taskManager.getEpicById(1);
        history.getHistory();
        taskManager.getSubTaskById(2);

        history.getHistory();

    }
}