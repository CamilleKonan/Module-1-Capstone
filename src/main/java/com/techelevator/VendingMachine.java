package com.techelevator;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class VendingMachine {
    private Map<String, Product> inventory;
    private double currentBalance;
    private static final String INVENTORY_FILE = "vendingmachine.csv";
    private Logger logger;

    public VendingMachine() {
        this.inventory = new LinkedHashMap<>();
        this.currentBalance = 0.0;
        this.logger = new Logger();
        loadInventory();
    }

    private void loadInventory() {
        try (BufferedReader br = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                String slotId = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String type = parts[3];
                int quantity = 5; // Initially stocked to maximum
                inventory.put(slotId, new Product(slotId, name, price, quantity, type));
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
    }

    public void displayItems() {
        for (Product product : inventory.values()) {
            System.out.println(product.getSlotID() + " | " + product.getName() + " | $" + product.getPrice() + " | " + (product.getQuantity() > 0 ? product.getQuantity() : "SOLD OUT"));
        }
    }

    public void feedMoney(double amount) {
        if (amount <= 0) {
            System.out.println("Please enter a positive amount.");
            return;
        }
        currentBalance += amount;
        System.out.println("Current Money Provided: $" + currentBalance);
    }

    public void selectProduct(String slotID) {
        Product product = inventory.get(slotID.toUpperCase());
        if (product == null) {
            System.out.println("Invalid product selection.");
            return;
        }

        if (product.getQuantity() == 0) {
            System.out.println("Product SOLD OUT.");
            return;
        }

        if (currentBalance < product.getPrice()) {
            System.out.println("Insufficient funds.");
            return;
        }

        product.setQuantity(product.getQuantity() - 1);
        currentBalance -= product.getPrice();

        String soundMessage = getSoundMessage(product.getType());
        System.out.println("Dispensing: " + product.getName() + " | " + product.getType() + ", " + soundMessage);
        logger.logTransaction(new Transaction("PURCHASE", slotID, product.getPrice()));
    }

    private String getSoundMessage(String type) {
        switch (type.toLowerCase()) {
            case "chip":
                return "Crunch Crunch, Yum!";
            case "candy":
                return "Munch Munch, Yum!";
            case "drink":
                return "Glug Glug, Yum!";
            case "gum":
                return "Chew Chew, Yum!";
            default:
                return "Yum";
        }
    }

    public void finishTransaction() {
        System.out.println("Returning change: $" + currentBalance);
        logger.logTransaction(new Transaction("CHANGE", "", currentBalance));
        currentBalance = 0.0;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public Map<String, Product> getInventory() {
        return inventory;
    }

    public void generateSalesReport() {
        logger.generateSalesReport(inventory);
    }
}