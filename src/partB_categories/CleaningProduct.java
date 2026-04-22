package partB_categories;

import partA_core.InventoryManager;

public class CleaningProduct extends InventoryManager {
    private String type; // e.g., "Liquid", "Powder"

    public CleaningProduct(String id, String name, double price, int qty,
                           String supplier, String location, String type) {
        super(id, name, price, qty, "Cleaning", supplier, location);
        this.type = type;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.1;
    }

    @Override
    public boolean validateProduct() {
        return type != null && !type.isEmpty();
    }

    @Override
    public String getCategoryDescription() {
        return "Cleaning product";
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: " + type;
    }
}
