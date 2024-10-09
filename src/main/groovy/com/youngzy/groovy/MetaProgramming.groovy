package com.youngzy.groovy

import groovy.time.TimeCategory

// 类别（Categories）
//类别类默认是不能启用的。要想使用定义在类别类中的方法，必须要使用 GDK 所提供的 use 范围方法，并且可用于每一个 Groovy 对象实例内部。
use(TimeCategory)  {
    println 1.minute.from.now   //为 Integer 添加了方法
    println 10.hours.ago

    def someDate = new Date()    //为 Date 添加了方法
    println someDate - 3.months
}

//@Category 标记
class Distance {
    def number
    String toString() { "${number}m" }
}

@Category(Number)
class NumberCategory {
    Distance getMeters() {
        new Distance(number: this)
    }
}

use (NumberCategory)  {
    assert 42.meters.toString() == '42m'
}