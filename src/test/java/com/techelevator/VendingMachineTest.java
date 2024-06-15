package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @Before
    public void setup() {
        // Ensure the inventory file is loaded with default data
        try (FileWriter writer = new FileWriter("vendingmachine.csv")) {
            writer.write("A1|Potato Crisps|3.05|Chip\n");
            writer.write("B1|Moonpie|1.80|Candy\n");
            writer.write("C1|Cola|1.25|Drink\n");
            writer.write("D1|Gum|0.75|Gum\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        vendingMachine = new VendingMachine();
    }

    @Test
    public void testFeedMoney() {
        vendingMachine.feedMoney(5.0);
        assertEquals(5.0, vendingMachine.getCurrentBalance(), 0.01);
    }

    @Test
    public void testSelectProduct() {
        vendingMachine.feedMoney(5.0);
        vendingMachine.selectProduct("A1");
        assertEquals(1.95, vendingMachine.getCurrentBalance(), 0.01);
        assertEquals(4, vendingMachine.getInventory().get("A1").getQuantity());
    }

    @Test
    public void testFinishTransaction() {
        vendingMachine.feedMoney(2.0);
        vendingMachine.finishTransaction();
        assertEquals(0.0, vendingMachine.getCurrentBalance(), 0.01);
    }
}
