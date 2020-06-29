package com.youngzy.stackskills.dp.p04abstractfactory;

import com.youngzy.stackskills.dp.p03factory.IDatabase;
import com.youngzy.stackskills.dp.p03factory.MSSQLServerDatabase;
import com.youngzy.stackskills.dp.p03factory.OracleDatabase;

public class OracleDatabaseFactory extends AbstractDatabaseFactory {
    @Override
    IDatabase getDatabase() {
        return new OracleDatabase();
    }
}
