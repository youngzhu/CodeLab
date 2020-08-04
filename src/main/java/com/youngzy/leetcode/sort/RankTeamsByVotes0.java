package com.youngzy.leetcode.sort;

import java.util.*;

// 放弃，换个思路
public class RankTeamsByVotes0 {
    private static final Stack<Character> STACK_ALL = new Stack<>();
    static {
        for (char c = 'A'; c <= 'Z'; c++) {
            STACK_ALL.push(c);
        }
    }

    public String rankTeams(String[] votes) {
        int numAlphas = votes[0].length(); // 字母的个数
        List<Character> result = new ArrayList<Character>(numAlphas);

        // 第 i 名的所有人
        // i - 排名
        int[][] order = new int[numAlphas][26];

        // 遍历每一轮投票
        for (String vote : votes) {
            // 单次投票结果
            // index 就是顺序
            char[] rank = vote.toCharArray();

            for (int no = 1; no <= rank.length; no++) {
                char alpha = rank[no - 1];
                order[no - 1][alpha - 'A'] ++;
            }
        }

        Stack<Character> paralleling = new Stack<>(); // 并列的

        for (int no = 0; no < numAlphas; no ++) {
            int offset = 0;
            if (paralleling.isEmpty()) {
                paralleling.addAll(STACK_ALL);
                offset = 1;
            }
            recursion(paralleling, result, no, order, offset);
        }

        char[] arr = new char[result.size()];
        for (int i = 0; i < result.size(); i ++) {
            arr[i] = result.get(i);
        }
        return new String(arr);
    }

    private void recursion(Stack<Character> paralleling, List<Character> result, int no, int[][] order, int offset) {
        if (paralleling.size() == 1) {
            // 没有并列，直接返回
            result.add(paralleling.pop());
            return;
        }

        // 有问题：并列不为空，不应该直接看下一个名次
        // 而应该先处理本名次的

        // 有并列，看下一个名次
        int nextNo = Math.min(no + 1 - offset, order[0].length - 1);
//        if (order[0].length - 1 == nextNo || no == nextNo) {
//            return;
//        }
        int[] votesOfNext = order[nextNo];
        Iterator<Character> iter = paralleling.listIterator();

        Stack<Character> newParalleling = new Stack<>();

        int maxCount = 0; // 被排在 no 位最多的次数
        for (Character c : paralleling) {
            if (result.contains(c)) {
                continue;
            }
            int count = votesOfNext[c - 'A'];
            if (count > 0) {
                if (count == maxCount) {
                    // 并列
                    newParalleling.push(c);
                    continue;

                } else if (count > maxCount) {
                    maxCount = count;
                    // 先清空，再压入最新值
                    newParalleling.empty();
                    newParalleling.push(c);
                }
            }
        }

        // 不能直接赋值，值传递引用传递问题
//        paralleling = newParalleling;
        paralleling.clear();
        paralleling.addAll(newParalleling);
        System.out.println(no);
        recursion(paralleling, result, nextNo, order, 0);

    }

    /**
     * 用 序号之和判断顺序是不靠谱的。。
     * A： 2，2，2
     * B： 1，2，3
     *
     * 按和算，A应该排第一
     * 但其实，B应该排第一
     *
     * @param votes
     * @return
     */
    public String rankTeams0(String[] votes) {
        int numAlphas = votes[0].length(); // 字母的个数
        char[] result = new char[numAlphas];

        // 初始化字母序号总和
        int[] sumRank = new int[26];
//        for (int i = 0; i < 26; i++) {
//            sumRank[i] = -1;
//        }

        // 遍历每一个投票
        for (String vote : votes) {
            // 单次投票结果
            // index 就是顺序
            char[] rank = vote.toCharArray();

            for (int order = 1; order <= rank.length; order++) {
                char alpha = rank[order - 1];
                sumRank[alpha - 'A'] += order;
            }
        }

        // 遍历，找到序号和 最小的值
        Set<Character> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int preMin = -1;
        for (int i = 0; i < numAlphas; i++) {

            min = Integer.MAX_VALUE;

            for (char c = 'A'; c <= 'Z'; c++) {
                int rank = sumRank[c - 'A'];
                if (rank > 0 && rank < min
                        && rank >= preMin && ! set.contains(c)) {
                    min = rank;
                    result[i] = c;
                }
            }

            set.add(result[i]);
            preMin = min;
        }

        return new String(result);
    }
}
