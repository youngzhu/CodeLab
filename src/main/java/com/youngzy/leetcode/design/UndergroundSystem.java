package com.youngzy.leetcode.design;

import java.util.*;

/**
 * 1396. 设计地铁系统
 *
 * 请你实现一个类UndergroundSystem，它支持以下 3 种方法：
 *
 * 1.checkIn(int id, string stationName, int t)
 * 编号为id的乘客在 t时刻进入地铁站stationName。
 * 一个乘客在同一时间只能在一个地铁站进入或者离开。
 *
 * 2.checkOut(int id, string stationName, int t)
 * 编号为id的乘客在 t时刻离开地铁站 stationName。
 *
 * 3.getAverageTime(string startStation, string endStation)
 * 返回从地铁站startStation到地铁站endStation的平均花费时间。
 * 平均时间计算的行程包括当前为止所有从startStation直接到达endStation的行程。
 * 调用getAverageTime时，询问的路线至少包含一趟行程。
 * 你可以假设所有对checkIn和checkOut的调用都是符合逻辑的。
 * 也就是说，如果一个顾客在 t1时刻到达某个地铁站，那么他离开的时间t2一定满足t2 > t1。所有的事件都按时间顺序给出。
 *
 *
 * 示例：
 *
 * UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(45, "Leyton", 3);
 * undergroundSystem.checkIn(32, "Paradise", 8);
 * undergroundSystem.checkIn(27, "Leyton", 10);
 * undergroundSystem.checkOut(45, "Waterloo", 15);
 * undergroundSystem.checkOut(27, "Waterloo", 20);
 * undergroundSystem.checkOut(32, "Cambridge", 22);
 * // 返回 14.0。从 "Paradise"（时刻 8）到 "Cambridge"(时刻 22)的行程只有一趟
 * undergroundSystem.getAverageTime("Paradise", "Cambridge");
 * // 返回 11.0。总共有 2 躺从 "Leyton" 到 "Waterloo" 的行程，
 * 编号为 id=45 的乘客出发于 time=3 到达于 time=15，
 * 编号为 id=27 的乘客于 time=10 出发于 time=20 到达。
 * 所以平均时间为 ( (15-3) + (20-10) ) / 2 = 11.0
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");
 * undergroundSystem.checkIn(10, "Leyton", 24);
 * // 返回 11.0
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");
 * undergroundSystem.checkOut(10, "Waterloo", 38);
 * // 返回 12.0
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");
 *
 *
 * 提示：
 *
 * 总共最多有20000次操作。
 * 1 <= id, t <= 10^6
 * 所有的字符串包含大写字母，小写字母和数字。
 * 1 <=stationName.length <= 10
 * 与标准答案误差在10^-5以内的结果都视为正确结果。
 *
 * 链接：https://leetcode-cn.com/problems/design-underground-system
 */
public interface UndergroundSystem {

    /**
     * 编号为id的乘客在 t时刻进入地铁站stationName。
     * 一个乘客在同一时间只能在一个地铁站进入或者离开。
     *
     * @param id
     * @param stationName
     * @param t
     */
    public void checkIn(int id, String stationName, int t) ;


    public void checkOut(int id, String stationName, int t) ;

    public double getAverageTime(String startStation, String endStation) ;

}
