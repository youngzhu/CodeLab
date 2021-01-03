package com.youngzy.book.concurrency.ch11;

import java.util.HashSet;
import java.util.Set;

/**
 * 11-6 锁分解 - 分解前
 */
public class ServerStatusBeforeSplit {
    public final Set<String> users;
    public final Set<String> queries;

    public ServerStatusBeforeSplit() {
        users = new HashSet<>();
        queries = new HashSet<>();
    }

    public synchronized void addUser(String u) {
        users.add(u);
    }

    public synchronized void addQuery(String q) {
        queries.add(q);
    }

    public synchronized void removeUser(String u) {
        users.remove(u);
    }

    public synchronized void removeQuery(String q) {
        queries.remove(q);
    }
}
