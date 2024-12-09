package com.youngzy.groovy.book.gina2.ch10

enum MyEnum {
    variable, value
}

// 这几个类型比较特殊，即使加了 TypeChecked ，不同的类型也能赋值

@groovy.transform.TypeChecked
void testAssignmentsWithCoercion() {
    MyEnum val = 'value'                              //#A
    assert val == MyEnum.value

    String blue = java.awt.Color.BLUE               //#B
    assert blue == 'java.awt.Color[r=0,g=0,b=255]'

    boolean nonEmpty = new Date()                   //#C
    Boolean empty = ''                              //#C
    assert nonEmpty
    assert !empty

    Class stringClass = 'java.lang.String'          //#D
    assert stringClass.interfaces.size() == 3
}

testAssignmentsWithCoercion()
//#A Strings coerced to enum values
//#B Anything coerced to String
//#C Anything coerced to boolean/Boolean
//#D Strings coerced to classes
