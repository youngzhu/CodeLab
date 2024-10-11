package com.youngzy.groovy

import groovy.transform.Memoized
import groovy.transform.ToString
import groovy.transform.TupleConstructor
import groovy.transform.builder.*

//// 编译时元编程
//如果用运行时元编程来实现的话，新方法只能可见于 Groovy；而用编译时元编程来实现，新方法也可以在 Java 中显现出来。
// 同时，编译时元编程的性能要好过运行时元编程（因为不再需要初始化过程）。

// AST
// 抽象语法树（AST，Abstract Syntax Tree）
// 可用的 AST 转换
// AST 转换可分为两大类：
//
// 1. 全局 AST 转换。它们的应用是透明的，具有全局性，只要能在类路径上找到它们，就可以使用它们。
// 2. 本地 AST 转换。利用标记来注解源代码。与全局 AST 转换不同，本地 AST 转换可能支持形式参数。
//Groovy 并不带有任何的全局 AST 转换，但你可以在这里找到一些可用的本地 AST 转换：

// 代码生成转换
//这一类转换包含能够去除样板文件代码的 AST 转换。
// 样板文件代码通常是一种必须编写然而又没有任何有用信息的代码。通过自动生成这种样板文件代码，剩下必须要写的代码就变得清晰而简洁起来，从而就减少了因为样板文件代码不正确而引入的错误。
//
//@groovy.transform.ToString
//@ToString AST 转换能够生成人类可读的类的 toString 形式。比如，像下面这样注解 Person 类会自动为你生成 toString 方法。
@ToString(includePackage = false)
class Person2 {
    String firstName
    String lastName
    int age
}

def p = new Person2(firstName: 'Jack', lastName: 'Nicholson', age: 30)
// 默认有包名
//assert p.toString() == 'com.youngzy.groovy.Person2(Jack, Nicholson, 30)'
assert p.toString() == 'Person2(Jack, Nicholson, 30)'


// @groovy.transform.TupleConstructor
@TupleConstructor
class Person3 {
    String firstName
    String lastName
}

// 传统的映射样式的构造函数
def p1 = new Person3(firstName: 'Jack', lastName: 'Nicholson')
// 生成的元组构造函数
def p2 = new Person3('Jack', 'Nicholson')
// 生成的元组构造函数，带有第二个属性的默认值
def p3 = new Person3('Jack')

////@groovy.transform.builder.Builder
// 默认使用 set
@Builder(builderStrategy=SimpleStrategy)
class Person4 {
    String first
    String last
    Integer born
}
p1 = new Person4().setFirst('Johnny').setLast('Depp').setBorn(1963)
assert "$p1.first $p1.last" == 'Johnny Depp'

// 指定一个前缀：
@Builder(builderStrategy=SimpleStrategy, prefix="")
class Person5 {
    String first
    String last
    Integer born
}
// 没有 set 了
p = new Person5().first('Johnny').last('Depp').born(1963)
assert "$p.first $p.last" == 'Johnny Depp'

// ExternalStrategy 需要额外一个Builder类
@Builder(builderStrategy=ExternalStrategy, forClass=Person5)
class PersonBuilder { }

p = new PersonBuilder().first('Johnny').last('Depp').born(1963).build()
assert "$p.first $p.last" == 'Johnny Depp'

// 类设计注释
//@groovy.lang.Delegate
//利用 @Delegate 注释 when 字段，意味着 Event 类将把对 Date 方法的所有调用都委托给 when 字段。
class Event {
    @Delegate
    Date when
    String title
}
// 然后就可以直接在 Event 类中调用before 方法了：
def ev = new Event(title:'Groovy keynote', when: new Date(2024-1900, 9, 1))
//println(ev.when)
def now = new Date()
assert ev.before(now)

// @groovy.transform.Memoized
long longComputation(int seed) {
    // 延缓计算
    Thread.sleep(1000*seed)
    System.nanoTime()
}
def x = longComputation(1)
def y = longComputation(1)
assert x!=y

//
@Memoized
long longComputation2(int seed) {
    // 延缓计算
    Thread.sleep(1000*seed)
    System.nanoTime()
}

x = longComputation2(1) // 1 秒后返回结果
y = longComputation2(1) // 立刻返回结果
def z = longComputation2(2) // 2 秒后返回结果
assert x==y
assert x!=z

//缓存的大小可以通过 2 个可选参数来配置：
//
//protectedCacheSize 结果数目，这些结果不会被垃圾回收。
//maxCacheSize 存入内存中的最大结果数目。
//默认情况下，缓存数目并没有限制，并且没有缓存结果能够避免被垃圾回收。
// protectedCacheSize>0 将会创建一个无限制的缓存，其中一些结果能够避免被回收。
// 若设置 maxCacheSize>0，则将创建一个受限的缓存，无法避免被垃圾回收。
// 将两个参数都进行设置，则可以创建一种受限而又受保护的缓存。


////@groovy.lang.Singleton
@Singleton
class GreetingService {
    String greeting(String name) { "Hello, $name!" }
}
assert GreetingService.instance.greeting('Bob') == 'Hello, Bob!'

@Singleton(property='theOnlyOne')
class GreetingService2 {
    String greeting(String name) { "Hello, $name!!" }
}

assert GreetingService2.theOnlyOne.greeting('Bob') == 'Hello, Bob!!'

//
class Collaborator {
    public static boolean init = false
}
@Singleton(lazy=true,strict=false)
class GreetingService3 {
    static void init() {}
    GreetingService3() {
        Collaborator.init = true
    }
    String greeting(String name) { "Hello, $name!!!" }
}
GreetingService3.init() // 确保类被初始化
assert Collaborator.init == false
GreetingService3.instance
assert Collaborator.init == true

////开发 AST 转换
//局部转换
//使用局部 AST 转换就能轻松实现，它需要两个要素：
//
// - @WithLogging 注释的定义。
// - org.codehaus.groovy.transform.ASTTransformation的实现，将日志表达式添加到方法上。
@WithLogging
def greet() {
    println "Hello World"
}

greet()

//全局转换
//全局 AST 转换跟局部转换的一个重大区别在于：不需要注释，这意味着它们是全局应用的，也就是要应用到每个被编译的类上。
// 由于全局转换会对编译器效率产生极大影响，所以一定要把它们最后的手段，不到万不得已不可使用。
//
//实现这一点需要完成以下两步工作：
//
// 1. 在 META-INF/services 目录中创建 org.codehaus.groovy.transform.ASTTransformation 描述符。
// 2. 创建 ASTTransformation 实现。
//
// 与局部的相比：转换代码看起来与局部用例没什么区别，但我们使用的是 SourceUnit，而不是 ASTNode[] 参数
// 影响全局，所以放在 ASTTransformationGlobal.groovy 里验证



