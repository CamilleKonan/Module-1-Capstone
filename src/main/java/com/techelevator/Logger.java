package com.techelevator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Logger {
    private static final String LOG_FILE = "Log.txt";
    private static final String SALES_REPORT_FILE = "SalesReport.txt";

    public void logTransaction(Transaction transaction) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(transaction.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }

    public void generateSalesReport(Map<String, Product> inventory) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SALES_REPORT_FILE))) {
            double totalSales = 0.0;
            for (Product product : inventory.values()) {
                int sold = 5 - product.getQuantity(); // Assuming max quantity is 5
                double sales = sold * product.getPrice();
                bw.write(product.getName() + "|" + sold + "\n");
                totalSales += sales;
            }
            bw.write("\n**TOTAL SALES** $" + totalSales + "\n");
        } catch (IOException e) {
            System.out.println("Error writing sales report: " + e.getMessage());
        }
    }
}
