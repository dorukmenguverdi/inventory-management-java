import java.util.Scanner;

public class InventoryApp {
    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("----- INVENTORY MENU -----");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Stock");
            System.out.println("4. Find Product");
            System.out.println("5. List All Products");
            System.out.println("6. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter product ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter product quantity: ");
                    int quantity = scanner.nextInt();

                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    try {
                        Product newProduct = new Product(id, name, quantity, price);
                        boolean added = inventory.addProduct(newProduct);
                        if (added) {
                            System.out.println("[INFO] Product added: " + id);
                        }
                        else {
                            System.out.println("[WARN] Product with same ID already exists: " + id);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("[ERROR]" + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter the product ID to remove: ");
                    String removeId = scanner.nextLine();

                    if (inventory.removeProduct(removeId)) {
                        System.out.println("[INFO] Product removed: " + removeId);
                    }
                    else {
                        System.out.println("[INFO] No product was removed.");
                    }
                    break;

                case 3:
                    System.out.print("Enter the product ID to update: ");
                    String updateId = scanner.nextLine();

                    System.out.print("Enter the new quantity: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine();

                    try{
                        boolean updated = inventory.updateStock(updateId, newQuantity);
                        if (updated) {
                            System.out.println("[INFO] Stock updated for product: " + updateId);
                        }
                        else {
                            System.out.println("[INFO] No product found to update.");
                        }
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("[ERROR]" + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter the product ID to search: ");
                    String searchId = scanner.nextLine();

                    Product foundProduct = inventory.findProduct(searchId);
                    if (foundProduct != null) {
                        System.out.println("----- PRODUCT FOUND -----");
                        System.out.println(foundProduct);
                    }
                    else {
                        System.out.println("[INFO] No product found with id: " + searchId);
                    }
                    break;

                case 5:
                    if (inventory.getAllProducts().isEmpty()) {
                        System.out.println("[INFO] Inventory is empty.");
                    }
                    else {
                        System.out.println("----- INVENTORY LIST -----");
                        for (Product product : inventory.getAllProducts()) {
                            System.out.println(product);
                        }
                    }
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting Inventory System. Goodbye!");
                    break;

                default:
                    System.out.println("[WARN] Invalid choice. Please try again");
            }


        }
        scanner.close();

    }
}
