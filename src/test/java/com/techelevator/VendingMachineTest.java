package com.techelevator;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    private VendingMachine vendingMachine;
    private static final String INVENTORY_FILE = "vendingmachine.csv";
    private static final String BACKUP_FILE = "vendingmachine_backup.csv";

    @Before
    public void setup() {
        // Delete the backup file if it exists
        try {
            if (Files.exists(Paths.get(BACKUP_FILE))) {
                Files.delete(Paths.get(BACKUP_FILE));
            }
            // Backup the original inventory file
            Files.copy(Paths.get(INVENTORY_FILE), Paths.get(BACKUP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ensure the inventory file is loaded with default data
        try (FileWriter writer = new FileWriter(INVENTORY_FILE)) {
            writer.write("A1|Potato Crisps|3.05|Chip\n");
            writer.write("B1|Moonpie|1.80|Candy\n");
            writer.write("C1|Cola|1.25|Drink\n");
            writer.write("D1|Gum|0.75|Gum\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        vendingMachine = new VendingMachine();
    }

    @After
    public void teardown() {
        // Restore the original inventory file
        try {
            if (Files.exists(Paths.get(INVENTORY_FILE))) {
                Files.delete(Paths.get(INVENTORY_FILE));
            }
            Files.copy(Paths.get(BACKUP_FILE), Paths.get(INVENTORY_FILE), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(Paths.get(BACKUP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFeedMoney() {
        vendingMachine.feedMoney(5.0);
        assertEquals(5.0, vendingMachine.getCurrentBalance(), 0.01);
    }
}