package com.youngzy.groovy.book.gina2.ch08

def move(string, distance) {
    return string.collect { (it as char) + distance as char }.join()
}

String.metaClass {
    shift = -1
    encode  {-> move delegate, shift  }
    decode  {-> move delegate, -shift }
    getCode {-> encode() }
    getOrig {-> decode() }
}

assert "IBM".encode() == "HAL"
assert "HAL".orig     == "IBM"

def ibm = "IBM"
ibm.shift = 7
assert ibm.code == "PIT"