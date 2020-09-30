package com.youngzy.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 常规做法
 *
 * 执行用时：164 ms, 在所有 Java 提交中击败了29.53% 的用户
 * 内存消耗：58.8 MB, 在所有 Java 提交中击败了98.92% 的用户
 */
public class Cashier0 implements Cashier {
    int count = 1; // 计数器，注意不能从0开始

    Map<Integer, Product> productMap;

    int n;
    double discountPercent;

    /**
     *
     * @param n - 折扣频率
     * @param discount - 折扣
     * @param products - 超市中的商品列表
     * @param prices - 各商品的价格
     */
    public Cashier0(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discountPercent = 1 - discount / 100.0;

        productMap = new HashMap<>();

        for (int i = 0; i < prices.length; i++) {
            int id = products[i];
            Product product = new Product(id, prices[i]);

            productMap.put(id, product);
        }

    }

    /**
     *
     * @param product - 购买的商品
     * @param amount - 各商品的数量
     * @return
     */
    public double getBill(int[] product, int[] amount) {
        double answer = 0;

        for (int i = 0; i < product.length; i ++) {
            Product p = productMap.get(product[i]);
            answer += p.price * amount[i];
        }

        if (count % n == 0) {
            answer *= discountPercent;
        }

        count ++;

        return answer;
    }

    private class Product {
        int id;
        double price;

        public Product(int id, double price) {
            this.id = id;
            this.price = price;
        }
    }
}
