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

//def a = [1, 2, 3]
//a += 4      // 创建一个新列表，并以这种方式为 `a` 添加新的元素
//a += [5, 6]
//assert a == [1, 2, 3, 4, 5, 6]

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

/*
删除
 */
assert ['a','b','c','b','b'] - 'c' == ['a','b','b','b']
assert ['a','b','c','b','b'] - 'b' == ['a','c']
assert ['a','b','c','b','b'] - ['b','c'] == ['a']

list = [1,2,3,4,3,2,1]
list -= 3           // 从原始列表中去除 `3` 创建一个新列表
assert list == [1,2,4,2,1]
assert ( list -= [2,4] ) == [1,1]

list = [1,2,3,4,5,6,2,2,1]
assert list.remove(2) == 3          // 去除第 3 个元素，然后返回该元素
assert list == [1,2,4,5,6,2,2,1]

// 如果你只想去除列表中第一个跟值相同的元素，而不是所有元素的话，可以使用 remove 方法
list= ['a','b','c','b','b']
assert list.remove('c')             // 去除 'c' 并返回 true，因为元素已经清除了
assert list.remove('b')             // 去除第一个 'b' 并返回 true，因为元素已经清除了

assert ! list.remove('z')           // 返回 false，因为没有这个元素可供清除
assert list == ['a','b','b']

list= ['a',2,'c',4]
// 如果是数字就会被当做 index
list.remove(2)
assert list == ['a',2,4]
list.clear()
assert list == []

/*
 集合上的操作
 */
assert 'a' in ['a','b','c']             // 如果某一元素属于该列表，则返回 true
assert ['a','b','c'].contains('a')      // 等同于 Java 中的 `contains` 方法
assert [1,3,4].containsAll([1,4])       // `containsAll` 将检查是否已经找到所有的元素

assert [1,2,3,3,3,3,4,5].count(3) == 4  // 计算匹配相应值的元素的数目
assert [1,2,3,3,3,3,4,5].count {
    it%2==0                             // 计算符合谓语要求的元素数目
} == 2

// 交集
assert [1,2,4,6,8,10,12,6].intersect([1,3,6,9,6,12]) == [1,6,6,12]

// 没有交集
assert [1,2,3].disjoint( [4,6,9] )
assert ![1,2,3].disjoint( [2,4,6] )

/*
排序
 */
assert [6, 3, 9, 2, 7, 1, 5].sort() == [1, 2, 3, 5, 6, 7, 9]

list = ['abc', 'z', 'xyzuvw', 'Hello', '321']
assert list.sort {
    it.size()
} == ['z', 'abc', '321', 'Hello', 'xyzuvw']

def listSort = [7, 4, -6, -1, 11, 2, 3, -9, 5, -13]
assert listSort.sort {
    a, b -> a == b ? 0 : Math.abs(a) < Math.abs(b) ? -1 : 1
} == [-1, 2, 3, 4, 5, -6, 7, -9, 11, -13]

mc = { a, b -> a == b ? 0 : Math.abs(a) < Math.abs(b) ? -1 : 1 }
// 只适用于 JDK 8+
listSort.sort(mc)
assert listSort == [-1, 2, 3, 4, 5, -6, 7, -9, 11, -13]

list3 = [6, -3, 9, 2, -7, 1, 5]

Collections.sort(list3)
assert list3 == [-7, -3, 1, 2, 5, 6, 9]

Collections.sort(list3, mc)
assert list3 == [1, 2, -3, 5, 6, -7, 9]

/*
 复制
 */
assert [1, 2, 3] * 3 == [1, 2, 3, 1, 2, 3, 1, 2, 3]
assert [1, 2, 3].multiply(2) == [1, 2, 3, 1, 2, 3]
assert Collections.nCopies(3, 'b') == ['b', 'b', 'b']

// JDK 的 nCopies 的语义跟列表所用的 multiply 截然不同
assert Collections.nCopies(2, [1, 2]) == [[1, 2], [1, 2]] // 而不是 [1,2,1,2]


/*
 map
 */
def map = [name: 'Gromit', likes: 'cheese', id: 1234]
assert map.get('name') == 'Gromit'
assert map.get('id') == 1234
assert map['name'] == 'Gromit'
assert map['id'] == 1234
assert map instanceof java.util.Map

def emptyMap = [:]
assert emptyMap.size() == 0
emptyMap.put("foo", 5)
assert emptyMap.size() == 1
assert emptyMap.get("foo") == 5

// Map 的键默认都是字符串：[a:1] 和 ['a':1] 是相等的。
def a = 'Bob'
def ages = [a: 43]
assert ages['Bob'] == null // 没有找到 `Bob`
assert ages['a'] == 43     // 因为 `a` 是一个字面量！

ages = [(a): 43]            // 现在用括号把 `a` 转义
assert ages['Bob'] == 43   // 就找到 `Bob` 了！

/*
 对 map 的迭代
 */
//值得注意的是，使用 map 字面量标记定义的 map 都是有序的。也就是说，如果你迭代 map 中的元素，元素项绝对会按照它们添加进 map 的顺序返回。
map = [
        Bob  : 42,
        Alice: 54,
        Max  : 33
]

// `entry` 是一个 map 项
//map.each { entry ->
//    println "Name: $entry.key Age: $entry.value"
//}

// `entry` 是一个 map 项，`i` 是 map 中的索引
//map.eachWithIndex { entry, i ->
//    println "$i - Name: $entry.key Age: $entry.value"
//}

// 当然你还可以直接利用键与值来进行迭代
//map.each { key, value ->
//    println "Name: $key Age: $value"
//}

// Key、value 和 i 都可以作为 map 中的索引
//map.eachWithIndex { key, value, i ->
//    println "$i - Name: $key Age: $value"
//}

//另外值得一提的是，你永远不应使用 GString 作为 map 的键，因为 GString 的哈希值跟相应的 String 的哈希值是不同的。
def key = 'some key'
map = [:]
def gstringKey = "${key.toUpperCase()}"
map.put(gstringKey,'value')
assert map.get('SOME KEY') == null
assert map.get("SOME KEY") == null
assert map.get(gstringKey) == 'value'
assert map.get(gstringKey) == "value"
//map.each { k, v ->
//    println "key: $k, value: $v"
//}

/*
过滤与搜索
 */
def people = [
        1: [name:'Bob', age: 32, gender: 'M'],
        2: [name:'Johnny', age: 36, gender: 'M'],
        3: [name:'Claire', age: 21, gender: 'F'],
        4: [name:'Amy', age: 54, gender:'F']
]

// 查找单独的一个项
def bob = people.find {
    it.value.name == 'Bob'
}
// 不能这么用
//assert bob[age] == 32
def females = people.findAll {
    it.value.gender == 'F'
}

def ageOfBob = bob.value.age
def agesOfFemales = females.collect {
    it.value.age
}
assert ageOfBob == 32
assert agesOfFemales == [21,54]

def agesOfMales = people.findAll { id, person ->
    person.gender == 'M'
}.collect { id, person ->
    person.age
}
assert agesOfMales == [32, 36]

// 如果所有项都匹配谓语，`every` 就返回 true
assert people.every { id, person ->
    person.age > 18
}

// 如果所有项都匹配谓语，`any` 就返回 true
assert people.any { id, person ->
    person.age == 54
}

/*
Grouping
 */
assert ['a', 7, 'b', [2, 3]].groupBy {
    it.class
} == [(String)   : ['a', 'b'],
      (Integer)  : [7],
      (ArrayList): [[2, 3]]
]

assert [
        [name: 'Clark', city: 'London'],
        [name: 'Sharma', city: 'London'],
        [name: 'Maradona', city: 'LA'],
        [name: 'Zhang', city: 'HK'],
        [name: 'Ali', city: 'HK'],
        [name: 'Liu', city: 'HK'],
].groupBy {
    it.city
} == [
        London: [[name: 'Clark', city: 'London'],
                 [name: 'Sharma', city: 'London']],
        LA    : [[name: 'Maradona', city: 'LA']],
        HK    : [[name: 'Zhang', city: 'HK'],
                 [name: 'Ali', city: 'HK'],
                 [name: 'Liu', city: 'HK']],
]

/*
范围（Ranges）

用 .. 标记法定义的范围是全包含的（包含首尾两个值的列表）。

用 ..< 标记法定义的范围是半包含的，即只含有起始值，而不包含末尾值。
 */
// 全包含范围
def range = 5..8
assert range.size() == 4
assert range.get(2) == 7
assert range[2] == 7
assert range instanceof java.util.List
assert range.contains(5)
assert range.contains(8)

// 半包含范围
range = 5..<8
assert range.size() == 3
assert range.get(2) == 7
assert range[2] == 7
assert range instanceof java.util.List
assert range.contains(5)
assert !range.contains(8)

//不使用索引，获取范围的末尾值
range = 1..10
assert range.from == 1
assert range.to == 10

range = 'a'..'d'
assert range.size() == 4
assert range.get(2) == 'c'
assert range[2] == 'c'
assert range instanceof java.util.List
assert range.contains('a')
assert range.contains('d')
assert !range.contains('e')

//for (i in 1..10) {
//    println "Hello ${i}"
//}

//(1..10).each { i ->
//    println "Hello ${i}"
//}
//(1..10).each {
//    println "Hello ${it}"
//}

def years = 1
switch (years) {
    case 1..10: interestRate = 0.076; break;
    case 11..25: interestRate = 0.052; break;
    default: interestRate = 0.037;
}