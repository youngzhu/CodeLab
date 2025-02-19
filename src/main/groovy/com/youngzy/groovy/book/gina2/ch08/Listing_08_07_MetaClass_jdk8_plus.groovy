package com.youngzy.groovy.book.gina2.ch08

MetaClass mc = String.metaClass
final Object[] NO_ARGS = []
assert   1  == mc.respondsTo("toString", NO_ARGS).size()
assert   3  == mc.properties.size()
// 不同的jdk版本，结果可能不一样
assert  77  == mc.methods.size()
assert 216  == mc.metaMethods.size()
//
assert ""   == mc.invokeMethod("","toString", NO_ARGS)
assert null == mc.invokeStaticMethod(String, "println", NO_ARGS)
assert ""   == mc.invokeConstructor(NO_ARGS)