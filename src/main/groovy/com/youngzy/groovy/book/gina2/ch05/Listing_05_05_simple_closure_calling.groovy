package com.youngzy.groovy.book.gina2.ch05

def adder = { x, y -> return x+y }

assert adder(4, 3) == 7
assert adder.call(2, 6) == 8
