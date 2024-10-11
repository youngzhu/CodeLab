package com.youngzy.groovy.hello

class Person6 {
    String first, last
}

class Family {
    Person6 father, mother
    def nameOfMother() { "$mother.first $mother.last" }
}

