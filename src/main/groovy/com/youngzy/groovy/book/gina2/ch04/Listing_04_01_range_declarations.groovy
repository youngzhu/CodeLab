package com.youngzy.groovy.book.gina2.ch04

//#A Inclusive ranges
assert (0..10).contains(0)                             //#A
assert (0..10).contains(5)                             //#A
assert (0..10).contains(10)                            //#A

assert (0..10).contains(-1) == false                   //#A
assert (0..10).contains(11) == false                   //#A

//#B Half-exclusive ranges
assert (0..<10).contains(9)                            //#B
assert (0..<10).contains(10) == false                  //#B

//#1 References to ranges
def a = 0..10                                          //#1
assert a instanceof Range                              //#1
assert a.contains(5)                                   //#1

//#C Explicit construction
a = new IntRange(0,10)                                 //#C
assert a.contains(5)                                   //#C

//#D Bounds checking
assert (0.0..1.0).contains(1.0)                        //#D
assert (0.0..1.0).containsWithinBounds(0.5)            //#D

//#2 Date ranges
def today     = new Date()                             //#2
// 当前版本不支持
//def yesterday = today - 1                              //#2
//assert (yesterday..today).size() == 2                  //#2

//#3 String ranges
assert ('a'..'c').contains('b')                        //#3

//#E for-in-range loop
def log = ''                                           //#E
for (element in 5..9){                                 //#E
    log += element                                     //#E
}                                                      //#E
assert log == '56789'                                  //#E

//#F Loop with reverse range
log = ''                                               //#F
for (element in 9..5){                                 //#F
    log += element                                     //#F
}                                                      //#F
assert log == '98765'                                  //#F

//#4 Half-exclusive, reverse, each with closure
log = ''                                               //#4
(9..<5).each { element ->                              //#4
    log += element                                     //#4
}                                                      //#4
assert log == '9876'                                   //#4
