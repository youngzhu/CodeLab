package com.youngzy.groovy.book.gina2.ch08

assert String.metaClass =~ /MetaClassImpl/
println(String.metaClass)
// mata 里用 delegate
// 其他地方用 it ？
String.metaClass.low    = {-> delegate.toLowerCase() }
//String.metaClass.low    = { it.toLowerCase() }
assert String.metaClass =~ /ExpandoMetaClass/
println(String.metaClass)

assert "DiErK".low() == "dierk"