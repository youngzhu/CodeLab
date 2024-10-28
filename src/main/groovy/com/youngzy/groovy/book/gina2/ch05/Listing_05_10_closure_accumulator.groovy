package com.youngzy.groovy.book.gina2.ch05

def foo(n) {
    return { n += it }
}

// We want to write a function that generates accumulators — a function
// that takes a number n, and returns a function that takes another number i and returns n incremented by i
def accumulator = foo(1)
// 计算后，n值已改变
assert accumulator(1) == 2
assert accumulator(1) == 3
assert accumulator(2) == 5
assert accumulator(1) == 6