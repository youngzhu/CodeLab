package com.youngzy.groovy

def list = [5, 6, 7, 8]
assert list.get(2) == 7
assert list[2] == 7
assert list instanceof java.util.List

def emptyList = []
assert emptyList.size() == 0
emptyList.add(5)
assert emptyList.size() == 1

assert [1, 2, 3, 4, 5][-1] == 5             // 使用负数代表从末尾开始计算索引
assert [1, 2, 3, 4, 5][-2] == 4
assert [1, 2, 3, 4, 5].getAt(-2) == 4       // getAt() 允许出现负数索引......
try {
    [1, 2, 3, 4, 5].get(-2)                 // 但负数索引不允许用于 get() 方法
    assert false
} catch (e) {
    assert e instanceof ArrayIndexOutOfBoundsException
}

// 列表中的迭代
//[1, 2, 3].each {
//    println "Item: $it" // `it` 是个很隐含的参数，对应着当前元素
//}
//['a', 'b', 'c'].eachWithIndex { it, i -> // `it` 是当前元素，而 `i` 则是索引
//    println "$i: $it"
//}

// 映射 mapping
assert [1, 2, 3].collect { it * 2 } == [2, 4, 6]

// 代替 `collect` 的快捷格式
assert [1, 2, 3]*.multiply(2) == [1, 2, 3].collect { it.multiply(2) }

def collectList = [0]
// 有可能要给 `collect` 提供收集元素的列表
assert [1, 2, 3].collect(collectList) { it * 2 } == [0, 2, 4, 6]
assert collectList == [0, 2, 4, 6]

/*
过滤与搜索
 */
assert [1, 2, 3].find { it > 1 } == 2           // 查找符合规则的第一个元素
assert [1, 2, 3].findAll { it > 1 } == [2, 3]   // 查找符合规则的所有元素
assert ['a', 'b', 'c', 'd', 'e'].findIndexOf {      // 查找符合规则的第一个元素的索引
    it in ['c', 'e', 'g']
} == 2

assert ['a', 'b', 'c', 'd', 'c'].indexOf('c') == 2  // 返回索引
assert ['a', 'b', 'c', 'd', 'c'].indexOf('z') == -1 // 索引 -1 意指该值并不在列表中
assert ['a', 'b', 'c', 'd', 'c'].lastIndexOf('c') == 4

assert [1, 2, 3].every { it < 5 }               // 如果所有元素都符合断言，则返回 true
assert ![1, 2, 3].every { it < 3 }
assert [1, 2, 3].any { it > 2 }                 // 只要有元素符合断言，就返回 true
assert ![1, 2, 3].any { it > 3 }

// 很抽象啊
// 看着神奇，但是很难想到，也很难理解
assert [1, 2, 3, 4, 5, 6].sum() == 21                // 利用 plus() 方法对任何数值进行加和运算
assert ['a', 'b', 'c', 'd', 'e'].sum {
    it == 'a' ? 1 : it == 'b' ? 2 : it == 'c' ? 3 : it == 'd' ? 4 : it == 'e' ? 5 : 0
    // sum 中使用的自定义值
} == 15
assert ['a', 'b', 'c', 'd', 'e'].sum { ((char) it) - ((char) 'a') } == 10
assert ['a', 'b', 'c', 'd', 'e'].sum() == 'abcde'
assert [['a', 'b'], ['c', 'd']].sum() == ['a', 'b', 'c', 'd']

// 可以提供一个初始化值
assert [].sum(1000) == 1000
assert [1, 2, 3].sum(1000) == 1006

assert [1, 2, 3].join('-') == '1-2-3'           // 字符串连接
assert [1, 2, 3].inject('counting: ') {
    str, item -> str + item                     // 减少操作
} == 'counting: 123'
assert [1, 2, 3].inject(0) { count, item ->
    count + item
} == 6

/*
max & min
 */
def list1 = [9, 4, 2, 10, 5]
assert list1.max() == 10
assert list1.min() == 2

// 跟任何类似的可对比对象一样，我们也可以比较单个的字符
assert ['x', 'y', 'a', 'z'].min() == 'a'

// 可以使用闭包来指定排序行为
def list2 = ['abc', 'z', 'xyzuvw', 'Hello', '321']
assert list2.max { it.size() } == 'xyzuvw'
assert list2.min { it.size() } == 'z'

/*
Comparator
 */
Comparator mc = { a, b -> a == b ? 0 : (a < b ? -1 : 1) }

def list3 = [7, 4, 9, -6, -1, 11, 2, 3, -9, 5, -13]
assert list3.max(mc) == 11
assert list3.min(mc) == -13

Comparator mc2 = { a, b -> a == b ? 0 : (Math.abs(a) < Math.abs(b)) ? -1 : 1 }

assert list3.max(mc2) == -13
assert list3.min(mc2) == -1

assert list3.max { a, b -> a.equals(b) ? 0 : Math.abs(a) < Math.abs(b) ? -1 : 1 } == -13
assert list3.min { a, b -> a.equals(b) ? 0 : Math.abs(a) < Math.abs(b) ? -1 : 1 } == -1

/*
添加或去除元素
 */
def listAdd = []
assert listAdd.empty

listAdd << 5
assert !listAdd.isEmpty()
assert listAdd.size() == 1

listAdd << 7 << 'i' << 11
assert listAdd == [5, 7, 'i', 11]

listAdd << ['m', 'o']
assert listAdd == [5, 7, 'i', 11, ['m', 'o']]

// `<<` 链中的第一项是目标列表  
assert ([1, 2] << 3 << [4, 5] << 6) == [1, 2, 3, [4, 5], 6]

// 使用 `leftShift` 等同于使用 `<<`  
assert ([1, 2, 3].leftShift(4)) == [1, 2, 3, 4]

// ！！！注意：
// 列表中的 + 操作符生成的结果是不可变的。与 << 操作符相比，使用它会创建一个新列表，而这往往不是你想要的结果，容易引起性能问题。
assert [1, 2] + 3 + [4, 5] + 6 == [1, 2, 3, 4, 5, 6]
// 等于调用 `plus` 方法
assert [1, 2].plus(3).plus([4, 5]).plus(6) == [1, 2, 3, 4, 5, 6]

def a = [1, 2, 3]
a += 4      // 创建一个新列表，并以这种方式为 `a` 添加新的元素
a += [5, 6]
assert a == [1, 2, 3, 4, 5, 6]

assert [1, *[222, 333], 456] == [1, 222, 333, 456]
assert [*[1, 2, 3]] == [1, 2, 3]
assert [1, [2, 3, [4, 5], 6], 7, [8, 9]].flatten() == [1, 2, 3, 4, 5, 6, 7, 8, 9]


list = [1, 2]
list.add(3)
list.addAll([5, 4])
assert list == [1, 2, 3, 5, 4]

list = [1, 2]
list.add(1, 3) // 把 3 添加到索引为 1 的元素之前
assert list == [1, 3, 2]

list.addAll(2, [5, 4]) //将 [5,4] 添加到索引为 2 的元素之前
assert list == [1, 3, 5, 4, 2]

list = ['a', 'b', 'z', 'e', 'u', 'v', 'g']
list[8] = 'x' // `[]` 可以当操作符使用，从而按需扩展列表
// 如果需要，也可以在列表中添加 null 值
assert list == ['a', 'b', 'z', 'e', 'u', 'v', 'g', null, 'x']
















