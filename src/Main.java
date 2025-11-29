import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Welcome to Task Manager program in Java!");

        boolean isRunning = true;

        ArrayList<Task> allTasks = new ArrayList<>();

        for (Task t : FileManager.updateAllTasks()) {
            allTasks.add(t);
        }

        do {
            System.out.println("\n1. See all tasks");
            System.out.println("2. Add new task");
            System.out.println("3. Change task status (IsDone)");
            System.out.println("4. Delete task");
            System.out.println("5. Sort tasks");
            System.out.println("6. Filter tasks");
            System.out.println("7. Exit the program");
            System.out.print("Please choose what action do you want to do: ");

            int choice = 0;

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("\nInvalid choice! Please choose again");
                scanner.nextLine();
            }

            switch (choice) {
                case 0 -> {
                    continue;
                }
                case 1 -> TaskManager.displayTasks(allTasks);
                case 2 -> TaskManager.addTask(allTasks);
                case 3 -> TaskManager.editTask(allTasks);
                case 4 -> TaskManager.delTask(allTasks);
                case 5 -> TaskManager.sortTask(allTasks);
                case 6 -> TaskManager.filterTask(allTasks);
                case 7 -> isRunning = false;
                default -> System.out.println("Please enter valid value from (1-5)");
            }
        } while (isRunning);


        System.out.println("\nHave a nice day!");

    }
}