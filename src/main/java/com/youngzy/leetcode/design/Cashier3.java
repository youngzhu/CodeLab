package com.youngzy.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 真•蝇量模式 Flyweight Pattern
 *
 * 在上一个版本基础上优化一下：动态指定pool的大小
 * 期望可以减少空间的占用
 *
 * 测试结果：没啥进步
 */
public class Cashier3 implements Cashier {
    // 不能用static
    // 单个测试没问题，提交测试就会报错了
    int count = 1; // 计数器，注意不能从0开始

    int n;
    double discountPercent;

    // 商品和价格的对应关系
    Map<Integer, Integer> pp;
    // 为每一个不同的价格指定一个唯一id
    Map<Integer, Integer> priceMap;

    Factory factory;

    /**
     *
     * @param n - 折扣频率
     * @param discount - 折扣
     * @param products - 超市中的商品列表
     * @param prices - 各商品的价格
     */
    public Cashier3(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discountPercent = 1 - discount / 100.0;

        pp = new HashMap<>();
        priceMap = new HashMap<>();
        int priceId = 0;
        for (int i = 0; i < prices.length; i++) {
            pp.put(products[i], prices[i]);

            if (! priceMap.containsKey(prices[i])) {
                priceMap.put(prices[i], priceId++);
            }
        }

        factory = new Factory(priceMap.size());
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
            int priceId = priceMap.get(price);
            if (pool[priceId] == null) {
                pool[priceId] = new Product(price);
            }
            return pool[priceId];
        }
    }
}
