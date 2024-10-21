package com.youngzy.groovy.book.gina2.ch03

def matcher = 'a b c' =~ /\S/

assert matcher[0]     == 'a'
assert matcher[1..2]  == ['b','c']
assert matcher.size() == 3