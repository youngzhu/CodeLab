package com.youngzy.book.pragprog.ch04;

import java.math.BigDecimal;

/*
在实现了新的业务逻辑后，测试似乎没问题。
但上生产以后，程序挂掉了：打开了太多文件

这是因为某些场景下，write方法未被调用，而关闭程序正是在write方法中

紧急处理：Balance3
 */
public class Balance2 {
    CustomerFile customerFile;
    BigDecimal balance;

    void readCustomer() {
        customerFile = CustomerFile.open();
        balance = customerFile.getBalance();
    }

    void writeCustomer() {
        customerFile.updateBalance();
        customerFile.close();
    }

    void updateCustomer(BigDecimal amount) {
        readCustomer();
        if (amount.doubleValue() >= 0) {
            balance = balance.add(amount);
            writeCustomer();
        }
    }

    static class CustomerFile {
        static CustomerFile open() {
            return null;
        }

        BigDecimal getBalance() {
            return null;
        }
        void updateBalance() {}
        void close() {}
    }
}
