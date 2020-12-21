package com.youngzy.leetcode.design;

import java.util.*;

/**
 * 1396. 设计地铁系统
 *
 * 还是要以id为key记录进站，后面out时才能快速地取到进站信息
 *
 */
public class UndergroundSystem2 implements UndergroundSystem {

    //
    private Map<Integer, In> inMap = new HashMap<>();
    // key: 进站名+出站名
    private Map<String, Result> stationInOutMap = new HashMap<>();

    public void checkIn(int id, String stationName, int t) {
        inMap.put(id, new In(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        In in = inMap.get(id);
        String inOut = in.station + "_" + stationName;

        Result result = stationInOutMap.getOrDefault(inOut, new Result());
        result.add(t - in.time);
        stationInOutMap.putIfAbsent(inOut, result);
    }

    public double getAverageTime(String startStation, String endStation) {
        String inOut = startStation + "_" + endStation;
        return stationInOutMap.get(inOut).getResult();
    }

    private class In {
        private String station;
        private int time;

        In(String station, int time) {
            this.station = station;
            this.time = time;
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
            return 1.0 * totalTime / personTime;
        }
    }
}
