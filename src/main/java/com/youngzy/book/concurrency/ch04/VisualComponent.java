package com.youngzy.book.concurrency.ch04;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 4-9 将线程安全委托给多个状态变量
 *
 * CopyOnWriteArrayList 是一个线程安全的列表
 *
 * 由于鼠标事件和键盘事件没有耦合关系，
 * 所以可以把整个类的线程安全分别委托为2个状态变量
 */
public class VisualComponent {
    private final List<KeyListener>  keyListeners = new CopyOnWriteArrayList<KeyListener>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void  addKeyListener(KeyListener listener) {
        keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
        mouseListeners.remove(listener);
    }
}
