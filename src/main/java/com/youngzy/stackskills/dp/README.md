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

**将一个对象包装起来以增加新的行为和责任**

特点：
- 继承自同一个接口或抽象类
- 对象之间链式调用

例子：Java I/O

练习：把输入流中所有的字母都转成小写

## 03 工厂模式（简单工厂）
将相似对象（如实现了同一个接口）的创建放到一个类里，将"具体实现"从"使用（客户端）"解藕。

如，有两种数据库：MSSQLServer 和 Oracle

多用反射。不用类似key值的传参，是因为它违法了开/闭原则：类应该对扩展开放，对修改关闭。

反射的缺点：
- 复杂
- 效率差。JVM会在编译时对代码做一定优化。而反射是在运行时工作。
- 不安全
- 违法了OO编程抽象的原则（封装性）

## 04 抽象工厂模式
用来创建一群相关的产品（实现不同的接口）

**比较：**
工厂：创建若干个实现了相同接口的类  
抽象工厂：创建若干组相关类（实现了不同的接口），如 数据库连接 和 结果集 都可以由 AbstractDatabaseFactory 创建

## 05 单例模式
一个类只有一个实例，并可全局访问。

## 06 适配器模式
将一个接口转换为另一个接口

例子：将 Enumeration（老的）适配到 Iterator（新的）

## 07 外观模式
将复杂的接口简单化。

如早期的C代码，访问网络很复杂；而现在的代码如Java，使用URL等类就很方便了。

**最少知识（Least Knowledge）原则：只和你的密友交谈。**

## 08 模版模式
定义：在一个类中（通常是抽象类）中定义一个算法骨架，将具体的实现延迟到子类中。

如框架（Spring，Hibernate），可以定制部分操作，但大部分由框架完成。

## 09 迭代模式
将 对集合的遍历 和 集合的实现分开。  
如一个是数组，一个是List。如果没有迭代模式，就需要2个循环遍历数据。

## 10 命令模式
一个类只有一个方法。可以把它当成一个可以像类一样传递的方法。  
典型的应用有：Lambda表达式，Thread类（run），Comparator（compare）

## 11 组合模式
- 是一种结构，可同时包容个别对象和组合对象
- 允许客户把相同的操作应用在个别对象和组合对象上，也就是说可以忽略个别对象和组合对象的差别

## 12 生成器模式
定义：封装一个产品的构造过程，并允许按步骤构造。

优点：
- 隐藏内部实现
- 允许对象通过很多步骤来创建，并可以改变过程（跟工厂模式只有一个步骤不同）

缺点：
- 跟工厂模式相比，客户需要知道更多

特点：
- 构造器私有化

## 13 责任链模式
当需要一个以上的对象去处理某个请求时，就可以使用责任链模式。
例如：邮件分类。你会收到很多邮件（请求），需要不同的过滤器去识别到底是重要邮件，亲友邮件，工作邮件，推广邮件还是垃圾邮件。

## 14 Memento (备忘录模式)
没太看懂。说是为了记录对象的每一个状态，提到类 Undo，logging，还有Java的 Serializable

主要功能：实现恢复能力

## 15 Visitor（访问者模式）
使用场景：当需要为一个对象的组合增加新的行为，且封装并不重要时

## 16 状态模式
对象在不同状态应该有不同的行为

好处：
当有新的状态需要加入时，对象本身（如这里的播放器 MediaPlayer）不需要改变

## 17 Flyweight（蝇量模式）
可以让某一个实例作为许多"虚拟实例"来使用，例如 String 类。  
优点：节省内存  
缺点：单个逻辑实例无法再拥有独立而不同的行为

通过覆盖`equals`方法，忽略某些不重要的属性，使得两个或多个不同的实例，都指向一个唯一的实例。  
好巧，今天刚在leetcode上找了一道设计的题，刚好可以用上。

