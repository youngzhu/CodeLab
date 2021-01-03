package com.youngzy.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 1396. 设计地铁系统
 *
 * 在做V4的时候，想到要把什么再放进蝇量对象里的
 * 现在想不起来了。。
 *
 * 仔细看了一下，应该是没法再加了
 *
 */
public class UndergroundSystem5 implements UndergroundSystem {
    // key - id
    // value - 进站信息（站名+时间）
    Map<Integer, In> idInMap = new HashMap<>();
    private final Factory factory = new Factory();

    public void checkIn(int id, String stationName, int t) {
        // 不能用 putIfAbsent
        idInMap.put(id, new In(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        In in = idInMap.get(id);
        InFlyweight inFlyweight = factory.getFlyweight(in.station);
        inFlyweight.out(stationName, in.time, t);
    }

    public double getAverageTime(String startStation, String endStation) {
        return factory.getFlyweight(startStation).getResult(endStation);
    }

    class Factory {
        private final Map<String, InFlyweight> map = new HashMap<>();

        InFlyweight getFlyweight(String stationName) {
            InFlyweight flyweight = map.getOrDefault(stationName, new InFlyweight());
            map.putIfAbsent(stationName, flyweight);
            return flyweight;
        }
    }

    class InFlyweight {
        Map<String, Result> outMap = new HashMap<>();

        void out(String outStation, int inTime, int outTime) {
            Result result = outMap.getOrDefault(outStation, new Result());
            result.add(outTime - inTime);
            outMap.putIfAbsent(outStation, result);
        }

        double getResult(String outStation) {
            return outMap.get(outStation).getResult();
        }
    }

    private class Result {
        int totalTime; // 总时间
        int personTime; // 人次

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

    class In {
        private String station;
        private int time;

        In(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }
}
