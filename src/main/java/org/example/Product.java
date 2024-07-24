package org.example;
public class Product {
    private double cost;
    private int quantity;
    private String name;

    // Constructor
    public Product(String name, double cost, int quantity) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }


    public void totalCost() {
        double total = cost * quantity;
        System.out.println("Total cost is " + total);
    }


    public void printProduct() {
        System.out.println(name + " costs " + cost + " and " + quantity + " units were purchased");
    }


    public static void main(String[] args) {
        Product gas = new Product("Gallon of gas", 4.0, 10);

        gas.printProduct();
        gas.totalCost();
    }
}
