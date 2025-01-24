package com.youngzy.groovy.book.gina2.ch17

class Counter {
    int biggerThan(items, threshold) {
        items.grep{ it > threshold }.size()
    }
}
