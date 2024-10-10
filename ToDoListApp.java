import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    private List<String> todoList;

    public ToDoListApp() {
        todoList = new ArrayList<>();
    }

    public void addItem(String item) {
        todoList.add(item);
        System.out.println("Added: " + item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < todoList.size()) {
            String removedItem = todoList.remove(index);
            System.out.println("Removed: " + removedItem);
        } else {
            System.out.println("Invalid index. Please try again.");
        }
    }

    public void viewItems() {
        if (todoList.isEmpty()) {
            System.out.println("To-do list is empty.");
        } else {
            System.out.println("To-do List:");
            for (int i = 0; i < todoList.size(); i++) {
                System.out.println((i + 1) + ". " + todoList.get(i));
            }
        }
    }

    public static void main(String[] args) {
        ToDoListApp app = new ToDoListApp();
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("\nTo-Do List Menu:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. View Items");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Enter the item to add: ");
                    String itemToAdd = scanner.nextLine();
                    app.addItem(itemToAdd);
                    break;
                case "2":
                    System.out.print("Enter the index of the item to remove: ");
                    int indexToRemove = Integer.parseInt(scanner.nextLine()) - 1; // Convert to 0-based index
                    app.removeItem(indexToRemove);
                    break;
                case "3":
                    app.viewItems();
                    break;
                case "4":
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!command.equals("4"));

        scanner.close();
    }
}
