package com.youngzy.groovy.book.gina2.ch07

def boxer = new Expando()

assert null == boxer.takeThis

boxer.takeThis = 'ouch!'

assert 'ouch!' == boxer.takeThis

boxer.fightBack = {times -> delegate.takeThis * times  }

assert 'ouch!ouch!ouch!' == boxer.fightBack(3)
