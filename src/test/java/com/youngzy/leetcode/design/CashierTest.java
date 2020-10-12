package com.youngzy.leetcode.design;

import org.junit.Test;

import static org.junit.Assert.*;

public class CashierTest {

    @Test
    public void getBill() {
        int[] products = {1, 2, 3, 4, 5, 6, 7};
        int[] prices = {100, 200, 300, 400, 300, 200, 100};
//        Cashier cashier = new Cashier0(3,50, products, prices);
        Cashier cashier = new Cashier3(3,50, products, prices);

        double delta = 0.1;
        int[] purchasedProducts = {1, 2};
        int[] amount = {1, 2};
        assertEquals(500, cashier.getBill(purchasedProducts, amount), delta);

        purchasedProducts = new int[]{3,7};
        amount = new int[]{10,10};
        assertEquals(4000, cashier.getBill(purchasedProducts, amount), delta);

        purchasedProducts = new int[]{1,2,3,4,5,6,7};
        amount = new int[]{1,1,1,1,1,1,1};
        assertEquals(800, cashier.getBill(purchasedProducts, amount), delta);

        purchasedProducts = new int[]{4};
        amount = new int[]{10};
        assertEquals(4000, cashier.getBill(purchasedProducts, amount), delta);

        purchasedProducts = new int[]{7,3};
        amount = new int[]{10,10};
        assertEquals(4000, cashier.getBill(purchasedProducts, amount), delta);

        purchasedProducts = new int[]{7,5,3,1,6,4,2};
        amount = new int[]{10,10,10,9,9,9,7};
        assertEquals(7350, cashier.getBill(purchasedProducts, amount), delta);

        purchasedProducts = new int[]{2,3,5};
        amount = new int[]{5,3,2};
        assertEquals(2500, cashier.getBill(purchasedProducts, amount), delta);

    }


}