设计一个BNF语法来解析时间规范，需要能处理以下所有示例。
4pm, 7:38pm, 23:42, 3:16, 3:16am

````
time    ::= hour ampm | hour : minute appm | hour : minute  
ampm    ::= am | pm
hour    ::= digit | digit digit
minute  ::= digit digit
digit   ::= 0|1|2|3|4|5|6|7|8|9
````

最好能考虑到，hour 只能在00-12之间，minute只能在00-59之间

````
hour    ::= h-tens digit | digit
h-tens  ::= 0|1
minute  ::= m-tens digit
m-tens  ::= 0|1|2|3|4|5
digit   ::= 0|1|2|3|4|5|6|7|8|9
````
- - -
## 小科普
BNF（巴科斯范式，[wiki][]），基本格式为

``
<符号> ::= <使用符号的表达式>
``

这里的 <符号> 是非终结符。  
而表达式是一个符号序列，多个用 ｜ 分隔。每个符号序列整体都是左端符号的一种可能的替代。  
从未在左端出现过的符号叫终结符。

[wiki]: https://zh.wikipedia.org/wiki/%E5%B7%B4%E7%A7%91%E6%96%AF%E8%8C%83%E5%BC%8F