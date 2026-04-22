package partA_core;

public class InventoryManager extends Product implements Sellable {
    
    private String supplierName;
    private String storageLocation;

    public InventoryManager(String productId, String productName, double price, int quantity, String category, String supplierName, String storageLocation) {
        super(productId, productName, price, quantity, category);
        this.supplierName = supplierName;
        this.storageLocation = storageLocation;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    // ========== Sellable Interface Implementation ==========

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.1;
    }

    @Override
    public double applyTax() {
        return getPrice() * 0.18;
    }

    @Override
    public boolean checkAvailability(int qty) {
        return getQuantity() >= qty;
    }

    @Override
    public double calculateTotalValue() {
        return getPrice() * getQuantity();
    }

    @Override
    public void updateStock(int qty) {
        setQuantity(getQuantity() - qty);
    }

    @Override
    public boolean validateProduct() {
        return getPrice() > 0 && getQuantity() >= 0;
    }

    @Override
    public void generateReport() {
        System.out.println("Inventory Report: " + toString());
    }

    @Override
    public String getCategoryDescription() {
        return "General Product";
    }

    @Override
    public void processSale(int quantity) {
        if (checkAvailability(quantity)) {
            updateStock(quantity);
            System.out.println("Sale successful!");
        } else {
            System.out.println("Insufficient stock!");
        }
    }

    @Override
    public double calculateFinalPrice(int quantity) {
        return (getPrice() * quantity) + applyTax() - calculateDiscount();
    }

    @Override
    public void printReceipt() {
        System.out.println("Receipt printed for: " + getProductName());
    }

    @Override
    public int getStockQuantity() {
        return getQuantity();
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Supplier: " + supplierName +
                ", Location: " + storageLocation;
    }
}