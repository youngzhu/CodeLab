package com.youngzy.groovy.hello.designpattern.chainofresponsibility

// 在职责链模式下，有意将使用并实现一个接口（一个或更多的方法）的数个对象松散耦合。一组实现接口的对象按照列表的形式进行组合（或者在极少数情况下按照树的形式）。
// 使用接口的对象向第一个实现器（implementor）对象发出请求。第一个实现器对象会确定是否自己执行任何行为，以及是否将该请求沿列表（或树）传播下去。
// 有时，当实现器对象都没有响应请求时，会在模式中编写对一些请求的默认实现。

class UnixLister {
    private nextInLine
    UnixLister(next) { nextInLine = next }
    def listFiles(dir) {
        if (System.getProperty('os.name') == 'Linux') {
            println "ls $dir".execute().text
        } else {
            nextInLine.listFiles(dir)
        }
    }
}

class WindowsLister {
    private nextInLine
    WindowsLister(next) { nextInLine = next }
    def listFiles(dir) {
        if (System.getProperty('os.name') == 'Windows XP') {
            println "cmd.exe /c dir $dir".execute().text
        } else {
            nextInLine.listFiles(dir)
        }
    }
}

class DefaultLister {
    def listFiles(dir) {
        new File(dir).eachFile { f -> println f }
    }
}

def lister = new UnixLister(new WindowsLister(new DefaultLister()))

//lister.listFiles('Downloads')
lister.listFiles('./')