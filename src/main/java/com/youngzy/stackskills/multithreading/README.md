# 多线程

进程（Process） VS 线程（Thread）

Program:Process 1:1  
Process:Thread 1:N

|Old-School | New School|
|---|---|
Interface Runnable | Callable 
Class Thead | Executors
Thread.join() | Future.get()

两种启动线程的方法：
方法一：
1. 写一个类 实现 `Runnable` 接口
2. 将类1 作为构造器参数创建一个 `Thread` 对象
3. 调用 `Thread` 对象的 `start()` 方法

方法二：
1. 继承`Thread`类，并覆盖其 `run()` 方法
2. 调用 `Thread` 对象的 `start()` 方法

一般认为，方式一更好，因为它将线程和具体任务分离了。

关键字 `synchronized` 作用的是实例（instance），所以对 `static` 变量无效

| |Old-School | New School|
|---|---|---|
接口| Runnable | Callable |
类| Thread | Executor |
终止线程 | Thread.interrupt() | Future.cancel() |
等待线程结束 | Thread.join() | Future.get() |