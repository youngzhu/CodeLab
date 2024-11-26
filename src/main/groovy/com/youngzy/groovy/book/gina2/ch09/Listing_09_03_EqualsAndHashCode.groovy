package com.youngzy.groovy.book.gina2.ch09

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Actor {
    String firstName, lastName
}
def magneto = new Actor(firstName:'Ian', lastName: 'McKellen')
def gandalf = new Actor(firstName:'Ian', lastName: 'McKellen')
def mckellen = new Actor(firstName:'Ian', lastName: 'Mckellen')
assert magneto == gandalf
assert magneto != mckellen
