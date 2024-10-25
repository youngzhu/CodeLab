package com.youngzy.groovy.book.gina2.ch04

// 删除list中的 null 值
List x = [1, null, null, 2]

assert [1, 2] == x.findAll { it != null }
// 有技巧，关于 grep 和 Groovy Truth
assert [1, 2] == x.grep { it }

assert [1, 2] == x - [null]

x.removeAll([null])
assert [1, 2] == x