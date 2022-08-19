public class Main {

    public static void main(String[] args) {

        Manager.addEpicTask(new EpicTask("Сьездить в отпуск", "На бали первая остановка", Manager.epicId + 1));
        Manager.addSubTaskToEpic(new SubTask("Купить снаряжение", "Для дайвинга", "NEW", 1, Manager.subTasksId + 1));
        Manager.addSubTaskToEpic(new SubTask("Сьесть омара", "в ресторане", "NEW", 1, Manager.subTasksId + 1));

        Manager.addEpicTask(new EpicTask("Сходить в магазин", "купить яиц", Manager.epicId + 1));
        Manager.addSubTaskToEpic(new SubTask("Заглянуть на базар", "Купить свинины", "NEW", 2, Manager.subTasksId + 1));

        Manager.printEpicTasks();
        Manager.printTasks();
        Manager.printSubTasks();

        Manager.updateSubtask(new SubTask("Это Подзадача", "их можно удалять", "DONE", 1, 1));
        Manager.updateSubtask(new SubTask("Это Подзадача", "их можно удалять", "DONE", 1, 2));

        Manager.removeSubTaskById(1);
        Manager.removeEpicById(2);

        Manager.printEpicTasks();
        Manager.printTasks();
        Manager.printSubTasks();

    }
}