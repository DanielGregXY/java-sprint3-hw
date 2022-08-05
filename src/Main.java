import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Task> removedTasks = new ArrayList<>();
        printMenu();

        //noinspection InfiniteLoopStatement
        while (true) {
            switch (scanner.nextInt()) {
                case 0 -> printAllTasks(taskList);
                case 1 -> addTask(taskList);
                case 2 -> removeTaskByName(taskList, removedTasks);
                case 3 -> printDeletedTasks(removedTasks);
                default -> System.out.println("Такой команды нету");
            }
            printMenu();
        }

//        System.out.println("Задача " + task.id + ": " + task.name + "\nОписание: " + task.description);
    }


    public static void printMenu() {
        System.out.println("\n0 - Показать все задачи");
        System.out.println("1 - Добавить задачу");
        System.out.println("2 - Удалить задачу по названию");
        System.out.println("3 - Показать список удаленных задач");
    }

    public static void addTask(ArrayList<Task> taskList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название");
        String taskName = scanner.nextLine();

        System.out.println("Введите описание");
        String taskDescription = scanner.nextLine();

        Task task = new Task(taskName, taskDescription, "NEW", taskList.size() + 1);
        taskList.add(task);
    }

    public static void printAllTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("Список задач пуст");
        }

        for (Task task : taskList) {
            System.out.println("Задача " + task.id + ": " + task.name + "\nОписание: " + task.description);
        }
    }

    public static void printDeletedTasks(ArrayList<Task> removedTasks) {

        for (Task task : removedTasks) {
            System.out.println("Удаленные задачи: ");
            System.out.println("Задача " + task.id + ": " + task.name + "\nОписание: " + task.description);
        }
    }

    public static void removeTaskByName(ArrayList<Task> taskList, ArrayList<Task> removedTasks) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название задачи");
        String taskName = scanner.nextLine();

        for (Task task : taskList) {
            if (task.name.equals(taskName)) {
                taskList.remove(task);
                removedTasks.add(task);
            }
        }
    }
}