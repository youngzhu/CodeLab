package com.youngzy.leetcode.design;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TweetCountsTest {

    @Test
    public void test() {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 10);
        List<Integer> list = tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59);
        assertArrayEquals(new Integer[]{2}, list.toArray(new Integer[0]));
    }

    @Test
    public void testInt() {
        System.out.println(Integer.MAX_VALUE);
        int i = 1000000000;
        System.out.println(i);
    }

}