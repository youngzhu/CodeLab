package com.youngzy.leetcode.design;

import java.util.*;

/**
 * 1348. 推文计数
 * 请你实现一个能够支持以下两种方法的推文计数类 TweetCounts：
 *
 * 1. recordTweet(string tweetName, int time)
 * 记录推文发布情况：用户 tweetName 在 time（以 秒 为单位）时刻发布了一条推文。
 * 2. getTweetCountsPerFrequency(string freq, string tweetName, int startTime, int endTime)
 * 返回从开始时间 startTime（以 秒 为单位）到结束时间 endTime（以 秒 为单位）内，
 * 每 分 minute，时 hour 或者 日 day （取决于 freq）内指定用户 tweetName 发布的推文总数。
 * freq 的值始终为 分 minute，时 hour 或者 日 day 之一，表示获取指定用户 tweetName 发布推文次数的时间间隔。
 * 第一个时间间隔始终从 startTime 开始，因此时间间隔为 
 * [startTime, startTime + delta*1>,  
 * [startTime + delta*1, startTime + delta*2>,
 * [startTime + delta*2, startTime + delta*3>, ... ,
 * [startTime + delta*i, min(startTime + delta*(i+1), endTime + 1)>，
 *
 * 其中 i 和 delta（取决于 freq）都是非负整数。
 *  
 * 示例：
 * 输入：
 * ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 * [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
 *
 * 输出：
 * [null,null,null,null,[2],[2,1],null,[4]]
 *
 * 解释：
 * TweetCounts tweetCounts = new TweetCounts();
 * // "tweet3" 发布推文的时间分别是 0, 10 和 60
 * tweetCounts.recordTweet("tweet3", 0);
 * tweetCounts.recordTweet("tweet3", 60);
 * tweetCounts.recordTweet("tweet3", 10);
 * // 返回 [2]。统计频率是每分钟（60 秒），因此只有一个有效时间间隔 [0,60> - > 2 条推文。
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59);
 * // 返回 [2,1]。统计频率是每分钟（60 秒），因此有两个有效时间间隔 1) [0,60> - > 2 条推文，和 2) [60,61> - > 1 条推文。
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60);
 * // "tweet3" 发布推文的时间分别是 0, 10, 60 和 120 。
 * tweetCounts.recordTweet("tweet3", 120);
 * // 返回 [4]。统计频率是每小时（3600 秒），因此只有一个有效时间间隔 [0,211> - > 4 条推文。
 * tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);
 *
 * 提示：
 * 同时考虑 recordTweet 和 getTweetCountsPerFrequency，最多有 10000 次操作。
 * 0 <= time, startTime, endTime <= 10^9
 * 0 <= endTime - startTime <= 10^4
 *
 * 链接：https://leetcode-cn.com/problems/tweet-counts-per-frequency
 */
/*
从2378 ms提升到 424ms
就这样吧，虽然排名还是不高
 */
public class TweetCounts {

    private enum Frequency {
        minute(60),
        hour(3600),
        day(3600 * 24)
        ;

        private final int interval; // 单位间隔

        Frequency(int interval) {
            this.interval = interval;
        }
    }

    // set 没法用 get(i)
    private Map<String, TreeSet<Integer>> data;
    public TweetCounts() {
        data = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        TreeSet<Integer> set = data.get(tweetName);
        if (set == null) {
            set = new TreeSet<>();
            data.put(tweetName, set);
        }
        set.add(time);
    }

    /**
     * [startTime, startTime + delta*1>,  
     * [startTime + delta*1, startTime + delta*2>,
     * [startTime + delta*2, startTime + delta*3>, ... ,
     * [startTime + delta*i, min(startTime + delta*(i+1), endTime + 1)>
     */
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> result = new ArrayList<>();

        TreeSet<Integer> times = data.get(tweetName);
        if (times == null) {
            return result;
        }

        Frequency frequency = Frequency.valueOf(freq);
        int lower = startTime, upper;
        int i = 0;

        List<Integer> list = new ArrayList(times);
        int startFrom = 0;
        do {
            int counter = 0;
//            lower = startTime + frequency.interval * i;
            upper = startTime + frequency.interval * (i + 1);
            upper = Math.min(upper, endTime + 1);

            Range range = new Range(lower, upper);
            for (int idx = startFrom;idx < list.size(); idx ++) {
                if (list.get(idx) >= range.upper) {
                    break;
                }
                if (range.isInRange(list.get(idx))) {
                    counter++;
                    startFrom = idx;
                }
            }

            result.add(counter);
            i++;
            lower = upper;
        } while (lower <= endTime);

        return result;
    }

    private static class Range {
        private final int lower, upper;

        public Range(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }

        public boolean isInRange(int value) {
            return value >= lower && value < upper;
        }
    }
}
