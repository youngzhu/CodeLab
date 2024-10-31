package com.youngzy.groovy.book.gina2.ch08

// 必须是个Closure，见 failing_Listing_08_15_EMC_static.groovy
Integer.metaClass.static.answer = {-> 42}

assert Integer.answer() == 42