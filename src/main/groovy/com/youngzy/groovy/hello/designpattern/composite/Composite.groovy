package com.youngzy.groovy.hello.designpattern.composite

// 在复合模式（Composite Pattern）下，一组对象与单个对象的多个实例在处理方式上是相同的。
// 该模式常用于对象层级。通常，对于层级中的叶（leaf）或复合（composite）节点，一个或多个方法的调用方式应该是相同的。
// 在这种情况下，复合节点通常会调用它每一个子节点的同一命名方法。

abstract class Component {
    def name
    def toString(indent) {
        ("-" * indent) + name
    }
}

class MyComposite extends Component {
    private children = []
    def toString(indent) {
        def s = super.toString(indent)
        children.each { child ->
            s += '\\n' + child.toString(indent + 1)
        }
        s
    }
    def leftShift(component) {
        children << component
    }
}

class Leaf extends Component { }

def root = new MyComposite(name: "root")
root << new Leaf(name: "leaf A")
def comp = new MyComposite(name: "comp B")
root << comp
root << new Leaf(name: "leaf C")
comp << new Leaf(name: "leaf B1")
comp << new Leaf(name: "leaf B2")

// 为啥不换行呢？？
println root.toString(0)

//assert root.toString(0) ==  '''root
//-leaf A
//-comp B
//--leaf B1
//--leaf B2
//-leaf C'''

