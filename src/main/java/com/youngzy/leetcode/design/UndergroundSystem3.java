package com.youngzy.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 1396. 设计地铁系统
 *
 * 试试封装对象和蝇量模式
 *
 * 封装后稍微好点：11x 变为 10x ms
 *
 */
public class UndergroundSystem3 implements UndergroundSystem {

    //
    private Map<Integer, In> inMap = new HashMap<>();
    // key: 进站名+出站名
    private Map<StationInOut, Result> stationInOutMap = new HashMap<>();

    public void checkIn(int id, String stationName, int t) {
        inMap.put(id, new In(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        In in = inMap.get(id);
        StationInOut inOut = new StationInOut(in.station, stationName);

        Result result = stationInOutMap.getOrDefault(inOut, new Result());
        result.add(t - in.time);
        stationInOutMap.putIfAbsent(inOut, result);
    }

    public double getAverageTime(String startStation, String endStation) {
        StationInOut inOut = new StationInOut(startStation, endStation);
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

    private class StationInOut {
        String start;
        String end;

        public StationInOut(String start, String end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StationInOut that = (StationInOut) o;
            return Objects.equals(start, that.start) &&
                    Objects.equals(end, that.end);
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }
}
