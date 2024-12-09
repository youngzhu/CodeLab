package com.youngzy.groovy.book.gina2.ch10

class Duck {
  def methodMissing(String name, args) {
    println "$name!"
  }
}

def duck = new Duck()
duck.quack()
