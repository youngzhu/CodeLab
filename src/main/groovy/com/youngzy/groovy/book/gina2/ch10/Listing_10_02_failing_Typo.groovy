package com.youngzy.groovy.book.gina2.ch10

class Detective {
  String firstName
  String lastName
}

def sherlock = new Detective(firstname: 'Sherlock', lastname: 'Holmes')


assert sherlock.lastName == 'Holmes'
