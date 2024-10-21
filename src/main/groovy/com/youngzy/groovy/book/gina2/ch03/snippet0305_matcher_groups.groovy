package com.youngzy.groovy.book.gina2.ch03

def matcher = 'a:1 b:2 c:3' =~ /(\S+):(\S+)/

assert matcher.hasGroup()
println matcher[0]
assert matcher[0] == ['a:1', 'a', '1']  // 1st match
assert matcher[1][2] == '2'             // 2nd match, 2nd group