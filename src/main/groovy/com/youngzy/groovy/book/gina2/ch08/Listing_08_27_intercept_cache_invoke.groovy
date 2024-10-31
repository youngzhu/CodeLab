package com.youngzy.groovy.book.gina2.ch08

ArrayList.metaClass.methodMissing = { String name, Object args ->
    assert name.startsWith("findBy")
    assert args.size() == 1
    Object.metaClass."$name" = { value ->       // caches the method
        delegate.find { it[name.toLowerCase()-'findby'] == value }
    }
    delegate."$name"(args[0])                   // invoke the method
}

def data = [
    [name:'moon',    au: 0.0025],
    [name:'sun',     au: 1     ],
    [name:'neptune', au:30     ],
]

assert data.findByName('moon')     // intercepted call
assert data.findByName('sun')      // cached call
assert data.findByAu(1)