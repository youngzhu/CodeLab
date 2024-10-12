package com.youngzy.groovy.hello.designpattern.adapter

// 适配器模式（有时叫做包装模式）允许对象用在另一类接口。这一模式有两种
// 1. 委托
// 2. 继承

// 假设有以下类
// 可以询问 RoundHole 类是否有 RoundPeg 适合它，但询问 SquarePeg 相同的问题则不会成功，因为 SquarePeg 根本就没有 radius 属性（比如并不满足必须的接口）。
//

class SquarePeg {
    def width
    String toString() {
        "SquarePeg with peg width $width"
    }
}

class RoundPeg {
    def radius
}

class RoundHole {
    def radius
    def pegFits(peg) {
        peg.radius <= radius
    }
    String toString() { "RoundHole with radius $radius" }
}

//要想解决这个问题，就需要创建一个适配器使其具有正确的接口，如下所示：
class SquarePegAdapter {
    def peg
    def getRadius() {
        Math.sqrt(((peg.width / 2) ** 2) * 2)
    }
    String toString() {
        "SquarePegAdapter with peg width $peg.width (and notional radius $radius)"
    }
}

def hole = new RoundHole(radius: 4.0)
(4..7).each { w ->
    def peg = new SquarePegAdapter(peg: new SquarePeg(width: w))
    if (hole.pegFits(peg)) {
        println "peg $peg fits in hole $hole"
    } else {
        println "peg $peg does not fit in hole $hole"
    }
}


// 使用继承
class SquarePegAdapter2 extends SquarePeg {
    def getRadius() {
        Math.sqrt(((width / 2) ** 2) * 2)
    }
    String toString() {
        "SquarePegAdapter with width $width (and notional radius $radius)"
    }
}
(4..7).each { w ->
    def peg = new SquarePegAdapter2(width: w)
    if (hole.pegFits(peg)) {
        println "peg $peg fits in hole $hole"
    } else {
        println "peg $peg does not fit in hole $hole"
    }
}

////使用闭包适配
interface RoundThing {
    def getRadius()
}

//定义一个闭包作为适配器
def adapter = {
    p -> [getRadius: { Math.sqrt(((p.width / 2) ** 2) * 2) }] as RoundThing
}

(4..7).each { w ->
    def peg = new SquarePeg(width: w)
    if (hole.pegFits(adapter(peg))) {
        println "peg $peg fits in hole $hole"
    } else {
        println "peg $peg does not fit in hole $hole"
    }
}

// 使用 ExpandoMetaClass 来适配
(4..7).each { w ->
    def peg = new SquarePeg(width: w)
    peg.metaClass.radius = Math.sqrt(((peg.width / 2) ** 2) * 2)
    if (hole.pegFits(peg)) {
        println "peg $peg fits in hole $hole"
    } else {
        println "peg $peg does not fit in hole $hole"
    }
}



