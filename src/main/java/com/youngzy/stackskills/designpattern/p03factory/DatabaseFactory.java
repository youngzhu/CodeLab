package com.youngzy.stackskills.designpattern.p03factory;

public class DatabaseFactory {
    /**
     * 不用类似key值的传参，是因为它违法了开/闭原则：类应该对扩展开放，对修改关闭。
     *
     * @return
     * @throws Exception
     */
    public static IDatabase getDatabase() throws Exception {
        String databaseClzName = readFromConfig();
        IDatabase database = (IDatabase) Class.forName(databaseClzName).newInstance();

        return database;
    }

    private static String readFromConfig() {

        return "";
    }
}
