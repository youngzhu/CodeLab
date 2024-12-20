package com.youngzy.groovy.constant

/**
 *
 *
 * @author youngzy
 * @since 2024-12-20
 */
class PathsTest extends GroovyTestCase {
    void testRoot() {
        println(Paths.ROOT)
    }

    void testGetMainResources() {
        println(Paths.mainResources)
    }

    void testGetOutput() {
        println(Paths.output)
    }
}
