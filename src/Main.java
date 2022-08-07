import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();

        //noinspection InfiniteLoopStatement
        while (true) {
            switch (scanner.nextInt()) {
                case 0 -> Manager.printTasks();
                case 1 -> Manager.addTask();
                case 2 -> Manager.addEpicTask();
                case 3 -> Manager.addSubTaskToEpic(1);
                case 4 -> Manager.printEpicTasks();
                case 5 -> Manager.printSubTasks();
                case 6 -> Manager.removeTasks();
                case 7 -> Manager.removeEpicTasks();
                case 8 -> Manager.removeSubTasks();
                default -> System.out.println("Такой команды нету");
            }
            printMenu();
        }

//        System.out.println("Задача " + task.id + ": " + task.name + "\nОписание: " + task.description);
    }


    public static void printMenu() {
        System.out.println("\n0 - Показать задачи");
        System.out.println("1 - Добавить задачу");
        System.out.println("2 - Добавить большую задачу");
        System.out.println("3 - Добавить подзадачу к эпику по айди");
        System.out.println("4 - Показать Эпики");
        System.out.println("5 - Показать подзадачи");
        System.out.println("6 - Удалить все задачи");
        System.out.println("7 - Удалить все эпики");
        System.out.println("8 - Удалить все подзадачи");
    }
}
