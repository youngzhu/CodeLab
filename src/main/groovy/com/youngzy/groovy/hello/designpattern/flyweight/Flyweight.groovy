package com.youngzy.groovy.hello.designpattern.flyweight

// 利用享元模式（Flyweight Pattern）能够极大减少内存需求，因为它可以避免在处理包含很多极其相似部分的系统时，大量地去创建重量级对象。
// 例如，利用一个复杂的字符类来构建文档，该字符类要处理unicode、字体以及定位等情况。文档越大，如果文档中的每一个实际字符都需要一个特有的字符类实例的话，
// 内存需求显然就会越大。相反，由于字符本身可能保存在字符串中，我们可能仅仅会用到单个的了解字符处理规范的字符类（或少量的字符类，比如一个字符类对应一个字体类型）。
//
//在这种情境下，我们把这种由多个事物（比如字符类型）所共享的状态定义为内部（instrinsic）状态。它被重量级类所捕获。
// 区分实际字符（可能只是它的 ASCII 码或 Unicode）的状态被称为外部（extrinsic）状态。

// 如果想打造自己的机队，我们首先可能会去使用这些重量级类的很多实例。
// 但在实际情况下，各架飞机可能只有很小的状态（外部状态）改动，所以我们还是借助重量级对象的单例，分别捕获外部状态即可（购买日期与资产编号，具体可参见下面的代码）：

class FlyweightFactory {
    static instances = [797: new Boeing797(), 380: new Airbus380()]
}

class Aircraft {
    private type         // 型号（内部状态）
    private assetNumber  // 资产编号（外部状态）
    private bought       // 购买日期（外部状态）
    Aircraft(typeCode, assetNumber, bought) {
        type = FlyweightFactory.instances[typeCode]
        this.assetNumber = assetNumber
        this.bought = bought
    }
    def describe() {
        println """
        Asset Number: $assetNumber
        Capacity: $type.capacity people
        Speed: $type.speed
        Range: $type.range
        Bought: $bought
        """
    }
}

def fleet = [
        new Aircraft(380, 1001, '10-May-2007'),
        new Aircraft(380, 1002, '10-Nov-2007'),
        new Aircraft(797, 1003, '10-May-2008'),
        new Aircraft(797, 1004, '10-Nov-2008')
]

fleet.each { p -> p.describe() }







