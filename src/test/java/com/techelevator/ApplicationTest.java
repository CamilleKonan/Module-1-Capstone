package com.techelevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


@BeforeEach
public void setUp() {
    System.setOut(new PrintStream(outContent));
}

private void provideInput(String data) {
    ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
    System.setIn(testIn);
}

private String getOutput() {
    return outContent.toString();
}

@Test
public void testApplicationMenu() {
    String simulatedUserInput = "3\n";
    provideInput(simulatedUserInput);

    Application.main(new String[]{});

    String output = getOutput();
    assertTrue(output.contains("Vending Machine Menu:"));
    assertTrue(output.contains("1. Display Items"));
    assertTrue(output.contains("2. Purchase Item"));
    assertTrue(output.contains("3. Exit"));
    assertTrue(output.contains("Exiting. Have a nice day!"));
}

    @Test
    public void testDisplayItems() {
        String simulatedUserInput = "1\n3\n";
        provideInput(simulatedUserInput);

        Application.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Select an option: "));
        assertTrue(output.contains("A1 | Potato Crisps | $3.05 | 5"));
        assertTrue(output.contains("C3 | Mountain Melter | $1.5 | 5"));
        assertTrue(output.contains("B4 | Crunchie | $1.75 | 5"));
        assertTrue(output.contains("C3 | Mountain Melter | $1.5 | 5"));
        assertFalse(output.contains("Products sold out."));
    }

@Test
public void testPurchaseItemInsufficientBalance() {
    String simulatedUserInput = "2\n1\nA1\n3\n3\n";
    provideInput(simulatedUserInput);

    Application.main(new String[]{});

    String output = getOutput();
    assertTrue(output.contains("Purchase Menu:"));
    assertFalse(output.contains("Insufficient balance. Please feed more money."));
}

@Test
public void testFeedMoneyAndPurchaseItem() {
    String simulatedUserInput = "2\n2\n2.00\n1\nA1\n3\n3\n";
    provideInput(simulatedUserInput);

    Application.main(new String[]{});

    String output = getOutput();
    assertTrue(output.contains("Current Balance: $2.0"));
    assertFalse(output.contains("Insufficient balance. Please feed more money."));
}
}

