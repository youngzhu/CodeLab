package com.youngzy.stackskills.dp.p06adapter;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIteratorAdapter<T> implements Iterator<T> {

    Enumeration<T> enumeration;

    public EnumerationIteratorAdapter(Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumeration.nextElement();
    }
}
