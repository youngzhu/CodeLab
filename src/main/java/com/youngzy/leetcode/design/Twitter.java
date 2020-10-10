package com.youngzy.leetcode.design;

import java.util.*;

/**
 * 355. 设计推特
 * 设计一个简化版的推特(Twitter)，
 * 可以让用户实现发送推文，
 * 关注/取消关注其他用户，
 * 能够看见关注人（包括自己）的最近十条推文。
 * 你的设计需要支持以下的几个功能：
 *     postTweet(userId, tweetId): 创建一条新的推文
 *     getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 *     follow(followerId, followeeId): 关注一个用户
 *     unfollow(followerId, followeeId): 取消关注一个用户
 *
 * 示例:
 * Twitter twitter = new Twitter();
 *
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 *
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 *
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 *
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 *
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);
 *
 * 链接：https://leetcode-cn.com/problems/design-twitter
 */
public class Twitter {

    // 每个用户关注的人
    Map<Integer, List<Integer>> followee;
    // 每个用户发布的推文
    Map<Integer, List<Integer>> twitters;

    // 所有的推文，因为要按时间的顺序排列
    Stack<Integer> twittersAll;
    Stack<Integer> twittersAllBak;

    /** Initialize your data structure here. */
    public Twitter() {
        followee = new HashMap<>();
        twitters = new HashMap<>();

        twittersAll = new Stack<>();
        twittersAllBak = new Stack<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        List<Integer> twitterList = twitters.get(userId);
        if (twitterList == null) {
            twitterList = new LinkedList<>();
        }

        twitterList.add(tweetId);
        twitters.put(userId, twitterList);

        twittersAll.push(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();

        // 先找到所有可能看到的推文
        // 再按时间筛选
        List<Integer> all = getAllFeed(userId);
        if (all.size() > 0) {
            result.addAll(getRecentFeed(10, all));
        }

        return result;
    }

    private List<Integer> getRecentFeed(int count, List<Integer> allFeed) {
        List<Integer> result = new ArrayList<>(count);

        while (! twittersAll.isEmpty()) {
            if (count == 0) {
                break;
            }
            int twitterId = twittersAll.pop();
            twittersAllBak.push(twitterId);
            if (allFeed.contains(twitterId)) {
                result.add(twitterId);
                count --;
            }
        }

        // 恢复
        while (! twittersAllBak.isEmpty()) {
            twittersAll.push(twittersAllBak.pop());
        }

        return result;
    }

    /**
     * 找到所有可能看到的推文
     *
     * @param userId
     * @return
     */
    private List<Integer> getAllFeed(int userId) {
        List<Integer> result = new LinkedList<>();
        // 自己的
        if (twitters.get(userId) != null) {
            result.addAll(twitters.get(userId));
        }

        // 关注者的
        List<Integer> followeeList = followee.get(userId);

        if (followeeList != null) {
            for (Integer followeeId : followeeList) {
                if (twitters.get(followeeId) != null) {
                    result.addAll(twitters.get(followeeId));
                }
            }
        }

        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        List<Integer> followeeList = followee.get(followerId);
        if (followeeList == null) {
            followeeList = new ArrayList<>();
        }
        followeeList.add(followeeId);
        followee.put(followerId, followeeList);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        List<Integer> followeeList = followee.get(followerId);
        if (followeeList != null) {
            int idx = followeeList.indexOf(followeeId);
            if (idx != -1) {
                followeeList.remove(idx);
                followee.put(followerId, followeeList);
            }
        }
    }
}
