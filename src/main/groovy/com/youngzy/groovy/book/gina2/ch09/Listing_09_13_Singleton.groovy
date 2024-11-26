package com.youngzy.groovy.book.gina2.ch09

import static groovy.test.GroovyAssert.shouldFail

@Singleton class Zeus { }

assert Zeus.instance
def ex = shouldFail(RuntimeException) { new Zeus() }
// 有包名，就不调整了
//assert ex.message ==
//        "Can't instantiate singleton Zeus. Use Zeus.instance"
