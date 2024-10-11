package com.youngzy.groovy.hello

import groovy.mock.interceptor.MockFor
import groovy.mock.interceptor.StubFor
import org.junit.Test
import spock.lang.Specification

import static groovy.test.GroovyAssert.shouldFail


class Person6 {
    String first, last
}

class Family {
    Person6 father, mother
    def nameOfMother() { "$mother.first $mother.last" }
}

def mock = new MockFor(Person6)    // 通过 MockFor 的一个新实例创建一个新模拟
mock.demand.getFirst{ 'dummy' }
mock.demand.getLast{ 'name' }
mock.use {                        // Closure 被传入 use，启用模拟功能
    def mary = new Person6(first:'Mary', last:'Smith')
    def f = new Family(mother:mary)
    assert f.nameOfMother() == 'dummy name'
}
mock.expect.verify() // 调用 verify 查看是否序列和方法调用数正如预期

// StubFor
def stub = new StubFor(Person6)
stub.demand.with {                    // 使用 with 方法将所有闭包中的调用委托给 StubFor 实例
    getLast{ 'name' }
    getFirst{ 'dummy' }
}
stub.use {
    def john = new Person6(first:'John', last:'Smith')
    def f = new Family(father:john)
    assert f.father.first == 'dummy'
    assert f.father.last == 'name'
}
stub.expect.verify()                //调用 verify（可选的）检查是否调用数目符合预期

// ExpandoMetaClass
// 不限于自定义类，JDK中的类也可以
String.metaClass.swapCase = {->
    def sb = new StringBuffer()
    delegate.each {
        sb << (Character.isUpperCase(it as char) ? Character.toLowerCase(it as char) :
                Character.toUpperCase(it as char))
    }
    sb.toString()
}

def s = "heLLo, worLD!"
assert s.swapCase() == 'HEllO, WORld!'

// 模拟静态方法
class Book2 {
    String title
}

Book2.metaClass.static.create << { String title -> new Book2(title:title) }

def b = Book2.create("The Stand")
assert b.title == 'The Stand'

// 构造函数
Book2.metaClass.constructor << { String title -> new Book2(title:title) }

b = new Book2("The Stand")
assert b.title == 'The Stand'

//除了使用类级别的 ExpandoMetaClass，也支持使用元类或对象级别。
// 在该例中，元类更改只与实例有关，根据测试情境，这可能要比全局元类更改适应性更好。
b.metaClass.getTitle {-> 'My Title' }
assert b.title == 'My Title'

//Iterable##combinations
//可以从一个包含两个或更多子列表的列表中获得一个组合列表：
//该方法可以在测试用例情境下，针对特定的方法调用，生成所有可能的参数组合。
void testCombinations() {
    def combinations = [[2, 3],[4, 5, 6]].combinations()
    assert combinations == [[2, 4], [3, 4], [2, 5], [3, 5], [2, 6], [3, 6]]
}
//testCombinations()

// Iterable##eachCombination 它会在输入列表的每一个组合上应用一个函数：
void testEachCombination() {
    [[2, 3],[4, 5, 6]].eachCombination { println it[0] + it[1] }
}
//testEachCombination()

////JUnit4
class JUnit4ExampleTests {

    @Test
    void indexOutOfBoundsAccess() {
        def numbers = [1,2,3,4]
        shouldFail {
            numbers.get(4)
        }
    }

    @Test
    void shouldFailReturn() {
        def e = shouldFail {
            throw new RuntimeException('foo',
                    new RuntimeException('bar'))
        }
        assert e instanceof RuntimeException
        assert e.message == 'foo'
        assert e.cause.message == 'bar'
    }

}

//// 利用 Spock 测试
// 看不懂！明显的错误也不提示啊
class StackSpec extends Specification {

    def "adding an element leads to size increase"() {    // 功能方法，按照归约，以字符串字面量形式命名。
        // setup 是可选的
        setup: "a new stack instance is created"       // 设置块，包含用于功能所需完成工作的所有设置内容。
        def stack = new Stack()

        when:                                           // When 语句块描述了一种刺激性条件，由功能规范所确定的一种目标特定行为。
        stack.push 42

        then:                                           // Then 语句块包含的表达式能够验证由 When 语句块触发的代码结果。
        stack.size() == 11
        stack.peek() == 1
    }

    def "中文OK不？"() {
        def stack = new Stack()

        when:
        stack.push 42

        then:
        stack.size() == 11
    }
}