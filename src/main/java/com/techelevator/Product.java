package com.techelevator;

public class Product {
    private String slotID;
    private String name;
    private double price;
    private String type;
    private int quantity;

    public Product(String slotID, String name, double price, int quantity, String type) {
        this.slotID = slotID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public String getSlotID() {
        return slotID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
