package com.techelevator;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();

        while (true) {
            System.out.println("\nVending Machine Menu:");
            System.out.println("1. Display Items");
            System.out.println("2. Purchase Item");
            System.out.println("3. Exit");

            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    vendingMachine.displayItems();
                    break;
                case "2":
                    boolean purchasing = true;
                    while (purchasing) {
                        System.out.println("\nPurchase Menu:");
                        System.out.println("Current Balance: $" + vendingMachine.getCurrentBalance());
                        System.out.println("1. Select Product");
                        System.out.println("2. Feed Money");
                        System.out.println("3. Finish Transaction");
                        System.out.println("4. Cancel Purchase");

                        System.out.print("Select an option: ");
                        String purchaseChoice = scanner.nextLine();

                        switch (purchaseChoice) {
                            case "1":
                                vendingMachine.displayItems();
                                System.out.print("Enter the slot ID of the product: ");
                                String slotID = scanner.nextLine();
                                vendingMachine.selectProduct(slotID);
                                break;
                            case "2":
                                System.out.print("Enter the amount of money to feed: ");
                                try {
                                    double amount = Double.parseDouble(scanner.nextLine());
                                    vendingMachine.feedMoney(amount);
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid amount.");
                                }
                                break;
                            case "3":
                                vendingMachine.finishTransaction();
                                purchasing = false;
                                break;
                            case "4":
                                System.out.println("Purchase cancelled.");
                                purchasing = false;
                                break;
                            default:
                                System.out.println("Invalid option. Please select a valid option.");
                        }
                    }
                    break;
                case "3":
                    vendingMachine.generateSalesReport();
                    System.out.println("Exiting. Have a nice day!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option from the menu.");
            }
        }
    }
}