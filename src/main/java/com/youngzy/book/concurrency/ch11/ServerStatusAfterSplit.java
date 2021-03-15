package com.youngzy.book.concurrency.ch11;

import java.util.HashSet;
import java.util.Set;

/**
 * 11-7 锁分解 - 分解后
 */
public class ServerStatusAfterSplit {
    public final Set<String> users;
    public final Set<String> queries;

    public ServerStatusAfterSplit() {
        users = new HashSet<>();
        queries = new HashSet<>();
    }

    public void addUser(String u) {
        synchronized (users) {
            users.add(u);
        }
    }

    public void addQuery(String q) {
        synchronized (queries) {
            queries.add(q);
        }
    }

    public void removeUser(String u) {
        synchronized (users) {
            users.remove(u);
        }
    }

    public void removeQuery(String q) {
        synchronized (queries) {
            queries.remove(q);
        }
    }
}
