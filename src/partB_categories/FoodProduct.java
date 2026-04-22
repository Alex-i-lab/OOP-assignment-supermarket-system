package partB_categories;

import partA_core.InventoryManager;

public class FoodProduct extends InventoryManager {
    private String expiryDate;

    public FoodProduct(String id, String name, double price, int qty,
                       String supplier, String location, String expiryDate) {
        super(id, name, price, qty, "Food", supplier, location);
        this.expiryDate = expiryDate;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.2;
    }

    @Override
    public boolean validateProduct() {
        return expiryDate != null && !expiryDate.isEmpty();
    }

    @Override
    public String getCategoryDescription() {
        return "Perishable food item";
    }

    @Override
    public String toString() {
        return super.toString() + ", Expiry: " + expiryDate;
    }
}