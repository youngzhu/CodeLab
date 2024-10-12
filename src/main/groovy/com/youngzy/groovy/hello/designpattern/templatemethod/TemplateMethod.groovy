package com.youngzy.groovy.hello.designpattern.templatemethod

// 模板方法模式 抽象出一些算法的细节。算法的通用部分都包含在一个基本类中。特定实现细节也就位于这些基本类中。

abstract class Accumulator {
    protected initial
    abstract doAccumulate(total, v)
    def accumulate(values) {
        def total = initial
        values.each { v -> total = doAccumulate(total, v) }
        total
    }
}

class Sum extends Accumulator {
    def Sum() { initial = 0 }
    def doAccumulate(total, v) { total + v }
}

class Product extends Accumulator {
    def Product() { initial = 1 }
    def doAccumulate(total, v) { total * v }
}

assert 10 == new Sum().accumulate([1,2,3,4])
assert 24 == new Product().accumulate([1,2,3,4])

// 在这种特殊的情况下，可以使用 Groovy 的注入方法来实现相似的结果（使用闭包）：
Closure addAll = { total, item -> total += item }
def accumulated = [1, 2, 3, 4].inject(0, addAll)
assert 10 == accumulated    // => 10

accumulated = [ "1", "2", "3", "4" ].inject("", addAll)
assert "1234" == accumulated    // => "1234"

Closure multAll = { total, item -> total *= item }
accumulated = [1, 2, 3, 4].inject(1, multAll)
assert 24 == accumulated    // => 24

