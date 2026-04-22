package partC_system;

import partA_core.InventoryManager;
import partB_categories.FoodProduct;
import partB_categories.ElectronicProduct;
import partB_categories.BeverageProduct;
import partB_categories.CleaningProduct;
import partB_categories.ClothingProduct;
import partB_categories.PersonalCareProduct;

public class ProductFactory {

    public static InventoryManager createProduct(String category, String id, String name,
                                                 double price, int qty, String supplier, String location) {
        // Validate product ID - must not be negative
        id = InputValidator.validateProductId(id);
        
        // Validate category
        category = InputValidator.validateCategory(category);

        switch (category.toLowerCase()) {
            case "food":
                return new FoodProduct(id, name, price, qty, supplier, location, "2026-12-31");
            case "electronic":
                return new ElectronicProduct(id, name, price, qty, supplier, location, 12);
            case "beverage":
                return new BeverageProduct(id, name, price, qty, supplier, location, 1.0);
            case "cleaning":
                return new CleaningProduct(id, name, price, qty, supplier, location, "General");
            case "clothing":
                return new ClothingProduct(id, name, price, qty, supplier, location, "M");
            case "personal care":
                return new PersonalCareProduct(id, name, price, qty, supplier, location, "Generic");
            default:
                return new InventoryManager(id, name, price, qty, category, supplier, location);
        }
    }
}