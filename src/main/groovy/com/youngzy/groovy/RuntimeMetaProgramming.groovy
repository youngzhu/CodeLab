package com.youngzy.groovy

import groovy.time.TimeCategory

//// 运行时元编程

// 类别（Categories）
//类别类默认是不能启用的。要想使用定义在类别类中的方法，必须要使用 GDK 所提供的 use 范围方法，并且可用于每一个 Groovy 对象实例内部。
use(TimeCategory)  {
    println 1.minute.from.now   //为 Integer 添加了方法
    println 10.hours.ago

    def someDate = new Date()    //为 Date 添加了方法
    println someDate - 3.months
}

//@Category 标记
//
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

// ExpandoMetaClass
// 方法
class Book {
    String title
}

// << 用于追加（append）一个新的方法。如果方法已经存在，则会抛出一个异常。
// 如果需要替代（replace）一个方法，则需要使用 = 操作符。
Book.metaClass.titleInUpperCase << {-> title.toUpperCase() }

def b = new Book(title:"The Stand")

assert "THE STAND" == b.titleInUpperCase()

// 属性
Book.metaClass.author = "Stephen King"
b = new Book()

assert "Stephen King" == b.author

b.author = "Xiaobo Wang"
assert "Xiaobo Wang" == b.author

// 构造函数
// 构造函数可以通过特殊的 constructor 属性来添加。<< 或 = 操作符都可以用于指定 Closure 代码段。
//在添加构造函数时要格外注意，因为这极易造成栈溢出。
Book.metaClass.constructor << { String title -> new Book(title:title) }

def book = new Book('Groovy in Action - 2nd Edition')
assert book.title == 'Groovy in Action - 2nd Edition'

// 静态方法
//添加静态方法的方法与添加实例方法基本一样，只不过要在方法名前加上 static 修饰符。
Book.metaClass.static.create << { String title -> new Book(title:title) }

b = Book.create("The Stand")
assert b.title == 'The Stand'

// 借用方法
class Chinese {
    String name
}
class MortgageLender {
    def borrowMoney() {
        "buy house"
    }
}

def lender = new MortgageLender()

Chinese.metaClass.buyHouse = lender.&borrowMoney

def p = new Chinese()

assert "buy house" == p.buyHouse()

// 动态方法名
def methodName = "Bob"

Person.metaClass."changeNameTo${methodName}" = {-> delegate.name = "Bob" }

p = new Person(name: "Fred")
assert "Fred" == p.name

p.changeNameToBob()
assert "Bob" == p.name


// 重写 invokeMethod
class Stuff {
    def invokeMe() { "foo" }
    static staticInvokeMe() { "static foo" }
}

Stuff.metaClass.invokeMethod = { String name, args ->
    def metaMethod = Stuff.metaClass.getMetaMethod(name, args)
    def result
    if(metaMethod) {
        result = metaMethod.invoke(delegate,args)
    } else {
        result = "bar"
    }
    result
}

def stf = new Stuff()

assert "foo" == stf.invokeMe()
assert "bar" == stf.doStuff()

// 重写静态 invokeMethod
Stuff.metaClass.static.invokeMethod = { String name, args ->
    def metaMethod = Stuff.metaClass.getStaticMetaMethod(name, args)
    def result
    if(metaMethod) result = metaMethod.invoke(delegate,args)
    else {
        result = "static bar"
    }
    result
}

assert "static foo" == Stuff.staticInvokeMe()
assert "static bar" == Stuff.doStaticStuff()


// 扩展接口
List.metaClass.sizeDoubled = {-> delegate.size() * 2 }

def list = []

list << 1
list << 2

assert 4 == list.sizeDoubled()

//// 扩展模块
// 扩展现有类
//File 类中并不存在 getText 方法，但 Groovy 知道它的定义是在一个特殊类中 ResourceGroovyMethods：
def file = new File('x.txt')
def contents = file.getText('utf-8')
println(contents)

//实例方法
//为现有类添加实例方法，需要创建一个扩展类。比如想在 Integer 上加一个 maxRetries 方法，可以采取下面的方式：
//
// 还必须让 Groovy 能找到该扩展模块，这只需将扩展模块类和描述符放入类路径即可。这意味着有以下两种方法：
//
//1. 直接在类路径上提供类和模块描述符。
//2. 将扩展模块打包为 jar 文件，便于重用。
class MaxRetriesExtension {                                     //扩展类
    static void maxRetries(Integer self, Closure code) {        //静态方法的第一个实际参数对应着消息的接受者，也就是扩展实例。
        int retries = 0
        Throwable e
        while (retries<self) {
            try {
                code.call()
                break
            } catch (Throwable err) {
                e = err
                retries++
            }
        }
        if (retries==0 && e) {
            throw e
        }
    }
}

int i=0
5.maxRetries {
    i++
}
assert i == 1
i=0
try {
    5.maxRetries {
        throw new RuntimeException("oops")
    }
} catch (RuntimeException e) {
    assert i == 5
}

// 静态方法
class StaticStringExtension {      //
    static String greeting(String self) {   //
        'Hello, world!'
    }
}

assert String.greeting() == 'Hello, world!'