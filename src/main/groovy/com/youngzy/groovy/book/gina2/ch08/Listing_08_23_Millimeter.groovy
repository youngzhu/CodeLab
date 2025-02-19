package com.youngzy.groovy.book.gina2.ch08

Number.metaClass {
    getMm = { delegate          }
    getCm = { delegate *  10.mm }
    getM  = { delegate * 100.cm }
}

assert 1.m + 20.cm - 8.mm == 1.192.m
assert 1.m + 20.cm - 8.mm == 1192.mm