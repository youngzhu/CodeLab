package com.youngzy.groovy.book.gina2.ch17

import groovy.transform.TupleConstructor

@TupleConstructor
class Purchase {
  def name, number, completed = false

  def fill(theater) {
    if (theater.hasSeatsAvailable(name, number)) {
      theater.purchaseTicket(name, number)
      completed = true
    }
  }
}
