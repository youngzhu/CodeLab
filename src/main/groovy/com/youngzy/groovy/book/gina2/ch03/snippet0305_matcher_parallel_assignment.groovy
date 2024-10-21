package com.youngzy.groovy.book.gina2.ch03

def (x,y,z) = 'a b c' =~ /\S/

assert x == 'a'
assert y == 'b'
assert z == 'c'