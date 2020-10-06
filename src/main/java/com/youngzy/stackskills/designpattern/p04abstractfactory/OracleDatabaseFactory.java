package com.youngzy.stackskills.designpattern.p04abstractfactory;

import com.youngzy.stackskills.designpattern.p03factory.IDatabase;
import com.youngzy.stackskills.designpattern.p03factory.OracleDatabase;

public class OracleDatabaseFactory extends AbstractDatabaseFactory {
    @Override
    IDatabase getDatabase() {
        return new OracleDatabase();
    }
}
