package com.youngzy.book.concurrency.ch02;

/**
 * 2-3 延迟初始化中的竞态条件（不要这么做）
 */
public class LazyInitRace {
    private ExpensiveObject instance;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }

        return instance;
    }

}

class ExpensiveObject {}