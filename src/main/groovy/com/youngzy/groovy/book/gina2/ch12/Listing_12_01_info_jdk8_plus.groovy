package com.youngzy.groovy.book.gina2.ch12

def newline = "\n"

assert newline.toString() == "\n"

assert newline.dump() ==
    '''<java.lang.String@a value=
 hash=10>'''

assert newline.inspect() == /'\n'/
