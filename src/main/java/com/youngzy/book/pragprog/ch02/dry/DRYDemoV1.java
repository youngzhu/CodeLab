package com.youngzy.book.pragprog.ch02.dry;

/*
V1
对负数的处理明显重复了。
可以增加一个函数消除这种重复，见V2
 */
public class DRYDemoV1 {
    public void printBalance(Account account) {
        System.out.printf("支出: %10.2f\n", account.debits);
        System.out.printf("收入: %10.2f\n", account.credits);
        if (account.fees < 0)
            /*
            + 带符号输出，默认右对齐
            - 或 +- 左对齐
             */
            System.out.printf("费用: %+10.2f\n", account.fees);
        else
            System.out.printf("费用: %10.2f\n", account.fees);
        /*
        -m.n
        - 表示左对齐
        n>m n个字符完整输出
         */
        System.out.printf("%-1.15s\n", "-------------------");
        if (account.balance < 0)
            System.out.printf("余额: %+10.2f\n", account.balance);
        else
            System.out.printf("余额: %10.2f\n", account.balance);
    }
}
