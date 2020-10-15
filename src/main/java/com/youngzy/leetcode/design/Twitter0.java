package com.youngzy.leetcode.design;

import java.util.*;

public class Twitter0 implements Twitter {

    // 每个用户关注的人
    Map<Integer, List<Integer>> followee;
    // 每个用户发布的推文
    Map<Integer, List<Integer>> twitters;

    // 所有的推文，因为要按时间的顺序排列
    Stack<Integer> twittersAll;
    Stack<Integer> twittersAllBak;

    /** Initialize your data structure here. */
    public Twitter0() {
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
