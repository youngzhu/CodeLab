package com.youngzy.book.concurrency.ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 3-10 使用 ThreadLocal 来维持线程封闭性
 */
public class ConnectionDispenser {
    static String DB_URL = "jdbc:mysql://localhost/mydb";

    private ThreadLocal<Connection> connectionHolder =
            new ThreadLocal<Connection>() {
                public Connection init() {
                    try {
                        return DriverManager.getConnection(DB_URL);
                    } catch (SQLException e) {
                        throw new RuntimeException("Unable to acquire conn");
                    }
                }
            };

    public Connection getConnection() {
        return connectionHolder.get();
    }
}
