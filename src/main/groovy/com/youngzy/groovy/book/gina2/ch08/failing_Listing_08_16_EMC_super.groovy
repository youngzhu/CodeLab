package com.youngzy.groovy.book.gina2.ch08

ExpandoMetaClass.enableGlobally()

Map.metaClass.getTable = {->
    delegate.collect{ [it.key, it.value] }
}

assert [a:1, b:2].table == [['a', 1], ['b', 2]]