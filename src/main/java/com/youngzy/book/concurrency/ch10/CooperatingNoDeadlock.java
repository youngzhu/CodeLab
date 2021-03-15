package com.youngzy.book.concurrency.ch10;

import java.util.HashSet;
import java.util.Set;

/**
 * 10-6 通过公开调用来避免在互相协作的对象之间产生死锁
 */
public class CooperatingNoDeadlock {
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
            boolean reachedDestination;
            
            synchronized (this) {
                this.location = location;
                reachedDestination = location.equals(destination);
            }
            if (reachedDestination) 
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

        public Image getImage() {
            Set<Taxi> copy;
            synchronized (this) {
                copy = new HashSet<>(taxis);
            }
            Image image = new Image();
            for (Taxi taxi : copy)
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
