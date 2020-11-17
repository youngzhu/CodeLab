package com.youngzy.book.concurrency.ch04;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 4-4 基于监视器模式的车辆追踪程序
 *
 * 通过在返回之前复制可变的数据来维持线程安全的
 *
 * 车辆数据非常大的情况下将极大地降低性能
 * 因为 deepCopy 是从一个 synchronized 方法中调用的，在执行时间较长的复制操作中，
 * 锁将一直被占用。
 */
public class MonitorVehicleTracker {
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID:" + id);
        }

        loc.x = x;
        loc.y = y;
    }
    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> map) {
        Map<String, MutablePoint> result = new HashMap<>();

        for (String id : map.keySet()) {
            result.put(id, new MutablePoint(map.get(id)));
        }

        return Collections.unmodifiableMap(result);
    }
}
