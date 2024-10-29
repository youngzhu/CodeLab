package com.youngzy.groovy.book.gina2.ch07

def getList(){
    return [1,2,3]
}
def sum(a,b,c){
    return a + b + c
}
assert 6 == sum(*list)