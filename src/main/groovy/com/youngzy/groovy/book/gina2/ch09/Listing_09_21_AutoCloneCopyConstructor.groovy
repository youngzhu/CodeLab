package com.youngzy.groovy.book.gina2.ch09

import groovy.transform.*
import org.apache.commons.lang3.time.DateUtils

import static groovy.transform.AutoCloneStyle.*

@TupleConstructor
@AutoClone(style=COPY_CONSTRUCTOR)
class Person {
    final String name
    final Date born
}

@TupleConstructor(includeSuperProperties=true,
        callSuper=true)
//@AutoClone
// 还是有区别的，这个会报错：
// org.codehaus.groovy.runtime.typehandling.GroovyCastException:
// Cannot cast object 'com.youngzy.groovy.book.gina2.ch09.Person@73a2e526' with class 'com.youngzy.groovy.book.gina2.ch09.Person'
// to class 'com.youngzy.groovy.book.gina2.ch09.Chef2'
@AutoClone(style=COPY_CONSTRUCTOR)
class Chef2 extends Person {
    final List<String> recipes
}

def name = 'Jamie Oliver'
def recipes = ['Lentil Soup', 'Crispy Duck']
def born = DateUtils.parseDate('1975-05-27', 'yyyy-MM-dd')
def c1 = new Chef2(name, born, recipes)
def c2 = c1.clone()
assert c2.name == name
assert c2.born == born
assert c2.recipes == recipes
assert c1 != c2