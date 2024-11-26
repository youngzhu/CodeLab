package com.youngzy.groovy.book.gina2.ch09

class NoisySet2 {
    @Delegate
    Set delegate = new HashSet()

    // 有没有这个注解都会指向 Set
//    @Override
    boolean add(item) {
        println "adding $item"
        delegate.add(item)
    }

//    @Override
    boolean addAll(Collection items) {
        items.each { println "adding $it" }
        delegate.addAll(items)
    }
}

Set ns = new NoisySet2()
ns.add(1)
ns.addAll([2, 3])
assert ns.size() == 3
