package com.youngzy.groovy.book.gina2.ch10

import groovy.transform.TypeChecked

@TypeChecked
void printAuthors() {
  def authors = ['Dierk', 'Guillaume']
  printToUpperCase(authors)
}

void printToUpperCase(List<String> authors) {
  authors.each { println it.toUpperCase() }
}

printAuthors()
