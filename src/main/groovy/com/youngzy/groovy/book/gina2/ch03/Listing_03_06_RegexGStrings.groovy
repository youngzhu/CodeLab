package com.youngzy.groovy.book.gina2.ch03

assert "abc" == /abc/
assert "\\d" == /\d/ 

def reference = "hello"
assert reference == /$reference/

assert "\$" == /$/