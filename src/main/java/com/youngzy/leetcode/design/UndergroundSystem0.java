package com.youngzy.leetcode.design;

import java.util.*;

/*
问题：测试不通过，可能是 进-进-出-出 的问题，尝试修复看看
 */
public class UndergroundSystem0 implements UndergroundSystem {
    // key - 站名
    // value - 每一站进站/出站的人
    Map<String, List<InOut>> inMap, outMap;

    public UndergroundSystem0() {
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

        List<InOut> list = inMap.getOrDefault(stationName, new ArrayList<>());
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
        for (List<InOut> in : inMap.values()) {
            if (in.contains(inOut))
                return false;
        }

        for (List<InOut> in : outMap.values()) {
            if (in.contains(inOut))
                return false;
        }

        return true;
    }

    public void checkOut(int id, String stationName, int t) {
        InOut out = new InOut(id, t);

        if (! check(out)) {
            return;
        }

        List<InOut> list = outMap.getOrDefault(stationName, new ArrayList<>());

        list.add(out);
        outMap.putIfAbsent(stationName, list);
    }

    public double getAverageTime(String startStation, String endStation) {
        List<InOut> in = inMap.get(startStation);
        List<InOut> out = outMap.get(endStation);

        int round = 0; // 人次
        int totalTime = 0; // 总时间

        for (InOut o : out) {
            for (InOut i : in) {
                if (o.id == i.id && o.time > i.time) {
                    round++;
                    totalTime += o.time - i.time;
                }
            }
        }

        return 1.0 * totalTime / round;
    }

    private class InOut {
        int id; // 人
        int time; // 时间

        public InOut(int id, int time) {
            this.id = id;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InOut inOut = (InOut) o;
            return id == inOut.id &&
                    time == inOut.time;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, time);
        }
    }
}
