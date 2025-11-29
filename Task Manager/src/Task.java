import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private String name;
    private boolean desc;
    private char priority;
    private LocalDateTime date;


    Task(String name, boolean desc, char priority, LocalDateTime date) {
        this.name = name;
        this.desc = desc;
        this.priority = priority;
        this.date = date;
    }

    public String getTask() {
        return "Task: " + this.name + " | " + "Done: " + this.desc + " | " + "Priority: " + getPriorityColor() + " | " + "Date: " + getFormattedDate(this.date);
    }

    @Override
    public String toString() {
        return this.name + ";" + this.desc + ";" + this.priority + ";" + getFormattedDate(this.date);
    }

    void changeDesc(boolean desc) {
        this.desc = desc;
    }

    LocalDateTime getDate() {
        return this.date;
    }

    boolean getDescription() {
        return this.desc;
    }

    int getPriority() {
        switch (this.priority) {
            case 'L' -> {
                return 1;
            }
            case 'M' -> {
                return 2;
            }
            case 'H' -> {
                return 3;
            }
            default -> {
                return 0;
            }
        }
    }

    String getPriorityColor() {
        String ANSI_RESET = "\u001B[0m";

        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RED = "\u001B[31m";

        switch (getPriority()) {
            case 1 -> {
                return ANSI_GREEN + this.priority + ANSI_RESET;
            }
            case 2 -> {
                return ANSI_YELLOW + this.priority + ANSI_RESET;
            }
            case 3 -> {
                return ANSI_RED + this.priority + ANSI_RESET;
            }
            default -> {
                return "";
            }
        }
    }

    String getFormattedDate(LocalDateTime date) {
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy/HH:mm:ss");
        return date.format(formattedDate);
    }
}
