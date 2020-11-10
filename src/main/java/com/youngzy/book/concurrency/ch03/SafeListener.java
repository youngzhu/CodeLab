package com.youngzy.book.concurrency.ch03;


/**
 * 3-8 使用工厂方法类防止this引用在构造过程中逸出
 *
 * 有是没看懂。。
 */
public class SafeListener {
    private final EventListener listener;

    // 构造器 私有
    private SafeListener() {
        listener = new EventListener() {
            @Override
            public void onEvent(Event event) {
                doSomething(event);
            }
        };
    }

    // 工厂方法
    public static SafeListener newInstance(EventSource source) {
        SafeListener safeListener = new SafeListener();
        source.registerListener(safeListener.listener);
        return safeListener;
    }

    void doSomething(Event event) {

    }

    interface EventSource {
        void registerListener(EventListener eventListener);
    }

    interface EventListener {
        void onEvent(Event event);
    }

    interface Event {

    }
}
