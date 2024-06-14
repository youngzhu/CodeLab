package com.youngzy.leetcode.design;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class TwitterTest {

    @Test
    public void test() {
        Twitter twitter = new Twitter1();

        // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        List<Integer> newsFeed = twitter.getNewsFeed(1);
        assertArrayEquals(new Integer[]{5}, newsFeed.toArray(new Integer[0]));

        // 用户1关注了用户2.
        twitter.follow(1, 2);

        // 用户2发送了一个新推文 (推文id = 6).
        twitter.postTweet(2, 6);

        // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
        // 推文id6应当在推文id5之前，因为它是在5之后发送的.
        newsFeed = twitter.getNewsFeed(1);
        assertArrayEquals(new Integer[]{6, 5}, newsFeed.toArray(new Integer[0]));

        // 用户1取消关注了用户2.
        twitter.unfollow(1, 2);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        // 因为用户1已经不再关注用户2.
        newsFeed = twitter.getNewsFeed(1);
        assertArrayEquals(new Integer[]{5}, newsFeed.toArray(new Integer[0]));
    }
}