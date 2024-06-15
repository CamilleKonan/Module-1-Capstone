package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

    private Product product;

    @Before
    public void setup() {
        product = new Product("A1", "Potato Crisps", 3.05, 5, "Chip");
    }

    @Test
    public void testGetSlotID() {
        Assert.assertEquals("A1", product.getSlotID());
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Potato Crisps", product.getName());
    }

    @Test
    public void testGetPrice() {
        Assert.assertEquals(3.05, product.getPrice(), 0.01);
    }

    @Test
    public void testGetType() {
        Assert.assertEquals("Chip", product.getType());
    }

    @Test
    public void testGetQuantity() {
        Assert.assertEquals(5, product.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        product.setQuantity(3);
        Assert.assertEquals(3, product.getQuantity());
    }
}
