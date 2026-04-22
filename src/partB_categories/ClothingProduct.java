package partB_categories;

import partA_core.InventoryManager;

public class ClothingProduct extends InventoryManager {
    private String size; // e.g., "M", "L"

    public ClothingProduct(String id, String name, double price, int qty,
                           String supplier, String location, String size) {
        super(id, name, price, qty, "Clothing", supplier, location);
        this.size = size;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.2;
    }

    @Override
    public boolean validateProduct() {
        return size != null && !size.isEmpty();
    }

    @Override
    public String getCategoryDescription() {
        return "Wearable clothing item";
    }

    @Override
    public String toString() {
        return super.toString() + ", Size: " + size;
    }
}
