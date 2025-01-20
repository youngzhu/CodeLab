package com.youngzy.groovy.book.gina2.ch14
//@Grab('org.codehaus.groovy:groovy-xml:2.2.0')
import groovy.xml.StreamingMarkupBuilder

def path = new XmlSlurper().parse(new File('data/plan.xml'))

def builder = new StreamingMarkupBuilder()
def copier = builder.bind{ mkp.yield(path) }
def result = "$copier"

//println result
// 两种方式都行
//System.out << result
System.out << copier
assert result.startsWith('<plan><week ')
assert result.endsWith('</week></plan>')