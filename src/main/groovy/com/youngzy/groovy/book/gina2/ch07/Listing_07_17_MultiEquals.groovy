package com.youngzy.groovy.book.gina2.ch07

class Equalizer {
    boolean equals(Equalizer e){
        return true
    }
}

Object same  = new Equalizer()
Object other = new Object()

assert   new Equalizer().equals( same  )
assert ! new Equalizer().equals( other )