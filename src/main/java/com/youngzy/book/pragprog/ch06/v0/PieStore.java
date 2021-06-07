package com.youngzy.book.pragprog.ch06.v0;

import java.util.concurrent.*;

/**
 * 线程不安全
 *
 * 只有一个派，却卖出去两个
 */
public class PieStore {
    public static void main(String[] args) throws Exception {
        DisplayCase displayCase = new DisplayCase(1);
        Customer c1 = new Customer();
        Customer c2 = new Customer();

        Waiter w1 = new Waiter(1, displayCase, c1);
        Waiter w2 = new Waiter(2, displayCase, c2);

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future1 = executorService.submit(w1);
        Future future2 = executorService.submit(w2);

        future1.get();
        future2.get(); // get 可以保证线程执行完

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

    public void takePie() {
        pieCount --;
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

    private boolean isPieAvailable() {
        boolean result = displayCase.pieCount > 0;
        String msg = getId() + "瞧了一眼，";
        delay(10);
        if (result) {
            System.out.println(msg + "有");
        } else {
            System.out.println(msg + "没有了");
        }
        return result;
    }

    private void takePie() {
        delay(100);
        displayCase.takePie();
        // 故意延时 测试并发
        delay(200);
    }

    private String getId() {
        return "#" + no + ": ";
    }

    private void givePieToCustomer() {
        System.out.println(getId() + "请用餐");
        SOLD_COUNT++;
    }

    private void promisePieToCustomer() {
        System.out.println(getId() + "好的，请稍等");
    }

    private void apologizes() {
        System.out.println(getId() + "对不起，已经卖完了");
    }

    private void delay(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

// 顾客
class Customer {

}