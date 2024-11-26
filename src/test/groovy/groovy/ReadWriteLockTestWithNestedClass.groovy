package groovy

import groovy.transform.WithReadLock

import java.util.concurrent.locks.ReentrantReadWriteLock

import static java.lang.reflect.Modifier.isFinal
import static java.lang.reflect.Modifier.isPrivate
import static java.lang.reflect.Modifier.isStatic
import static java.lang.reflect.Modifier.isTransient

class ReadWriteLockTestWithNestedClass extends GroovyTestCase {

    static class MyClass {                            //#A
        @WithReadLock
        void readerMethod1() {}
    }

    void testLockFieldDefaultsForReadLock() {
        def field = MyClass.getDeclaredField('$reentrantlock')
        assert isPrivate(field.modifiers)
        assert !isTransient(field.modifiers)
        assert isFinal(field.modifiers)
        assert !isStatic(field.modifiers)
        assert field.type == ReentrantReadWriteLock
    }
}
//#A Nested class
