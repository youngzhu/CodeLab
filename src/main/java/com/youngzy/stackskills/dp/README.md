# Design Patterns

三大类：
## Behavioural 跟外部的交互
- Strategy
- Iterator
- Template
- Command
- Memento
- Visitor
- State
- Mediator
- Observer

## Structural 同一个逻辑单元内不同类之间的关系
- Decorator
- Adapter
- Facade
- Composite
- Flyweight
- Bridge
- Proxy

## Creational
- Factory & Abstract Factory
- Singleton
- Builder
- Prototype

## Design Principles
1. Rely on Interface, not Implementation
    **NO**
    ````
   public ArrayList<Integer> getList() {
        return new ArrayList<Integer>();
   }
   ````
   **YES**
    ````
   public List<Integer> getList() {
        return new ArrayList<Integer>();
   }
   ````
2. The Open/Closed Principle
    > Classes should be open for extension, but closed for modification.

    - Inheritance (Template Pattern)
    - Delegation (Observer)
    - Composition (Strategy)
   
3. Least Knowledge
    > Only talk to friends, don't talk to strangers

4. Dependency Inversion
    > Depend on abstractions, never on details.

5. The Hollywood Principle   
    > Don't call us, we'll call you. 

## 01 策略模式
> 策略模式属于对象的行为模式。其用意是针对一组算法，将每一个算法封装到具有共同接口的独立的类中，从而使得它们可以相互替换。策略模式使得算法可以在不影响到客户端的情况下发生变化。
> 《JAVA与模式》

- 定义了一族算法（业务规则）
- 封装了每个算法
- 这族的算法可互换代替（interchangeable）

via [维基百科]( https://zh.wikipedia.org/wiki/%E7%AD%96%E7%95%A5%E6%A8%A1%E5%BC%8F)

假设，有这么个需求场景：对编程语言进行排序，规则包括但不限于：按各社区的活跃度、按部署的应用服务器数量、按GitHub上的代码量、按搜索热度……

这些排序方法有点复杂，为了方便举例，定义几个简单的排序规则：
- 按字母顺序
- 如果语言中包含 Java ，则排第一，其余按字母排序，区分大小写；
- 如果语言中包含 Java ，则排第一，其余按字母排序，不区分大小写。

这些不同的排序方式就是策略。

**Spring 的依赖注入就是 策略模式**

## 02 装饰者模式 Decorator(结合Head First)
>定义（Head First）：动态地将责任附加到对象上。若要扩展功能，装饰者提供了比继承更有弹性的替代方案。

特点：
- 继承自同一个接口或抽象类
- 对象之间链式调用

例子：Java I/O

练习：把输入流中所有的字母都转成小写