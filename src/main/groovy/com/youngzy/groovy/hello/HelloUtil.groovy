package com.youngzy.groovy.hello

import java.beans.PropertyChangeListener

// 实用工具

//ConfigSlurper
//ConfigSlurper 是一种能够读取 Groovy 脚本形式配置文件的工具类。
// 就像与 Java 中 *.properties 文件那样，ConfigSlurper 可以使用点标记法。
// 但除此之外，它还能支持闭包范围的配置值以及任意对象类型。
//
//parse 方法可以用来获取 groovy.util.ConfigObject 实例。
// ConfigObject 是 java.util.Map 的一种专门实现，要么返回配置值，要么返回一个新的 ConfigObject 实例，但永远不会返回 null 值。

def config = new ConfigSlurper().parse('''
    app.date = new Date()  //  使用点标记法
    app.age  = 42
    app {                  //使用闭包范围作为点标记法的替代方法
        name = "Test${42}"
    }
''')

assert config.app.date instanceof Date
assert config.app.age == 42
assert config.app.name == 'Test42'
assert config.test != null
assert config.test instanceof ConfigObject

// 当配置变量名包含点时，可以用单引号或双引号对该变量名实施转义处理：
config = new ConfigSlurper().parse('''
    app."person.age"  = 42
''')

assert config.app."person.age" == 42

// ConfigSlurper 还提供了对 environments 的支持。
// environments 方法可以用来移交一个本身含有一些部分的闭包实例。
// 假如我们想要为开发环境创建一个特殊的配置值。在创建 ConfigSlurper 实例时，我们可以使用 ConfigSlurper(String) 构造函数来指定目标环境。
config = new ConfigSlurper('development').parse('''
  environments {
       development {
           app.port = 8080
       }

       test {
           app.port = 8082
       }

       production {
           app.port = 80
       }
  }
''')

assert config.app.port == 8080

//ConfigSlurper 环境并不局限于任何特定的环境名称。它只依赖于其值受支持并被解析的 ConfigSlurper 客户端代码。
//
//environments 方法是内建的，但 registerConditionalBlock 方法可以用来注册除environments 之外的其他方法名。
def slurper = new ConfigSlurper()
slurper.registerConditionalBlock('myProject', 'developers')  // 1⃣️

config = slurper.parse('''
  sendMail = true

  myProject {
       developers {
           sendMail = false
       }
  }
''')

assert !config.sendMail

//为了能与 Java 整合，可以采用 toProperties 方法将 ConfigObject 对象转化为一种可能储存在*.properties 文本文件中的 java.util.Properties 对象。
// 但要留意的是，在将配置值添加到新创建的 Properties 实例时，这些值会被转化 String 实例。
config = new ConfigSlurper().parse('''
    app.date = new Date()
    app.age  = 42
    app {
        name = "Test${42}"
    }
''')

def properties = config.toProperties()

assert properties."app.date" instanceof String
assert properties."app.age" == '42'
assert properties."app.name" == 'Test42'
assert properties.getProperty("app.name") == 'Test42'

//Expando 类
//Expando 类可创建动态可扩展对象。
// 虽然有 Expando 这样的名称，但它却不能使用 ExpandoMetaClass。
// 每一个 Expando 对象都表现为一个单独的、动态生成的实例，可以在运行时利用属性或方法来扩展。
def expando = new Expando()
expando.name = 'John'

assert expando.name == 'John'

// 动态属性注册了一个 Closure 代码块时，会出现一个特例。一经注册，即可调用，就好像是方法调用一般。
expando = new Expando()
expando.toString = { -> 'John' }
expando.say = { String s -> "John says: ${s}" }

assert expando as String == 'John'
assert expando.say('Hi') == 'John says: Hi'

// 可观测的列表、映射与集
//Groovy 提供了可观测的列表、映射与集。
// 对于这其中每一种集合，在添加、删除或改变元素时，都会触发 java.beans.PropertyChangeEvent 事件。
// PropertyChangeEvent 并不仅仅是表示某个特定事件发生的标志，更重要的是，它记录着特定属性的名称以及该属性改变前后的值。
//
//根据发生改变的类型，可观测集合可以发出更多的专门类型的 PropertyChangeEvent 事件。
// 比如，可观测列表上添加元素，就会发出一个 ObservableList.ElementAddedEvent 事件。
def event                                             //声明一个捕获触发事件的 PropertyChangeEventListener。
def listener = {
    if (it instanceof ObservableList.ElementEvent)  {    // ObservableList.ElementEvent 与其子类型都跟该侦听器有关。
        event = it
    }
} as PropertyChangeListener

def observable = [1, 2, 3] as ObservableList       // 注册侦听器。
observable.addPropertyChangeListener(listener)      //为指定列表创建一个 ObservableList。

observable.add 42                                 // 触发 ObservableList.ElementAddedEvent 事件。

assert event instanceof ObservableList.ElementAddedEvent

def elementAddedEvent = event as ObservableList.ElementAddedEvent
assert elementAddedEvent.changeType == ObservableList.ChangeType.ADDED
assert elementAddedEvent.index == 3
assert elementAddedEvent.oldValue == null
assert elementAddedEvent.newValue == 42

// 多个元素被删除时，比如在调用 clear() 时，它保存有将从列表中删除的元素。
observable.clear()

assert event instanceof ObservableList.ElementClearedEvent

def elementClearedEvent = event as ObservableList.ElementClearedEvent
assert elementClearedEvent.values == [1, 2, 3, 42]
assert observable.size() == 0









