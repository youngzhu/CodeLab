package com.youngzy.stackskills.designpattern.p04abstractfactory;

import com.youngzy.stackskills.designpattern.p03factory.IDatabase;

public abstract class AbstractDatabaseFactory {
    abstract IDatabase getDatabase();
}
