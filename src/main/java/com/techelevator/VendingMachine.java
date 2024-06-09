package com.techelevator;

import org.junit.platform.engine.support.descriptor.FileSystemSource;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendingMachine {
    private List<Product> inventory;
    private double currentBalance;
    private static final String INVENTORY_FILE = "vendingmachine.csv";
    private static final String LOG_FILE = "Log.txt";
    private static final String SALES_REPORT_FILE = "SalesReport.txt";

    public VendingMachine() {
        this.inventory = new ArrayList<Product>();
        this.currentBalance = 0.0;
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
                inventory.add(new Product(slotId, name, price, quantity, type));
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
    }

    public void displayItems() {
        for (Product product : inventory) {
            System.out.println(product.getSlotID() + " | " + product.getName() + " | $" + product.getPrice() + " | " + (product.getQuantity() > 0 ? product.getQuantity() : "SOLD OUT"));
        }
    }

    public void feedMoney(double amount) {
        currentBalance += amount;
        System.out.println("Current Money Provided: $" + currentBalance);
    }

    public void selectProduct(String slotID) {
        for (Product product : inventory) {
            if (product.getSlotID().equalsIgnoreCase(slotID)) {
                if (product.getQuantity() > 0 && currentBalance >= product.getPrice()) {
                    product.setQuantity(product.getQuantity() - 1);
                    currentBalance -= product.getPrice();
                   //Determines the appropriate message based on product
                    String soundMessage = "";
                    switch (product.getType().toLowerCase()) {
                        case "chip":
                            soundMessage ="Crunch Crunch, Yum!";
                            break;
                        case "candy":
                            soundMessage ="Munch Munch, Yum!";
                            break;
                        case "drink":
                            soundMessage ="Glug Glug, Yum!";
                            break;
                        case "gum":
                            soundMessage ="Chew Chew, Yum!";
                            break;
                        default:
                            soundMessage = "Yum";
                            break;
                    }

                    // Print the dispensing message
                    System.out.println("Dispensing: " + product.getName() + " | " + product.getType() + ", " + soundMessage);
                    logTransaction("PURCHASE " + slotID + " $" + product.getPrice());
                    return;
                } else if (product.getQuantity() == 0) {
                    System.out.println("product SOLD OUT.");
                } else {
                    System.out.println("Insufficient funds.");
                }
            }
        }
        System.out.println("Invalid product selection.");
    }

    public void finishTransaction() {
        System.out.println("Returning change: $" + currentBalance);
        logTransaction("CHANGE $" + currentBalance);
        currentBalance = 0.0;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    private void logTransaction(String logEntry) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(new Date() + " " + logEntry + "\n");
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }

    public void generateSalesReport() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SALES_REPORT_FILE))) {
            double totalSales = 0.0;
            for (Product product : inventory) {
                int sold = 5 - product.getQuantity(); //Assuming max quantity is 5
                double sales = sold + product.getPrice();
                bw.write(product.getName() + "|" + sold + "\n");
                totalSales += sales;
            }
            bw.write("\n**TOTAL SALES** $" + totalSales + "\n");
            } catch (IOException e) {
            System.out.println("Error writing sales report: " + e.getMessage());
        }
    }

}