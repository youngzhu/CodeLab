package com.youngzy.groovy.book.gina2.ch03

Money  buck = new Money(1, 'USD')
assert buck
assert buck        == new Money(1, 'USD')              //Use overridden ==
assert buck + buck == new Money(2, 'USD')              //Use implemented +

assert buck + 1 == new Money(2, 'USD')              //Use implemented +

//assert buck + 1.0 == new Money(2, 'USD')              //Use implemented +
