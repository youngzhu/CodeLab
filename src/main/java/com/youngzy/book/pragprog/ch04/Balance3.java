package com.youngzy.book.pragprog.ch04;

import java.math.BigDecimal;

/*
貌似解决了问题。但实际情况更糟：三个方法都因为共享了 customerFile 产生了耦合。

正确的代码（重构后）：Balance4
 */
public class Balance3 {
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
        } else {
            customerFile.close();
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
