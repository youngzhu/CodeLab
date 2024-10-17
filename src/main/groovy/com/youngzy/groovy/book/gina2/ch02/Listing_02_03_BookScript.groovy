package com.youngzy.groovy.book.gina2.ch02

Book gina = new Book('Groovy in Action')

assert gina.getTitle()         == 'Groovy in Action'
// 函数可以在定义前调用
assert getTitleBackwards(gina) == 'noitcA ni yvoorG'

String getTitleBackwards(book) {
    String title = book.getTitle()
    return title.reverse()
}
