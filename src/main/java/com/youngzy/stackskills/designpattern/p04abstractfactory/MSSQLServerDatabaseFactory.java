package com.youngzy.stackskills.designpattern.p04abstractfactory;

import com.youngzy.stackskills.designpattern.p03factory.IDatabase;
import com.youngzy.stackskills.designpattern.p03factory.MSSQLServerDatabase;

public class MSSQLServerDatabaseFactory extends AbstractDatabaseFactory {
    @Override
    IDatabase getDatabase() {
        return new MSSQLServerDatabase();
    }
}
