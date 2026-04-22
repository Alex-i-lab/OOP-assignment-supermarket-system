package partB_categories;

import partA_core.InventoryManager;

public class BeverageProduct extends InventoryManager {
    private double volumeLiters;

    public BeverageProduct(String id, String name, double price, int qty,
                           String supplier, String location, double volumeLiters) {
        super(id, name, price, qty, "Beverage", supplier, location);
        this.volumeLiters = volumeLiters;
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.05;
    }

    @Override
    public boolean validateProduct() {
        return volumeLiters > 0;
    }

    @Override
    public String getCategoryDescription() {
        return "Drinkable beverage";
    }

    @Override
    public String toString() {
        return super.toString() + ", Volume: " + volumeLiters + " L";
    }
}
