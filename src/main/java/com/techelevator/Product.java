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
        setPrice(price);
        setQuantity(quantity);
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

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "slotID='" + slotID + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
