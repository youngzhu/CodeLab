package com.youngzy.book.pragprog.ch02.dry;

/*
注释的重复

fees 函数的意图被描述了两次：注释中一次，代码中一次。
一旦规则有所调整，就必须更新两个地方。
很快，注释和代码就会变得不同步

这一点，本人持保留态度。加点注释会更好理解。
 */
public class CommentDRY {
    /**
     * 计算这个账户的费用
     *
     * 每张退票算 20
     * 如果账户透支超过3天，每天产生的费用 10
     * 如果账户余额超过 2000，减少 50% 的费用
     *
     * @param account
     * @return
     */
    public double fees(Account account) {
        double fees = 0;

        if (account.returnedCheckCount > 0)
            fees += 20 * account.returnedCheckCount;
        if (account.overdraftDays > 3)
            fees += 10 * account.overdraftDays;
        if (account.balance > 2_000)
            fees /= 2;

        return fees;
    }

    public double calculateAccountFees(Account account) {
        double fees = 0;

        fees += 20 * account.returnedCheckCount;
        if (account.overdraftDays > 3)
            fees += 10 * account.overdraftDays;
        if (account.balance > 2_000)
            fees /= 2;

        return fees;
    }
}
