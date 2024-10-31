package com.youngzy.groovy.book.gina2.ch08

def boxer = new Expando()

boxer.takeThis  = 'ouch!'
boxer.fightBack = { times -> takeThis * times  }

assert boxer.fightBack(3) == 'ouch!ouch!ouch!'