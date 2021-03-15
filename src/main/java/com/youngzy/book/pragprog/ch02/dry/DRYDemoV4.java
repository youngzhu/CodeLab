package com.youngzy.book.pragprog.ch02.dry;

/*
V1
对负数的处理明显重复了。
可以增加一个函数消除这种重复，见V2

V2
优点：消除了对负数处理的重复
缺点：在 支出 和 收入 在数字的格式化上还是重复

V3
消除了所有对数字格式化的重复
现在客户要求在文字说明和金额之间多加一个空格，这需要改变4处代码
可以再新增一个方法来消除这种重复，见V4

V4
如果还需要变更金额的格式，只需要修改formatAmount方法
如果变更输出的格式，只需要修改reportLine方法

完美！
不重复的目的就是为了能以尽量少的变动来满足需求的变化。
 */
public class DRYDemoV4 {
    public void printBalance(Account account) {
        reportLine("支出", account.debits);
        reportLine("收入", account.credits);
        reportLine("费用", account.fees);
        printLine( "-------------------", "");
        reportLine("余额", account.balance);
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

    private void printLine(String label, String value) {
        /*
        -m.n
        - 表示左对齐
        n>m n个字符完整输出
         */
        System.out.printf("%-4.15s%s\n", label, value);
    }

    private void reportLine(String label, double amount) {
        printLine(label + ":", formatAmount(amount));
    }
}
