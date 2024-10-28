package com.youngzy.groovy.book.gina2.ch06

def list = [1, 2, 3]
// each 里不能这么做，这里可以
while (list) {
    list.remove(0)
}
assert list == []

while (list.size() < 3) list << list.size() + 1
assert list == [1, 2, 3]