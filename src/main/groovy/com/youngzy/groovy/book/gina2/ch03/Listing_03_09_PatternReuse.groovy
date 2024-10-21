package com.youngzy.groovy.book.gina2.ch03

import java.time.MonthDay

def twister = 'she sells sea shells at the sea shore of seychelles'
// some more complicated regex: 
// word that starts and ends with same letter
def regex = /\b(\w)\w*\1\b/
def many  = 100 * 1000

start = System.nanoTime()
many.times{
    twister =~ regex                                   //#1
}
timeImplicit = System.nanoTime() - start

sleep(1000)

start = System.nanoTime()
pattern = ~regex                                       //#A
many.times{
    pattern.matcher(twister)                           //#B
}
timePredef = System.nanoTime() - start

assert timeImplicit > timePredef * 2                   //#C
printf("timeImplicit: %d, timePredef: %d\n", timeImplicit, timePredef)
printf("timeImplicit / timePredef: %.2f", timeImplicit / timePredef)
//#1 Find operator with implicit pattern construction
//#A Explicit pattern construction
//#B Apply pattern on a string
//#C At least twice as fast (possibly 3–5 times)
