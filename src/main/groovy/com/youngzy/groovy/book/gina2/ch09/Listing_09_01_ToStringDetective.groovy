package com.youngzy.groovy.book.gina2.ch09

import groovy.transform.ToString

@ToString
class Detective {
  String firstName, lastName
}

def sherlock  = new Detective(firstName: 'Sherlock', lastName: 'Holmes')
assert sherlock .toString() == 'com.youngzy.groovy.book.gina2.ch09.Detective(Sherlock, Holmes)'
