import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {
    static Scanner scanner = new Scanner(System.in);

    static void displayTasks(ArrayList<Task> allTasks) {
        System.out.println("\nAll your tasks: \n");

        if (allTasks.isEmpty()) {
            System.out.println("You didn't add task yet!\n");
            return;
        }

        for (int i = 0; i < allTasks.size(); i++) {
            System.out.println((i + 1) + ") " + allTasks.get(i).getTask());
        }
    }
    static void editTask(ArrayList<Task> allTasks) {
        displayTasks(allTasks);
        System.out.print("\nWhat task status do you want to change: ");

        int choice = scanner.nextInt() - 1;
        boolean isChoiceValid = false;
        boolean newDesc = false;

        do {
            try {
                System.out.println("What is your new status (true/false). For this task *" + allTasks.get(choice) + "*");
                newDesc = scanner.nextBoolean();
                isChoiceValid = true;
            } catch (Exception e) {
                System.out.println("Please provide valid value (true/false)");
                scanner.nextLine();
            }
        } while (!isChoiceValid);

        allTasks.get(choice).changeDesc(newDesc);

        FileManager.updateFile(allTasks);
    }
    static void delTask(ArrayList<Task> allTasks) {
        displayTasks(allTasks);

        boolean isChoiceValid = false;

        do {
            System.out.print("Please choose what task do you want to delete: ");
            try {
                int choice = scanner.nextInt() - 1;
                allTasks.remove(choice);
                isChoiceValid = true;
            } catch (Exception e) {
                System.out.println("Please choose valid number");
                scanner.nextLine();
            }
        } while (!isChoiceValid);

        FileManager.updateFile(allTasks);
    }
    static void addTask(ArrayList<Task> allTasks) {

        boolean isDescValid = false;
        boolean isPrioValid = false;

        // Default values for new task
        boolean description = false;
        char priority = 'L';

        System.out.print("Enter your task name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter your task description (is your task done).");

        do {
            System.out.print("\nValue must be (true/false): ");
            try {
                description = scanner.nextBoolean();
                isDescValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please choose from (true/false)");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Something went wrong");
                scanner.nextLine();
            }
        } while (!isDescValid);


        // TODO = Trzeba zrobic poprawny komunikat a nie tylko "Something went wrong".
        String ANSI_RESET = "\u001B[0m";

        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";

        do {
            System.out.print("Enter your task priority (" + ANSI_GREEN + "L" + ANSI_RESET + ", " + ANSI_YELLOW + "M" + ANSI_RESET + ", " + ANSI_RED + "H" + ANSI_RESET + "):");
            try {
                priority = scanner.next().toUpperCase().charAt(0);
                if (priority == 'L' || priority == 'M' || priority == 'H') {
                    isPrioValid = true;
                } else {
                    Exception e = new Exception ("Enter a valid value");
                    throw e;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong!");
            }
        } while (!isPrioValid);


        LocalDateTime dateNow = LocalDateTime.now();

        Task newTask = new Task(name, description, priority, dateNow);
        allTasks.add(newTask);

        FileManager.addTask(newTask);
    }
    static void sortTask(ArrayList<Task> allTasks) {
        boolean isChoiceValid = false;
        int choice = 0;

        do {
            System.out.println("\nSort by:");
            System.out.println("1. Date ascending");
            System.out.println("2. Date descending");
            System.out.println("3. Priority (L -> H)");
            System.out.println("4. Priority (H -> L)");
            try {
                System.out.print("What do you want to do: ");
                choice = scanner.nextInt();
                isChoiceValid = true;
            } catch (Exception e) {
                System.out.println("Please choose valid option (1-4)");
                scanner.nextLine();
            }
            switch (choice) {
                // Collections.sort(allTasks, (t1, t2) -> t1.getDate().compareTo(t2.getDate()));
                case 1 -> allTasks.sort(Comparator.comparing(Task::getDate));
                case 2 -> allTasks.sort(Comparator.comparing(Task::getDate).reversed());
                case 3 -> allTasks.sort(Comparator.comparing(Task::getPriority));
                case 4 -> allTasks.sort(Comparator.comparing(Task::getPriority).reversed());
                default -> {
                    System.out.println("Please choose valid option (1-4)\n");
                    isChoiceValid = false;
                }
            }
        } while (!isChoiceValid);


        displayTasks(allTasks);
        scanner.nextLine();

    }
    static void filterTask(ArrayList<Task> allTasks) {
        boolean isChoiceValid = false;
        int choice = 0;


        do {
            System.out.println("\nFilter:");
            System.out.println("1. Only completed (isDone == true)");
            System.out.println("2. Only not completed (isDone == false)");
            System.out.println("3. Priority L");
            System.out.println("4. Priority M");
            System.out.println("5. Priority H");

            System.out.print("What do you want to do: ");
            try {
                choice = scanner.nextInt();
                isChoiceValid = true;
            } catch (Exception e) {
                System.out.print("Please choose valid option (1-5)");
                scanner.nextLine();
            }

        } while (!isChoiceValid);

        switch (choice) {
            case 1 -> { for (Task t : allTasks) {
                if (t.getDescription()) {
                    System.out.println(t.getTask());
                }}}
            case 2 -> { for (Task t : allTasks) {
                if (!t.getDescription()) {
                    System.out.println(t.getTask());
                }}}
            case 3 -> { for (Task t : allTasks) {
                if (t.getPriority() == 1) {
                    System.out.println(t.getTask());
                }}}
            case 4 -> { for (Task t : allTasks) {
                if (t.getPriority() == 2) {
                    System.out.println(t.getTask());
                }}}
            case 5 -> { for (Task t : allTasks) {
                if (t.getPriority() == 3) {
                    System.out.println(t.getTask());
                }}}
            default -> System.out.println("Please choose valid option (1-5)\n");
        }
    }
}
