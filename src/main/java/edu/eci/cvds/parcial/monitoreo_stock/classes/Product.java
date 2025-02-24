package edu.eci.cvds.parcial.monitoreo_stock.classes;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private String category;

    public Product(String name, int price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
