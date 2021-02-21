package com.youngzy.book.pragprog.ch02.dry;

/*
以下的两个方法并不违背 DRY
虽然代码相同，但表达的意思却不同。
这两个函数校验了两个不想干的东西，只是恰巧使用了相同的规则。
这是一个巧合，而非重复
 */
public class NotDRY {

    /**
     * 校验年龄
     * @param value
     */
    public void validateAge(Object value) {
        // 检查是否是数字
        // 检查值是否大于0
    }

    /**
     * 校验订单数量
     *
     * @param value
     */
    public void validateQuantity(Object value) {
        // 检查是否是数字
        // 检查值是否大于0
    }
}
