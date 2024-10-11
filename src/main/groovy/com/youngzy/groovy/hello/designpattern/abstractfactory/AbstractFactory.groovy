package com.youngzy.groovy.hello.designpattern.abstractfactory

//抽象工厂模式能将一组有共同主题的独立工厂封装起来。它将普通工厂的意图具体化，例如：不必再利用代码通过使用接口来了解接口背后的具体实施，
// 而应用于一组接口，并且选择一整套能够实现这些接口的具体类。
//
//考虑这样一个范例，其中有接口 Button、TextField 和 Scrollbar。将 WindowsButton、MacButton、FlashButton 作为 Button 的具体类。
// WindowsScrollBar、MacScrollBar 和 FlashScrollBar 作为 ScrollBar 的具体实现。
// 抽象工厂模式应能让我选择某次想要用到的具体窗口系统（比如 Windows、Mac、Flash），并从此可以编写代码引用这些接口，但能一直在后台使用正确的具体类（来自某个窗口系统）


// 工厂代码
def guessFactory = [messages: GuessGameMessages, control: GuessGameControl, converter: GuessGameInputConverter]
def twoupFactory = [messages: TwoupMessages, control: TwoupControl, converter: TwoupInputConverter]

class GameFactory {
    def static factory
    def static getMessages() { return factory.messages.newInstance() }
    def static getControl() { return factory.control.newInstance() }
    def static getConverter() { return factory.converter.newInstance() }
}

// 对工厂的使用
//GameFactory.factory = twoupFactory
GameFactory.factory = guessFactory
def messages = GameFactory.messages
def control = GameFactory.control
def converter = GameFactory.converter
println messages.welcome
def reader = new BufferedReader(new InputStreamReader(System.in))
while (control.moreTurns()) {
    def input = reader.readLine().trim()
    control.play(converter.convert(input))
}
println messages.done