package com.youngzy.groovy.book.gina2.ch08

class InspectMe {
    int outer(){
        return inner()        
    }
    private int inner(){
        return 1
    }
}

def tracer = new TracingInterceptor(writer: new StringWriter()) //#1
def proxyMetaClass = ProxyMetaClass.getInstance(InspectMe)
proxyMetaClass.interceptor = tracer

InspectMe inspectMe = new InspectMe()
inspectMe.metaClass = proxyMetaClass  //#2

assert 1 == inspectMe.outer()         //#3

assert "\n" + tracer.writer.toString() == """
before com.youngzy.groovy.book.gina2.ch08.InspectMe.outer()
  before com.youngzy.groovy.book.gina2.ch08.InspectMe.inner()
  after  com.youngzy.groovy.book.gina2.ch08.InspectMe.inner()
after  com.youngzy.groovy.book.gina2.ch08.InspectMe.outer()
"""