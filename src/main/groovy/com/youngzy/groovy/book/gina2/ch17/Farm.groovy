package com.youngzy.groovy.book.gina2.ch17

class Farm {
    def getMachines() {
        /* some expensive code here */
        throw new RuntimeException('should not reach here')
    }
}