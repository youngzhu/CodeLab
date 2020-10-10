package com.youngzy.leetcode.design;

import java.util.*;

/**
 * 蝇量模式 Flyweight Pattern
 * 将价格相同的实例看作一个实例
 *
 * 执行用时：898 ms, 在所有 Java 提交中击败了5.37% 的用户
 * 内存消耗：59.9 MB, 在所有 Java 提交中击败了98.92% 的用户
 *
 * 空间没少，时间效率更差了。。
 *
 */
public class Cashier1 implements Cashier {
    int count = 1; // 计数器，注意不能从0开始

    Map<Integer, Product> productMap;
    Set<Product> set = new HashSet<>();

    Map<Integer, List<Integer>> priceMap;

    int n;
    double discountPercent;

    /**
     *
     * @param n - 折扣频率
     * @param discount - 折扣
     * @param products - 超市中的商品列表
     * @param prices - 各商品的价格
     */
    public Cashier1(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discountPercent = 1 - discount / 100.0;

        priceMap = new HashMap<>();

        for (int i = 0; i < prices.length; i++) {
            int id = products[i];
            int price = prices[i];

            List<Integer> idList = priceMap.get(price);
            if (idList == null) {
                idList = new LinkedList<>();
                idList.add(id);

                priceMap.put(price, idList);
            } else {
                idList.add(id);
            }
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

        Map<Integer, Integer> billMap = new HashMap<>();
        double discount = 1.0;

        for (int i = 0; i < product.length; i ++) {
            int id = product[i];
            int amt = amount[i];

            for (Map.Entry<Integer, List<Integer>> entry : priceMap.entrySet()) {
                if (entry.getValue().contains(id)) {
                    if (! billMap.containsKey(entry.getKey())) {
                        billMap.put(entry.getKey(), 0);
                    }

                    int allAmt = billMap.getOrDefault(entry.getKey(), 0);

                    billMap.put(entry.getKey(), allAmt + amt);

                    break;
                }

            }

        }

        for (Map.Entry<Integer, Integer> entry : billMap.entrySet()) {
            answer += entry.getKey() * entry.getValue();
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Double.compare(product.price, price) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(price);
        }
    }
}
