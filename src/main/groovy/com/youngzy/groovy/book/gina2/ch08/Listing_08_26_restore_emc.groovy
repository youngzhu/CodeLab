package com.youngzy.groovy.book.gina2.ch08

MetaClass oldMetaClass = String.metaClass           // stores old metaclass

MetaMethod alias = String.metaClass.metaMethods     // stores MetaMethod
                   .find { it.name == 'size' }
String.metaClass {
    oldSize = { -> alias.invoke delegate  }
    size    = { -> oldSize() * 2 }
}

assert "abc".size()    == 6
assert "abc".oldSize() == 3

if (oldMetaClass.is(String.metaClass)){
    String.metaClass {                              // reverses modification
        size    = { -> alias.invoke delegate }
        oldSize = { -> throw new UnsupportedOperationException() }
    }
} else {
    String.metaClass = oldMetaClass                 // resets metaClass
}

assert "abc".size() == 3