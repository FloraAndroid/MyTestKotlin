package com.example.user.mytestkotlin;

import com.example.user.mytestkotlin.CoffieExample.CoffeeOrder;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class CoffeeOrderTest {
    private final static float PRICE_TEST = 5.0f;
    private CoffeeOrder mOrder;
    @Before
    public void setUp() {
        mOrder = new CoffeeOrder(PRICE_TEST);
    }
    @Test
    public void orderIsNotNull() {
        assertNotNull(mOrder);
    }
    @Test
    public void orderDecrement() {
        mOrder.decrementCoffeeCount();
        assertEquals(0, mOrder.getCoffeeCount());
        mOrder.setCoffeeCount(25);
        mOrder.decrementCoffeeCount();
        assertEquals(24, mOrder.getCoffeeCount());
    }
    @Test
    public void orderIncrement() {
        mOrder.incrementCoffeeCount();
        assertEquals(1, mOrder.getCoffeeCount());
        mOrder.setCoffeeCount(25);
        mOrder.incrementCoffeeCount();
        assertEquals(26, mOrder.getCoffeeCount());
    }

    @Test
    public void orderTotalPrice() {
        assertEquals(0.0f, mOrder.getTotalPrice());
        mOrder.setCoffeeCount(25);
        float price=25*PRICE_TEST;
        assertEquals(125.0f, mOrder.getTotalPrice());
    }


}
