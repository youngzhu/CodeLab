package com.youngzy.groovy.book.gina2.ch07

def oracle(Object o) { return 'object' }
def oracle(String o) { return 'string' }

// 根据运行时（runtime）实际的类型选择对应的方法
Object x = 1
Object y = 'foo'

assert 'object' == oracle(x)
assert 'string' == oracle(y)                           //#A
//#A This would return object in Java
