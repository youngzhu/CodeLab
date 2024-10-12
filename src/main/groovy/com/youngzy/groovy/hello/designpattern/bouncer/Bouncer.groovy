package com.youngzy.groovy.hello.designpattern.bouncer

import static groovy.test.GroovyAssert.shouldFail

// 保镖模式（Bouncer Pattern）指的是某个方法唯一目的在于抛出异常（当满足特定条件时）或什么都不做。这种方法经常使用于防卫警戒某方法的前提条件。
//
//当编写工具方法时，应对有可能会出毛病的输入参数保持警惕。在编写内部方法时，应该通过有效的单元测试来保证一直持有特定前提条件。因为在这些情况下，
// 人们可能不会注重方法的防御。

// Null 检查范例
class NullChecker {
    static check(name, arg) {
        if (arg == null) {
            throw new IllegalArgumentException(name + ' is null')
        }
    }
}

void doStuff(String name, Object value) {
    NullChecker.check('name', name)
    NullChecker.check('value', value)
    // 事务逻辑
}

// 用 Groovy 来做，就简单多了：
void doStuff2(String name, Object value) {
    assert name != null, 'name should not be null'
    assert value != null, 'value should not be null'
    // 事务逻辑
}

//验证范例
class NumberChecker {
    static final String NUMBER_PATTERN = "\\d+(\\.\\d+(E-?\\d+)?)?"
    static isNumber(str) {
        if (!str ==~ NUMBER_PATTERN) {
            throw new IllegalArgumentException("Argument '$str' must be a number")
        }
    }
    static isNotZero(number) {
        if (number == 0) {
            throw new IllegalArgumentException('Argument must not be 0')
        }
    }
}

def stringDivide(String dividendStr, String divisorStr) {
    NumberChecker.isNumber(dividendStr)
    NumberChecker.isNumber(divisorStr)
    def dividend = dividendStr.toDouble()
    def divisor = divisorStr.toDouble()
    NumberChecker.isNotZero(divisor)
    // 搞不懂为什么一定要有 return
    // 没有return 函数调用结果为 null
    return dividend / divisor
}

assert 40 == stringDivide('1.2E2', '3.0')

def e = shouldFail {
    stringDivide('1.2E2', '0')
}
assert e instanceof IllegalArgumentException
assert e.message == 'Argument must not be 0'

// 但利用 Groovy，我们只需这样使用即可：
def stringDivide2(String dividendStr, String divisorStr) {
    assert dividendStr =~ NumberChecker.NUMBER_PATTERN
    assert divisorStr =~ NumberChecker.NUMBER_PATTERN
    def dividend = dividendStr.toDouble()
    def divisor = divisorStr.toDouble()
    assert divisor != 0, 'Divisor must not be 0'
    return dividend / divisor
}

assert 40 == stringDivide2('1.2E2', '3.0')

e = shouldFail {
    stringDivide2('1.2E2', '0')
}
assert e instanceof AssertionError
//assert e.message == 'Argument must not be 0'












