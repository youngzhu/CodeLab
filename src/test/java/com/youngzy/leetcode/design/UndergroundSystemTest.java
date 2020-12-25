package com.youngzy.leetcode.design;

import org.junit.Test;

import static org.junit.Assert.*;

public class UndergroundSystemTest {

    @Test
    public void test() {
        UndergroundSystem undergroundSystem = new UndergroundSystem4();

        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        // 返回 14.0。从 "Paradise"（时刻 8）到 "Cambridge"(时刻 22)的行程只有一趟
        double result = undergroundSystem.getAverageTime("Paradise", "Cambridge");
        assertEquals(14, result, 0.000001);
        // 返回 11.0。总共有 2 躺从 "Leyton" 到 "Waterloo" 的行程，
        //编号为 id=45 的乘客出发于 time=3 到达于 time=15，
        //编号为 id=27 的乘客于 time=10 出发于 time=20 到达。
        //所以平均时间为 ( (15-3) + (20-10) ) / 2 = 11.0
        result = undergroundSystem.getAverageTime("Leyton", "Waterloo");
        assertEquals(11, result, 0.000001);
        undergroundSystem.checkIn(10, "Leyton", 24);
        // 返回 11.0
        result = undergroundSystem.getAverageTime("Leyton", "Waterloo");
        assertEquals(11, result, 0.000001);
        undergroundSystem.checkOut(10, "Waterloo", 38);
        // 返回 12.0
        result = undergroundSystem.getAverageTime("Leyton", "Waterloo");
        assertEquals(12, result, 0.000001);
    }

}