package main;

import java.util.Scanner;
import partA_core.*;
import partB_categories.*;
import partC_system.*;

public class SupermarketSystem {

    // ========== Counter Variables ==========
    private static int productCount = 1;
    private static int customerCount = 1;
    private static int orderCount = 1;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        boolean continueShopping = true;
        while (continueShopping) {
            processNewSale(sc);
            continueShopping = askContinue(sc);
        }
        
        exitSystem();
    }

    // ========== Continuation Logic ==========
    private static boolean askContinue(Scanner sc) {
        while (true) {
            System.out.print("\nProcess another sale? (yes/no): ");
            String choice = sc.nextLine().trim().toLowerCase();
            if (choice.equals("yes") || choice.equals("y")) {
                return true;
            } else if (choice.equals("no") || choice.equals("n")) {
                return false;
            } else {
                System.out.println("⚠ Please enter 'yes' or 'no'.");
            }
        }
    }

    // ========== Input Validation Helper Methods ==========
    
    private static String getValidatedString(Scanner sc, String prompt, int minLength, int maxLength) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return InputValidator.validateNonEmptyString(input, "Input");
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }

    private static double getValidatedPrice(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return InputValidator.validatePositiveDouble(input, "Price");
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }

    private static int getValidatedPositiveInteger(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return InputValidator.validatePositiveInteger(input, prompt.replaceAll(":.*", ""));
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }

    private static String getValidatedCategory(Scanner sc) {
        while (true) {
            System.out.print("Enter Category (food/electronic/beverage/clothing/cleaning/personalcare): ");
            String category = sc.nextLine().trim();
            try {
                return InputValidator.validateCategory(category);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }

    private static String getValidatedPhoneNumber(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String phone = sc.nextLine().trim();
            try {
                return InputValidator.validatePhoneNumber(phone);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }

    private static String getValidatedExpiryDate(Scanner sc) {
        while (true) {
            System.out.print("Enter Expiry Date (dd/MM/yyyy format, e.g., 25/12/2025): ");
            String date = sc.nextLine().trim();
            try {
                return InputValidator.validateExpiryDate(date);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }

    private static int getValidatedWarranty(Scanner sc) {
        while (true) {
            System.out.print("Enter Warranty (months): ");
            String input = sc.nextLine().trim();
            try {
                return InputValidator.validateInRange(input, "Warranty", 1, 120);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }

    private static String getValidatedProductId(Scanner sc) {
        while (true) {
            System.out.print("Enter Product ID: ");
            String id = sc.nextLine().trim();
            try {
                return InputValidator.validateProductId(id);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }

    // ========== Sale Processing ==========
    private static void processNewSale(Scanner sc) {
        try {
            System.out.println("\n========== 🏪 SUPERMARKET SYSTEM 🏪 ==========\n");
            
            // ========== ENTER PRODUCT ==========
            System.out.println("--- ENTER PRODUCT ---");
            String id = getValidatedProductId(sc);
            String name = getValidatedString(sc, "Enter Product Name: ", 1, 100);
            double price = getValidatedPrice(sc, "Enter Price: $");
            int stockQty = getValidatedPositiveInteger(sc, "Enter Quantity (Stock): ");
            String category = getValidatedCategory(sc);

            String supplier = getValidatedString(sc, "Enter Supplier Name: ", 1, 100);
            String location = getValidatedString(sc, "Enter Storage Location: ", 1, 100);

            InventoryManager product;
            if (category.equalsIgnoreCase("food")) {
                String expiry = getValidatedExpiryDate(sc);
                product = new FoodProduct(id, name, price, stockQty, supplier, location, expiry);
            } else if (category.equalsIgnoreCase("electronic")) {
                int warranty = getValidatedWarranty(sc);
                product = new ElectronicProduct(id, name, price, stockQty, supplier, location, warranty);
            } else {
                product = new InventoryManager(id, name, price, stockQty, category, supplier, location);
            }

            // ========== ENTER CUSTOMER ==========
            System.out.println("\n--- ENTER CUSTOMER ---");
            String customerName = getValidatedString(sc, "Enter Customer Name: ", 1, 100);
            String phone = getValidatedPhoneNumber(sc, "Enter Phone Number: ");
            Customer customer = new Customer("C" + String.format("%03d", customerCount++), customerName, phone);

            // ========== ENTER ORDER ==========
            System.out.println("\n--- ENTER ORDER ---");
            int purchaseQty = getValidatedPositiveInteger(sc, "Enter Quantity to Purchase: ");

            if (!product.checkAvailability(purchaseQty)) {
                System.out.println("\n❌ Error: Not enough stock! Available: " + product.getStockQuantity());
                return;
            }

            // ========== PROCESS SALE ==========
            product.processSale(purchaseQty);
            Order order = new Order("O" + String.format("%03d", orderCount++), product, purchaseQty);

            // ========== DISPLAY OUTPUT ==========
            System.out.println("\n========== ✅ TRANSACTION OUTPUT ==========");
            System.out.println("\n📦 PRODUCT:");
            System.out.println(product);

            System.out.println("\n👤 CUSTOMER:");
            System.out.println(customer);

            System.out.println("\n📋 ORDER:");
            System.out.println(order);

            // ========== END ==========
            System.out.println("\n✅ Transaction completed successfully!\n");
            
        } catch (Exception e) {
            System.out.println("\n❌ Error processing sale: " + e.getMessage());
        }
    }
    
    private static void exitSystem() {
        System.out.println("\n========================================");
        System.out.println("   Thank you for using SUPERMARKET SYSTEM");
        System.out.println("   Goodbye! 👋");
        System.out.println("========================================\n");
    }
}