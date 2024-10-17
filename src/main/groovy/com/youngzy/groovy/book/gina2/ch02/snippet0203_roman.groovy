package com.youngzy.groovy.book.gina2.ch02

def roman = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII']  //#A
assert roman[4] == 'IV'                                     //#B

roman[8] = 'VIII'                                           //#C
assert roman.size() == 9
//#A List of Roman numerals
//#B List access
//#C List expansion

// 可以跳跃吗？
// 可以。跳过的为 null
roman[10] = 'X'
assert roman.size() == 11
assert roman[9] == null