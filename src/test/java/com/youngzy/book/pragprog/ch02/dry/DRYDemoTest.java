package com.youngzy.book.pragprog.ch02.dry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DRYDemoTest {

    DRYDemoV4 demo = new DRYDemoV4();

    @Test
    public void printBalanceNegative() {
        System.out.println("\n--负数--");
        Account account = new Account();

        account.debits = 5364235.12;
        account.credits = 356004.44;
        account.fees = -1230.16;
        account.balance = - 569877432.49;

        demo.printBalance(account);
    }

    @Test
    public void printBalancePositive() {
        System.out.println("\n--正数--");
        Account account = new Account();

        account.debits = 534646424235.12;
        account.credits = 356495004.44;
        account.fees = 67.16;
        account.balance = 14564.95;

        demo.printBalance(account);
    }

    @Test
    public void printBalancePositiveAndNegative() {
        System.out.println("\n--一正一负--");
        Account account = new Account();

        account.debits = 534646424235.12;
        account.credits = 356495004.44;
        account.fees = 100;
        account.balance = -12345;

        demo.printBalance(account);
    }
}