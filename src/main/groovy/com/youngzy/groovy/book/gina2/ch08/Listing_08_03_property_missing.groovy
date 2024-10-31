package com.youngzy.groovy.book.gina2.ch08

class PropPretender {
    def propertyMissing(String name) {
        "accessed $name"
    }
}
def bounce = new PropPretender()
assert bounce.hello == 'accessed hello'