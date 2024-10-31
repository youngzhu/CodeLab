package com.youngzy.groovy.book.gina2.ch08

class DynamicPretender {
    Closure whatToDo = { name -> "-[ ] $name"}  // Closure property with default logic
    def propertyMissing(String name) {
        whatToDo(name)                         // Delegates to the closure
    }
}
def one = new DynamicPretender()
assert one.hello == '-[ ] hello'
one.whatToDo     = { name -> name.size() }   // Changes hook behavior at runtime
assert one.hello == 5