package com.techelevator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class ApplicationTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void testDisplayItemsOption() {
        provideInput("1\n4\n");
        Application.main(new String[]{});
        String output = getOutput();
        assertTrue(output.contains("Potato Crisps"));
    }

    @Test
    public void testFeedMoneyAndSelectProduct() {
        provideInput("2\n2\n5\n1\nA1\n3\n4\n");
        Application.main(new String[]{});
        String output = getOutput();
        assertTrue(output.contains("Current Balance: $5.0"));
        assertTrue(output.contains("Dispensing: Potato Crisps"));
        assertTrue(output.contains("Current Balance: $1.95"));
    }

    @Test
    public void testFinishTransaction() {
        provideInput("2\n2\n2\n3\n4\n");
        Application.main(new String[]{});
        String output = getOutput();
        assertTrue(output.contains("Current Balance: $2.0"));
        assertTrue(output.contains("Returning change: $2.0"));
    }

    @Test
    public void testInvalidOption() {
        provideInput("5\n4\n");
        Application.main(new String[]{});
        String output = getOutput();
        assertTrue(output.contains("Invalid option."));
    }
}
