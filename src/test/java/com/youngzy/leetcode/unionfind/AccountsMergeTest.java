package com.youngzy.leetcode.unionfind;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AccountsMergeTest {

    AccountsMerge am;
    List<List<String>> input;

    @Before
    public void setUp() throws Exception {
        am = new AccountsMerge();

    }

    private void build(String[]... data) {
        input = new ArrayList<>();

        for (String[] d : data) {
            input.add(Arrays.asList(d));
        }
    }

    @Test
    public void accountsMerge() {
        String[] data1 = {"John", "johnsmith@mail.com", "john00@mail.com"};
        String[] data2 = {"John", "johnnybravo@mail.com"};
        String[] data3 = {"John", "johnsmith@mail.com", "john_newyork@mail.com"};
        String[] data4 = {"Mary", "mary@mail.com"};

        build(data1, data2, data3, data4);

        am.accountsMerge(input);
    }

    @Test
    public void accountsMerge2() {
        String[] data1 = {"David","David0@m.co","David1@m.co"};
        String[] data2 = {"David","David3@m.co","David4@m.co"};
        String[] data3 = {"David","David4@m.co","David5@m.co"};
        String[] data4 = {"David","David2@m.co","David3@m.co"};
        String[] data5 = {"David","David1@m.co","David2@m.co"};

        build(data1, data2, data3, data4, data5);

        am.accountsMerge(input);
    }
}