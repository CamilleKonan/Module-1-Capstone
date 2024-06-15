package com.techelevator;

import java.util.Date;

public class Transaction {
    private Date date;
    private String type;
    private String slotID;
    private double amount;

    public Transaction(String type, String slotID, double amount) {
        this.date = new Date();
        this.type = type;
        this.slotID = slotID;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getSlotID() {
        return slotID;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return date + " " + type + " " + slotID + " $" + amount;
    }
}
