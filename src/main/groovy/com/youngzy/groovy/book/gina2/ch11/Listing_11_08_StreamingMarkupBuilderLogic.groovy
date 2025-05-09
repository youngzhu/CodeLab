package com.youngzy.groovy.book.gina2.ch11

import org.apache.commons.lang3.time.DateUtils

def builder = new groovy.xml.StreamingMarkupBuilder()
def writable = builder.bind {                                        //#A
  invoices {                                                         //#B
    for (day in 1..3) {
      def invDate = DateUtils.parseDate("2015-01-0$day", 'yyyy-MM-dd')
      invoice(date: invDate) {
        item(count: day) {
          product(name: 'ULC', dollar: 1499)
        }
      }
    }
  }
}
def result = writable.toString()                                     //#C
assert result.startsWith("<invoices><invoice date='Thu Jan 01")      //#|D
assert result.endsWith('</invoice></invoices>')                      //#|D
//#A The bind method introduces the markup Closure
//#B The root node must be included in the markup
//#C Or writable.writeTo(file)
//#D Checking start and end of long single line
