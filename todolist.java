import java.util.ArrayList;
import java.util.Scanner;

public class todolist {

    private static ArrayList<String> todoList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    deleteItem();
                    break;
                case 3:
                    updateItem();
                    break;
                case 4:
                    displayItems();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }


    private static void displayMenu() {
        System.out.println("\nTo-Do List Menu:");
        System.out.println("1. Add an item");
        System.out.println("2. Delete an item");
        System.out.println("3. Update an item");
        System.out.println("4. Display all items");
        System.out.println("5. Exit");
    }


    private static void addItem() {
        System.out.print("Enter the item to add: ");
        String item = scanner.nextLine();
        todoList.add(item);
        System.out.println("'" + item + "' has been added to the list.");
    }


    private static void deleteItem() {
        displayItems();
        try {
            System.out.print("Enter the index of the item to delete: ");
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (index > 0 && index <= todoList.size()) {
                String removedItem = todoList.remove(index - 1);
                System.out.println("'" + removedItem + "' has been deleted from the list.");
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Clear the invalid input
        }
    }


    private static void updateItem() {
        displayItems();
        try {
            System.out.print("Enter the index of the item to update: ");
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (index > 0 && index <= todoList.size()) {
                System.out.print("Enter the new value: ");
                String newItem = scanner.nextLine();
                todoList.set(index - 1, newItem);
                System.out.println("Item at index " + index + " has been updated to '" + newItem + "'.");
            } else {
                System.out.println("Invalid index. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Clear the invalid input
        }
    }


    private static void displayItems() {
        if (todoList.isEmpty()) {
            System.out.println("The to-do list is empty.");
        } else {
            System.out.println("\nTo-Do List:");
            for (int i = 0; i < todoList.size(); i++) {
                System.out.println((i + 1) + ": " + todoList.get(i));
            }
        }
    }
}
