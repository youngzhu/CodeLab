package com.youngzy.leetcode.design;

import java.util.*;

/**
 * 学习别人优秀的设计
 */
public class Twitter1 implements Twitter {
    // 用户（ID）和其发布的推文（链表结构）对应的关系
    private Map<Integer, Tweet> tweetMap;
    // 用户（ID）与其关注者之间的对应关系
    // 不必要有序，所以用set
    private Map<Integer, Set<Integer>> followingsMap;
    private PriorityQueue<Tweet> heap;

    public Twitter1() {
        tweetMap = new HashMap<>();
        followingsMap = new HashMap<>();
        // 设或不设容量，差别不大
        heap = new PriorityQueue<>(MAX_NEWS_FEED, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                // 时间值越大（越靠后）排在越前面
                return o2.timestamp - o1.timestamp;
            }
        });
    }

    public void postTweet(int userId, int tweetId) {
        Tweet newTweet = new Tweet(tweetId);
        if (tweetMap.containsKey(userId)) {
            Tweet prePost = tweetMap.get(userId);
            newTweet.prevPost = prePost;
        }

        tweetMap.put(userId, newTweet);
    }

    private static final int MAX_NEWS_FEED = 10;
    public List<Integer> getNewsFeed(int userId) {
        // 每次必须清除
        // 之前没有这句，提交测试报错了
        heap.clear();

        // 如果自己也发布了推文，在堆中也加入
        if (tweetMap.containsKey(userId)) {
            heap.add(tweetMap.get(userId));
        }

        // 加上关注者的推文
        if (followingsMap.containsKey(userId)) {
            Set<Integer> followings = followingsMap.get(userId);
            for (int followingId : followings) {
                // 判断关注者 是否发布了推文
                // 如果发了，则加入heap
                if (tweetMap.containsKey(followingId)) {
                    heap.add(tweetMap.get(followingId));
                }

            }
        }

        List<Integer> answer = new ArrayList<>(MAX_NEWS_FEED);
        int count = MAX_NEWS_FEED;
        while (!heap.isEmpty() && count > 0) {
            Tweet tweet = heap.poll();
            answer.add(tweet.id);

            if (tweet.prevPost != null) {
                heap.add(tweet.prevPost);
            }

            count--;
        }

        return answer;
    }

    public void follow(int followerId, int followingId) {
        // 不能关注自己
        if (followingId == followerId) {
            return;
        }

        Set<Integer> followings = new HashSet<>(Arrays.asList(followingId));
        if (followingsMap.containsKey(followerId)) {
            followings.addAll(followingsMap.get(followerId));
        }
        followingsMap.put(followerId, followings);
    }

    public void unfollow(int followerId, int followingId) {
        if (followingId == followerId) {
            return;
        }

        // 如果已有关注的人群，去掉对followingId的关注
        if (followingsMap.containsKey(followerId)) {
            Set<Integer> followings = followingsMap.get(followerId);
            followings.remove(followingId);
            followingsMap.put(followerId, followings);
        }

    }

    /**
     * 推文类
     */
    private static class Tweet {
        // 伪自增序列
        // 每发布一条推文+1
        private static int count = 0;

        private int id;
        // 伪时间戳，表示发布的时间顺序
        // 越大表示时序越近
        private int timestamp;
        // 前一条发布的推文
        private Tweet prevPost;

        public Tweet(int id) {
            this.id = id;
            this.timestamp = count ++;
        }
    }
}
