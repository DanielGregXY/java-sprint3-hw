import java.util.Scanner;

public class Main {
    static int epicId = 0;
    static int subTasksId = 0;
    static int tasksId = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        printMenu();

        Manager.addEpicTask(new EpicTask("Сьездить в отпуск", "На бали первая остановка", epicId + 1));
        Manager.addSubTaskToEpic(new SubTask("Купить снаряжение", "Для дайвинга", "NEW", 1, subTasksId + 1));
        Manager.addSubTaskToEpic(new SubTask("Сьесть омара", "в ресторане", "NEW", 1, subTasksId + 1));

        Manager.addEpicTask(new EpicTask("Сходить в магазин", "купить яиц", epicId + 1));
        Manager.addSubTaskToEpic(new SubTask("Заглянуть на базар", "Купить свинины", "NEW", 2, subTasksId + 1));

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

// //       noinspection InfiniteLoopStatement
//        while (true) {
//            switch (scanner.nextInt()) {
//                case 0 -> Manager.printTasks();
//                case 1 ->
//                        Manager.addTask(new Task("Сходить в зал", "купить абонемент и сходить в зал", "NEW", tasksId + 1));
//                case 2 -> Manager.addEpicTask(new EpicTask("Это эпик", "у него могут быть подзадачи", epicId + 1));
//                case 3 ->
//                        Manager.addSubTaskToEpic(new SubTask("Это Подзадача", "их можно удалять", "NEW", 1, subTasksId + 1));
//                case 4 -> Manager.printEpicTasks();
//                case 5 -> Manager.printSubTasks();
//                case 6 -> Manager.removeTasks();
//                case 7 -> Manager.removeEpicTasks();
//                case 8 -> Manager.removeSubTasks();
//                case 9 ->
//                        Manager.updateTask(new Task("Сходить в зал и покупаться", "купить абонемент и сходить в зал", "NEW", 1));
//                case 10 -> Manager.removeTaskById(1);
//                case 11 -> Manager.getEpicSubTasks(1);
//                case 12 -> Manager.updateSubtask(new SubTask("Это Подзадача", "их можно удалять", "DONE", 1, 2));
//                default -> System.out.println("Такой команды нету");
//            }
//            printMenu();
//        }

//        System.out.println("Задача " + task.id + ": " + task.name + "\nОписание: " + task.description);
    }


//    public static void printMenu() {
//        System.out.println("\n0 - Показать задачи");
//        System.out.println("1 - Добавить задачу");
//        System.out.println("2 - Добавить большую задачу");
//        System.out.println("3 - Добавить подзадачу к эпику по айди");
//        System.out.println("4 - Показать Эпики");
//        System.out.println("5 - Показать подзадачи");
//        System.out.println("6 - Удалить все задачи");
//        System.out.println("7 - Удалить все эпики");
//        System.out.println("8 - Удалить все подзадачи");
//    }
}
