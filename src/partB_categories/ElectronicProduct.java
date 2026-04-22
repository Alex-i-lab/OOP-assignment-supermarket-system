package partB_categories;

import partA_core.InventoryManager;

public class ElectronicProduct extends InventoryManager {
    private int warrantyMonths;

    public ElectronicProduct(String id, String name, double price, int qty,
                             String supplier, String location, int warrantyMonths) {
        super(id, name, price, qty, "Electronic", supplier, location);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.15;
    }

    @Override
    public boolean validateProduct() {
        return warrantyMonths > 0;
    }

    @Override
    public String getCategoryDescription() {
        return "Electronic device with warranty";
    }

    @Override
    public String toString() {
        return super.toString() + ", Warranty: " + warrantyMonths + " months";
    }
}
