package com.youngzy.groovy.hello

// Eval
// Eval 类方便了简单脚本的求值计算，但并不能超出一定的范围：由于没有脚本缓存，这意味着不能够计算几行代码。
assert Eval.me('33*3') == 99
assert Eval.me('"foo".toUpperCase()') == 'FOO'

assert Eval.x(4, '2*x') == 8 // 带有一个名为 x 的绑定参数的简单计算
assert Eval.me('k', 4, '2*k') == 8 // 带有一个名为 k 的自定义绑定参数的简单计算
assert Eval.xy(4, 5, 'x*y') == 20 // 带有两个名为 x 与 y 的绑定参数的简单计算
assert Eval.xyz(4, 5, 6, 'x*y+z') == 26 // 带有三个绑定参数（x、y 和 z）的简单计算

// GroovyShell
def shell = new GroovyShell()
def result = shell.evaluate '3*5' // 直接执行代码，可被当作 Eval 来使用
def result2 = shell.evaluate(new StringReader('3*5')) // 可从多种数据源读取（String、Reader、File、InputStream）
assert result == result2
def script = shell.parse '3*5' // 延迟代码执行。parse 返回一个 Script 实例
assert script instanceof groovy.lang.Script
assert script.run() == 15

// 报错！不是想象中的脚本啊
//script = shell.parse 'cd'
//script.run()


// 在脚本与程序间共享数据
def sharedData = new Binding()
def shellWithBinding = new GroovyShell(sharedData)
def now = new Date()
// 绑定 数据
sharedData.setProperty('text', 'I am shared data!')
sharedData.setProperty('date', now)

result = shellWithBinding.evaluate('"At $date, $text"')
assert result == "At $now, I am shared data!"

// 也可以从脚本写入绑定对象
shellWithBinding.evaluate('foo=123')
assert sharedData.getProperty('foo') == 123
//这里重要的一点是，如果想写入绑定对象，必须要使用未声明变量。
// 使用 def 或像下例中那样使用 explicit 类型都是错误的，会引起失败，因为这样做的结果等于创建了本地变量
shellWithBinding.evaluate('int bar=123')
try {
    assert sharedData.getProperty('bar')
} catch (MissingPropertyException e) {
    println "bar is defined as a local variable"
}
// 也没有局部变量啊。。
//assert bar == 123








