package com.youngzy.book.pragprog.ch02;
/*
要求一次读取文件中的一行，将每一行拆分成多个字段。
以下两个类的定义，哪个更符合正交性？

答：
Split2更正交。它专注于任务，而忽略类数据来自哪里之类的细节。
这不仅使代码更容易开发，而且也更灵活。
它既可以处理文件中的数据，也可以从终端获取。
 */
class Split1 {
    // 打开要读取的文件
    Split1(String fileName) {}
    // 读取下一行
    void readNextLine() {}
    // 返回当前行的第n个字段
    String getField(int n){return "";}
}

class Split2 {
    // 切分一行
    Split2(String line){}
    // 返回当前行的第n个字段
    String getField(int n){return "";}
}