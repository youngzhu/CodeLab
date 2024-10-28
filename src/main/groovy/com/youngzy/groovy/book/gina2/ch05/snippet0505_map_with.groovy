package com.youngzy.groovy.book.gina2.ch05

// with method does exactly that: executing a closure by first setting the delegate
// to the receiver of the with method:

def map = [:]
map.with { // delegate is now map
    a = 1 // same as map.a = 1
    b = 2 // same as map.b = 2
}
assert map == [a:1, b:2]