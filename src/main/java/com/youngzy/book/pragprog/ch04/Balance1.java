package com.youngzy.book.pragprog.ch04;

import java.math.BigDecimal;

/*
乍一看，逻辑是合理的：读取记录 - 更新余额 - 将记录写回去
但 read 和 write 方法是紧密耦合在一起的，因为他们共享了实例变量 customerFile

问题在哪？
如果业务逻辑被改：只有更新的值不为负数时才更新余额
代码实现见 Balance2
 */
public class Balance1 {
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
        balance = balance.add(amount);
        writeCustomer();
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
