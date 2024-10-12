package com.youngzy.groovy.hello.designpattern.decorator

// 装饰器模式（Decorator Pattern）可以在不改变一个对象的基本接口的情况装饰其行为。
// 当需要原始对象（未被装饰对象）时，被装饰对象可以被替换。
// 装饰行为通常并不会包含对原始对象源码的修改，装饰器应该能以各种灵活的手段进行组合，从而形成带有多种装饰行为的对象。

class Logger {
    def log(String message) {
        println message
    }
}

// 有时为日志记录添加时间戳是非常有用的，或者可能想改变消息的大小写格式。
// 我们可能会试图将所有的功能都塞进 Logger 类中。这会使得 Logger 类变得十分复杂臃肿。
// 另外，每一个人都将获得所有的功能，即使你不想要其中的某个小子集，也毫无办法。最后，功能交互就会变得极难控制。
//
//为了克服这些缺点，定义两个装饰器类。Logger 类的用途在于可自由地装饰它们的基本记录器，其中可按照任何预想顺序用到多个（或不用）装饰器类。
// 这样的类如下所示：
class TimeStampingLogger extends Logger {
    private Logger logger
    TimeStampingLogger(logger) {
        this.logger = logger
    }
    def log(String message) {
        def now = Calendar.instance
        logger.log("$now.time: $message")
    }
}

class UpperLogger extends Logger {
    private Logger logger
    UpperLogger(logger) {
        this.logger = logger
    }
    def log(String message) {
        logger.log(message.toUpperCase())
    }
}

//
def logger = new UpperLogger(new TimeStampingLogger(new Logger()))
logger.log("G'day Mate") // Sat Oct 12 14:32:23 CST 2024: G'DAY MATE
// 由上可见，我们用了两个装饰器来装饰 logger 的行为。根据我们选用的装饰器使用顺序，日志消息最终结果是全部大写字母，而时间戳则还是正常的格式。
// 如果我们交换顺序，则结果如下：时间戳本身也被改成了大写。
logger = new TimeStampingLogger(new UpperLogger(new Logger()))
logger.log('Hi There') // SAT OCT 12 14:32:23 CST 2024: HI THERE

// 顺序的问题有点绕，要想一想才能明白
// 从外到里
// UpperLogger-TimeStampingLogger: 先把消息大写，再加上时间戳，所以时间戳保持原样（里面有小写）
// TimeStampingLogger-UpperLogger: 先加上时间戳，时间戳+消息 都大写


// 探讨一下动态行为
// 之前的装饰器都是 Logger 类所特有的。我们可以利用 Groovy 的元编程特性创建一个装饰器，使其自然具有更通用的功用，比如像下面这个类：
class GenericLowerDecorator {
    private delegate
    GenericLowerDecorator(delegate) {
        this.delegate = delegate
    }
    def invokeMethod(String name, args) {
        def newargs = args.collect { arg ->
            if (arg instanceof String) {
                return arg.toLowerCase()
            } else {
                return arg
            }
        }
        delegate.invokeMethod(name, newargs)
    }
}

logger = new GenericLowerDecorator(new TimeStampingLogger(new Logger()))
logger.log('IMPORTANT Message') // Sat Oct 12 14:32:23 CST 2024: important message

// 我们可能会需要观察类在一段时间的使用情况。如果它深埋在代码基中，可能很难判断它是何时调用的以及所用的参数。
// 另外，也很难知道它是否成功执行。我们可以轻松地创建一个通用的跟踪装饰器，只要 Calc 类上的任何方法一被调用，就让它打印出跟踪信息，以及提供方法执行的时间信息。
// 下面就是这个跟踪装饰器的代码：
class Calc {
    def add(a, b) { a + b }
}

class TracingDecorator {
    private final delegate
    TracingDecorator(delegate) {
        this.delegate = delegate
    }
    def invokeMethod(String name, args) {
        println "Calling $name $args"
        def before = System.currentTimeMillis()
        def result = delegate.invokeMethod(name, args)
        println "Got $result in ${System.currentTimeMillis()-before} ms"
        result
    }
}

def tracedCalc = new TracingDecorator(new Calc())
assert 15 == tracedCalc.add(3, 12)



