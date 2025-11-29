import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileManager {

    static String loopAllTasks(ArrayList<Task> allTasks) {
        String allTasksString = "";
        for (int i = 0; i < allTasks.toArray().length; i++) {
            allTasksString += allTasks.get(i).toString() + System.lineSeparator();
        }
        return allTasksString;
    }

    static void updateFile(ArrayList<Task> allTasks) {
        try (FileWriter writer = new FileWriter("allTasks.txt")) {
            writer.write(loopAllTasks(allTasks));
        } catch (IOException e) {
            System.out.println("File could not been written");
        }
    }

    static ArrayList<Task> updateAllTasks() {
        String filePath = "./allTasks.txt";

        ArrayList<Task> allTasksFromFile = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {

                int first1 = line.indexOf(';');
                int first2 = line.indexOf(';', first1 + 1);
                int first3 = line.indexOf(';', first2 + 1);

                String dateLine = line.substring(first3 + 1);
                char priority = line.charAt(first2 + 1);
                boolean desc = Boolean.parseBoolean(line.substring(first1 + 1, first2));
                String name = line.substring(0, first1);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy/HH:mm:ss");
                LocalDateTime date = LocalDateTime.parse(dateLine, formatter);

                Task task = new Task(name, desc, priority, date);

                allTasksFromFile.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("You didn't add task yet!\n");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return allTasksFromFile;
    }

    static void addTask(Task newTask) {
        try (FileWriter writer = new FileWriter("allTasks.txt", true)) {
            writer.write("\n" + newTask);
        } catch (IOException e) {
            System.out.println("Task you trying add is invalid");
        }
    }
}