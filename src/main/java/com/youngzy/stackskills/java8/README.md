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

# Interface
接口里定义的变量
```
double PI = 3.14;
```
会自动变成
```
public static final double PI = 3.14;
```

# Inner Class
- Static Inner Class
- Member ~
- Local （在方法内定义的类）
- Anonymous

# 枚举 Enum
- 默认就是 `final` `static`
- 可以当作 `Date` 之类的直接在 类中引用，而不用专门建个文件
- 可以像抽象类一样，有抽象函数

# 集合
- HashSet 无序，TreeSet 有序

