package partB_categories;

import partA_core.InventoryManager;

public class PersonalCareProduct extends InventoryManager {
    private String brand;

    public PersonalCareProduct(String id, String name, double price, int qty,
                               String supplier, String location, String brand) {
        super(id, name, price, qty, "Personal Care", supplier, location);
        this.brand = brand;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.1;
    }

    @Override
    public boolean validateProduct() {
        return brand != null && !brand.isEmpty();
    }

    @Override
    public String getCategoryDescription() {
        return "Personal care item";
    }

    @Override
    public String toString() {
        return super.toString() + ", Brand: " + brand;
    }
}
