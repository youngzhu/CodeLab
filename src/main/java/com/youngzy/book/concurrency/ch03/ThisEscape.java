package com.youngzy.book.concurrency.ch03;


/**
 * 3-7 隐式地使 this 引用逸出（不要这么做）
 *
 * 没太看懂。。。
 *
 * 书中的原话是：
 * 当 ThisEscape 发布 EventListener 时，也隐含地发布了
 * ThisEscape 实例本身，因为在这个内部类的实例中包含了对
 * ThisEscape 实例的隐式引用
 *
 */
public class ThisEscape {
    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                doSomething(event);
            }
        });
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
