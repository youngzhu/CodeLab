package com.youngzy.groovy.hello

//// 语法风格指南

//  可选择性使用的 return 关键字
// 像 if/else、try/catch 这些语句也能返回值，就好像在这些语句中也存在“最后一个表达式”一样。
def foo(n) {
    if(n == 1) {
        "Roshan"
    } else {
        "Dawrani"
    }
    "last"
}

// 不知道是不是版本问题。这里总是返回 null 啊，之前也遇到过，加 return 就可以了
// 还是说 class 里，和 脚本 里不一样？
// 果然，class 里的可以！！
//assert foo(1) == "Roshan"
//assert foo(2) == "Dawrani"

class ReturnDemo {
    def foo(n) {
        if(n == 1) {
            "Roshan"
        } else {
            "Dawrani"
        }
//        "last"
    }
}
def rd = new ReturnDemo()
assert rd.foo(1) == "Roshan"
assert rd.foo(2) == "Dawrani"

// def 和类型，最多留一个就行

// 默认就是 public

// 利用 with 来处理对于同一 bean 的重复操作
// 在创建新实例时，带有默认构造函数的命名参数是非常有用的。但是，如果更新一个已有实例呢？难道你还必须一遍一遍重复 server 前缀？
// 不必如此，Groovy 所提供的 with() 方法可以应用于所有类型的对象，比如像下面这样：
/*server.name = application.name
server.status = status
server.sessionCount = 3
server.start()
server.stop()*/

//就可以转换成如下的形式：
/*server.with {
    name = application.name
    status = status
    sessionCount = 3
    start()
    stop()
}*/

//// GString（插值、多行）
void foo() {
    throw new Exception("Unable to convert resource: " + resource)
}

//跟下面的方式对比一下：
void foo1() {
    throw new Exception("Unable to convert resource: ${resource}")
}

//当字符串与它们的联合表达式用 Java 表示显得很长时，比如像下面这个：
void foo2() {
    throw new Exception("Failed to execute command list-applications:" +
            " The group with name " +
            parameterMap.groupname[0] +
            " is not compatible group of type " +
            SERVER_TYPE_NAME)
}

//你可以使用 \ 行连续字符（这并不是一个多行字符串）：
void foo3() {
    throw new Exception("Failed to execute command list-applications: \
The group with name ${parameterMap.groupname[0]} \
is not compatible group of type ${SERVER_TYPE_NAME}")
}

//或者利用三个引号的多行字符串来表示：
void foo4() {
    throw new Exception("""Failed to execute command list-applications:
    The group with name ${parameterMap.groupname[0]}
    is not compatible group of type ${SERVER_TYPE_NAME}""")
}


//如果需要编写正则表达式模式，应该使用“斜杠式”字符串标记法：
//这样写的好处在于不必使用双重转义反斜杠，从而更便于使用 regex。
assert "foooo/baaaaar" =~ /fo+\/ba+r/

//最后要强调的是，在需要字符串常量时，尽量优先使用单引号字符串，而在显然需要字符串插值时，才使用双引号字符串。





