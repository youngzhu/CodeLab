# Java Reflection, Annotations and Lambdas

## Lambdas
函数式的编程。可用来替代匿名类或功能性接口（Functional Interface）。

匿名函数。

所谓功能性接口就是指指包含一个方法的接口，如`Comparator`。

链式编程：input -> output
###四要素：

1. stream  
将集合变成一个数据流
1. filter  
input -> output  
按条件对input过滤，满足条件的作为output输出
1. map  
input -> output
(x1, x2, x3) -> (f(x1), f(x2), f(x3))  
改变了原来的值。
1. for each  
NO output stream
