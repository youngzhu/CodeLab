package com.youngzy.book.concurrency.ch10;

import java.util.HashSet;
import java.util.Set;

/**
 * 10-5 在互相协作对象之间的锁顺序死锁（不要这么做）
 */
public class CooperatingDeadlock {
    // 注意：容易发生死锁

    /**
     * 出租车对象
     */
    class Taxi {
        private Point location, destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        public synchronized void setLocation(Point location) {
            this.location = location;
            if (location.equals(destination))
                dispatcher.notifyAvailable(this);
        }

        public synchronized Point getDestination() {
            return destination;
        }

        public synchronized void setDestination(Point destination) {
            this.destination = destination;
        }
    }

    /**
     * 出租车车队
     */
    class Dispatcher {
        private final Set<Taxi> taxis;
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<>();
            availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        public synchronized Image getImage() {
            Image image = new Image();
            for (Taxi taxi : taxis)
                image.drawMarker(taxi.getLocation());
            return image;
        }
    }

    class Image {
        public void drawMarker(Point point) {

        }
    }

    interface Point {}
}
