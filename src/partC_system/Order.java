package partC_system;

import partA_core.Product;

public class Order {
    
    private String orderId;
    private Product product;
    private int quantity;
    private double totalPrice;

    public Order(String orderId, Product product, int quantity) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        calculateTotal();
    }

    // ========== Getters and Setters ==========
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // ========== Business Logic ==========
    
    public void calculateTotal() {
        totalPrice = product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId +
                ", Product: " + product.getProductName() +
                ", Qty: " + quantity +
                ", Total: $" + String.format("%.2f", totalPrice);
    }
}