package com.youngzy.groovy.book.gina2.ch03

import groovy.transform.Immutable

@Immutable                                             //Overrides == operator
class Money {
    int     amount
    String  currency

    Money plus (Money other) {                         //Implements + operator
        if (null == other) return this
        if (other.currency != currency) {
            throw new IllegalArgumentException(
                    "cannot add $other.currency to $currency")
        }
        return new Money(amount + other.amount, currency)
    }

    // 不能直接“一般化”， amount的类型也要改
    Money plus (Integer more) {                         //Implements + operator
//    Money plus (BigDecimal more) {                         //Implements + operator
        return new Money(amount + more, currency)
    }
}
