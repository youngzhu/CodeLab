package com.youngzy.leetcode.design;

import java.util.*;

/**
 * 1396. 设计地铁系统
 *
 * 修复上一个版本 “进-进-出-出” 的问题
 */
public class UndergroundSystem1 implements UndergroundSystem {
    // key - 站名
    // value - 每一站进站/出站的人
    Map<String, Set<InOut>> inMap, outMap;
    private final Result result = new Result();

    public UndergroundSystem1() {
        inMap = new HashMap<>();
        outMap = new HashMap<>();
    }

    /**
     * 编号为id的乘客在 t时刻进入地铁站stationName。
     * 一个乘客在同一时间只能在一个地铁站进入或者离开。
     *
     * @param id
     * @param stationName
     * @param t
     */
    public void checkIn(int id, String stationName, int t) {
        InOut in = new InOut(id, t);

        if (! check(in)) {
            return;
        }

        Set<InOut> list = inMap.getOrDefault(stationName, new HashSet<>());
        list.add(in);
        inMap.putIfAbsent(stationName, list);
    }

    /**
     * 一个乘客在同一时间只能在一个地铁站进入或者离开
     *
     * @param inOut
     * @return
     */
    private boolean check(InOut inOut) {
        for (Set<InOut> in : inMap.values()) {
            if (in.contains(inOut))
                return false;
        }

        for (Set<InOut> in : outMap.values()) {
            if (in.contains(inOut))
                return false;
        }

        return true;
    }

    /**
     * 不用存储
     * 直接使用，然后舍弃
     *
     * @param id
     * @param stationName
     * @param t
     */
    public void checkOut(int id, String stationName, int t) {
        // ！！！问题在这里，进站信息不能用 出站站名取
        Set<InOut> inSet = inMap.getOrDefault(stationName, new HashSet<>());

        for (InOut in : inSet) {
            if (in.id == id) {
                result.add(t - in.time);
                break;
            }
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        return result.getResult();
    }

    private class InOut {
        int id; // 人
        int time; // 时间

        public InOut(int id, int time) {
            this.id = id;
            this.time = time;
        }

        // id相同即相等
        // 即 同一个人在 进 或 出 中只有一条数据
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InOut inOut = (InOut) o;
            return id == inOut.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    private class Result {
        int totalTime; // 总时间
        int personTime; // 人次

        Result() {
            totalTime = 0;
            personTime = 0;
        }

        void add(int time) {
            totalTime += time;
            personTime++;
        }

        double getResult() {
            if (personTime == 0)
                return 0;
            return 1.0 * totalTime / personTime;
        }
    }
}
