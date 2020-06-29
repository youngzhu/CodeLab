package com.youngzy.stackskills.dp.p04abstractfactory;

import com.youngzy.stackskills.dp.p03factory.IDatabase;
import com.youngzy.stackskills.dp.p03factory.MSSQLServerDatabase;

public class MSSQLServerDatabaseFactory extends AbstractDatabaseFactory {
    @Override
    IDatabase getDatabase() {
        return new MSSQLServerDatabase();
    }
}
