package com.youngzy.groovy.book.gina2.ch05

def log = ''
(1..10).each { counter -> log += counter }
assert log == '12345678910'

log = ''
(1..10).each { log += it }
assert log == '12345678910'