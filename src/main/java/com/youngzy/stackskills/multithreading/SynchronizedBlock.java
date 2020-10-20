package com.youngzy.stackskills.multithreading;

import java.util.List;

public class SynchronizedBlock {
    private int nameCounter;
    private String lastName;
    private List<String> nameList;

    public void addName(String name) {
        synchronized (this) {
            lastName = name;
            nameCounter ++;
        }
        nameList.add(name);
    }
}
