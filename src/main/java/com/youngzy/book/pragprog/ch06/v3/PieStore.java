package com.youngzy.book.pragprog.ch06.v3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 所以从源头上控制更好，即资源的事务性
 */
public class PieStore {
    public static void main(String[] args) throws Exception {
        DisplayCase displayCase = new DisplayCase(1);
        Customer c1 = new Customer();
        Customer c2 = new Customer();

        Waiter w1 = new Waiter(1, displayCase, c1);
//        Waiter w2 = new Waiter(2, displayCase, c2, lock);
        Waiter w2 = new Trainee(3, displayCase, c2);

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future2 = executorService.submit(w2);
        Future future1 = executorService.submit(w1);

        future2.get(); // get 可以保证线程执行完
        future1.get();

        executorService.shutdown(); // 要等待结果，这个方法不行

        // 要保证这一句最后执行
        System.out.println("共卖出：" + Waiter.SOLD_COUNT);
    }
}

// 陈列柜
class DisplayCase {
    int pieCount;

    public DisplayCase(int pieCount) {
        this.pieCount = pieCount;
    }

//    public void takePie() {
//        pieCount --;
//    }

    public boolean getPieIfAvailable() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            if (pieCount > 0) {
                pieCount--;
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }
}

// 服务员
class Waiter implements Runnable {
    static int SOLD_COUNT;

    int no;
    DisplayCase displayCase;
    Customer customer;

    public Waiter(int no, DisplayCase displayCase, Customer customer) {
        this.no = no;
        this.displayCase = displayCase;
        this.customer = customer;
    }

    public void run() {

        if (isPieAvailable()) {
            promisePieToCustomer();
            takePie();
            givePieToCustomer();
        } else {
            apologizes();
        }

    }

    protected boolean isPieAvailable() {
        boolean result = displayCase.getPieIfAvailable();
        String msg = getId() + "瞧了一眼，";
        delay(10);
        if (result) {
            System.out.println(msg + "有");
        } else {
            System.out.println(msg + "没有了");
        }
        return result;
    }

    protected void takePie() {
        delay(100);
//        displayCase.takePie();
        // 故意延时 测试并发
        delay(200);
    }

    protected String getId() {
        return "#" + no + ": ";
    }

    protected void givePieToCustomer() {
        System.out.println(getId() + "请用餐");
        SOLD_COUNT++;
    }

    protected void promisePieToCustomer() {
        System.out.println(getId() + "好的，请稍等");
    }

    protected void apologizes() {
        System.out.println(getId() + "对不起，已经卖完了");
    }

    protected void delay(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

// 实习生
class Trainee extends Waiter {

    public Trainee(int no, DisplayCase displayCase, Customer customer) {
        super(no, displayCase, customer);
    }

    @Override
    public void run() {
        // 忘记了锁
        if (isPieAvailable()) {
            promisePieToCustomer();
            takePie();
            givePieToCustomer();
        } else {
            apologizes();
        }
    }
}

// 顾客
class Customer {

}