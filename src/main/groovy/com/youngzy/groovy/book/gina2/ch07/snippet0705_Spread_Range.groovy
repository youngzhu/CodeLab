package com.youngzy.groovy.book.gina2.ch07

def range = (1..3)
assert [0,1,2,3] == [0,*range]