package com.youngzy.groovy.book.gina2.ch12

file = new File('Listing_12_03_File_Iteration.groovy')
file.each { println it }
assert file.any { it =~ /File/ }
assert 3 == file.findAll { it =~ /File/ }.size()

// file.grep { it } returns only nonempty lines, because empty strings evaluate to false
assert 7 == file.grep { it }.size()
