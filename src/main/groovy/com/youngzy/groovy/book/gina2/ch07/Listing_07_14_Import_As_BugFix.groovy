package com.youngzy.groovy.book.gina2.ch07

import com.youngzy.groovy.book.gina2.ch07.thirdparty.MathLib as OrigMathLib

// 给原类起个别名
// 再创建一个同名的类
// 下面使用该类的地方就不用调整
class MathLib extends OrigMathLib {
    Integer twice(Integer value) {
        return value * 2
    }
}

// nothing changes below here                          //#A
def mathlib = new MathLib()

assert 10 == mathlib.twice(5)                          //#B
assert 2 == mathlib.half(5)                            //#C
//#A Usage code for library remains unchanged
//#B Invoke fixed method
//#C Invoke original method
