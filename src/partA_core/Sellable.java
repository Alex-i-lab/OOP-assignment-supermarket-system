package partA_core;

public interface Sellable {
    void processSale(int quantity);
    double calculateFinalPrice(int quantity);
    void printReceipt();
}