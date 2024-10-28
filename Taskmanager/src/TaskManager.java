import java.io.*; // Import all classes from java.io package
import java.util.ArrayList; // Import ArrayList
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>(); // Task list
    private int nextId = 1; // For generating unique task IDs
    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.loadTasks(); // Load tasks from file at startup
        manager.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Create Task");
            System.out.println("2. Read Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createTask(scanner);
                    break;
                case 2:
                    readTasks();
                    break;
                case 3:
                    updateTask(scanner);
                    break;
                case 4:
                    deleteTask(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    saveTasks(); // Save tasks before exiting
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private void createTask(Scanner scanner) {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        Task newTask = new Task(nextId++, name, description);
        tasks.add(newTask);
        System.out.println("Task created successfully!");
    }

    private void readTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    private void updateTask(Scanner scanner) {
        System.out.print("Enter task ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Task taskToUpdate = findTaskById(id);
        if (taskToUpdate != null) {
            System.out.print("Enter new task name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new task description: ");
            String description = scanner.nextLine();
            taskToUpdate.setName(name);
            taskToUpdate.setDescription(description);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Task not found.");
        }
    }

    private void deleteTask(Scanner scanner) {
        System.out.print("Enter task ID to delete: ");
        int id = scanner.nextInt();
        Task taskToDelete = findTaskById(id);
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Task not found.");
        }
    }

    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null; // Task not found
    }

    // Load tasks from file
    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromCSV(line);
                tasks.add(task);
                nextId = Math.max(nextId, task.getId() + 1); // Update nextId based on loaded tasks
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    // Save tasks to file
    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.toCSV());
                writer.newLine();
            }
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
