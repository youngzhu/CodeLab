package com.youngzy.leetcode.design;

import java.util.*;

/**
 * 真•蝇量模式 Flyweight Pattern
 *
 * 空间没少，时间效率更差了。。
 *
 */
public class Cashier2 implements Cashier {
    // 最多有1000种不同的价格
    private static final int MAX_PRICE_NUMBER = 1000;

    // 不能用static
    // 单个测试没问题，提交测试就会报错了
    int count = 1; // 计数器，注意不能从0开始

    Factory factory = new Factory(MAX_PRICE_NUMBER);

    int n;
    double discountPercent;
//    int[] products, prices;
    Map<Integer, Integer> pp;

    /**
     *
     * @param n - 折扣频率
     * @param discount - 折扣
     * @param products - 超市中的商品列表
     * @param prices - 各商品的价格
     */
    public Cashier2(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discountPercent = 1 - discount / 100.0;

//        this.products = products;
//        this.prices = prices;

        pp = new HashMap<>();
        for (int i = 0; i < prices.length; i++) {
            pp.put(products[i], prices[i]);
        }
    }

    /**
     *
     * @param product - 购买的商品
     * @param amount - 购买商品的数量
     * @return
     */
    public double getBill(int[] product, int[] amount) {
        double bill = 0;

        for (int i = 0; i < product.length; i++) {
            int price = pp.get(product[i]);
            bill += factory.getProduct(price).getBill(amount[i]);
        }

        if (count % n == 0) {
            bill *= discountPercent;
        }

        count ++;

        return bill;
    }

    private class Product {
        private double price;

        public Product(double price) {
            this.price = price;
        }

        double getBill(int amount) {
            return price * amount;
        }

    }

    private class Factory {
        private Product[] pool;

        public Factory(int maxNum) {
            pool = new Product[maxNum];
        }

        public Product getProduct(int price) {
            if (pool[price] == null) {
                pool[price] = new Product(price);
            }
            return pool[price];
        }
    }
}
