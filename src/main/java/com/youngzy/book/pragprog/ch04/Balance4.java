package com.youngzy.book.pragprog.ch04;

import java.math.BigDecimal;

/*
将资源的开与关，放到一个方法里。这就是平衡。
 */
public class Balance4 {

    BigDecimal balance;

    void readCustomer(CustomerFile customerFile) {
        balance = customerFile.getBalance();
    }

    void writeCustomer(CustomerFile customerFile) {
        customerFile.updateBalance();
    }

    void updateCustomer(BigDecimal amount) {
        CustomerFile customerFile = CustomerFile.open();
        readCustomer(customerFile);
        if (amount.doubleValue() >= 0) {
            balance = balance.add(amount);
            writeCustomer(customerFile);
        }

        customerFile.close();
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
