package com.youngzy.stackskills.designpattern.p03factory;

class Client {
    public static void main(String[] args) throws Exception {
        IDatabase database = DatabaseFactory.getDatabase();

        // 跟具体是哪个数据库无关
        // database.getConnection()
        // ...
    }
}
