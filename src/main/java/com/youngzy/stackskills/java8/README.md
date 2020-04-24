# 开始
- 一个Java文件可以有多个class。但 public class 最多一个，且类名跟文件名必须一样。
- 每个class都可以有main方法，都可以执行

# 基本类型
Java8中在数字里可以加 _ 便于人识别，如 1_000 
- 不能以_开头
- 也不能结尾
- 一个和多个没区别： _ vs __

## 数组
初始化
````
int[] arr = {1, 2, 3}
int[][] arr2 = {
{1, 2},
{1, 4}
};
````

#变量 Variables
- Local Variable
只在相应方法中有效
- Instance Variable
各个实例之间是独立的
- Static Variable
一个class只有一个值，即所有实例共享

# 方法
````
void add(int... args) {
    for (int i : args) {
        ...
    }
}
````
