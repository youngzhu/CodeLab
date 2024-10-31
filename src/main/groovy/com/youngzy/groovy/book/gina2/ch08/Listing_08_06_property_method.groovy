package com.youngzy.groovy.book.gina2.ch08

class NoParens {
    def getProperty(String propertyName) {
        if (metaClass.hasProperty(this, propertyName)) { //#1
            // 不能这么写，因为它会调用 getProperty() 陷入死循环
//            return this."$propertyName"
            return metaClass.getProperty(this, propertyName)
        }
       invokeMethod propertyName, null  //#2
    }
}

class PropUser extends NoParens {       //#3
    boolean existingProperty = true
}

def user = new PropUser()
assert user.existingProperty
assert user.toString() == user.toString //#4