package com.youngzy.groovy.book.gina2.ch07

import com.youngzy.groovy.book.gina2.ch07.thirdparty.MathLib as TwiceHalfMathLib
import com.youngzy.groovy.book.gina2.ch07.thirdparty2.MathLib as IncMathLib

// 起别名，解决类名冲突问题
def math1 = new TwiceHalfMathLib()
def math2 = new IncMathLib()

assert 3 == math1.half(math2.increment(5))