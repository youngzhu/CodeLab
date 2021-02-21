package com.youngzy.book.pragprog.ch02.dry;

/*
V1
对负数的处理明显重复了。
可以增加一个函数消除这种重复，见V2

V2
优点：消除了对负数处理的重复
缺点：在 支出 和 收入 在数字的格式化上还是重复
 */
public class DRYDemoV2 {
    public void printBalance(Account account) {
        System.out.printf("支出: %10.2f\n", account.debits);
        System.out.printf("收入: %10.2f\n", account.credits);
        System.out.printf("费用: %s\n", formatAmount(account.fees));
        /*
        -m.n
        - 表示左对齐
        n>m n个字符完整输出
         */
        System.out.printf("%-1.15s\n", "-------------------");
        System.out.printf("余额: %s\n", formatAmount(account.balance));
    }

    private String formatAmount(double amount) {
        String result;

        /*
        + 带符号输出，默认右对齐
        - 或 +- 左对齐
         */
        if (amount < 0)
            result = String.format("%+10.2f", amount);
        else
            result = String.format("%10.2f", amount);

        return result;
    }
}
