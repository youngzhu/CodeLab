package com.youngzy.stackskills.dp.p04abstractfactory;

import com.youngzy.stackskills.dp.p03factory.IDatabase;

public abstract class AbstractDatabaseFactory {
    abstract IDatabase getDatabase();
}
