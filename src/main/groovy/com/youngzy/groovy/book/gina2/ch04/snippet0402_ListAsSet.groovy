package com.youngzy.groovy.book.gina2.ch04

// 删除list中重复的数据
def x = [1, 1, 1]
// 方法一：使用 set
assert [1] == new HashSet(x).toList()
// 方法二：使用 unique()
assert [1] == x.unique()
assert [1] == [1, '1'].unique { item -> item.toInteger() }