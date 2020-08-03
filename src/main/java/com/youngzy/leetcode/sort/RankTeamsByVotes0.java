package com.youngzy.leetcode.sort;

import java.util.*;

/**
 * 1366. 通过投票对团队排名
 *
 * 现在有一个特殊的排名系统，依据参赛团队在投票人心中的次序进行排名，每个投票者都需要按从高到低的顺序对参与排名的所有团队进行排位。
 *
 * 排名规则如下：
 * 参赛团队的排名次序依照其所获「排位第一」的票的多少决定。
 * 如果存在多个团队并列的情况，将继续考虑其「排位第二」的票的数量。以此类推，直到不再存在并列的情况。
 * 如果在考虑完所有投票情况后仍然出现并列现象，则根据团队字母的字母顺序进行排名。
 *
 * 给你一个字符串数组 votes 代表全体投票者给出的排位情况，请你根据上述排名规则对所有参赛团队进行排名。
 * 请你返回能表示按排名系统 排序后 的所有团队排名的字符串。
 *
 * 示例 1：
 * 输入：votes = ["ABC","ACB","ABC","ACB","ACB"]
 * 输出："ACB"
 * 解释：A 队获得五票「排位第一」，没有其他队获得「排位第一」，所以 A 队排名第一。
 * B 队获得两票「排位第二」，三票「排位第三」。
 * C 队获得三票「排位第二」，两票「排位第三」。
 * 由于 C 队「排位第二」的票数较多，所以 C 队排第二，B 队排第三。
 *
 * 示例 2：
 * 输入：votes = ["WXYZ","XYZW"]
 * 输出："XWYZ"
 * 解释：X 队在并列僵局打破后成为排名第一的团队。X 队和 W 队的「排位第一」票数一样，但是 X 队有一票「排位第二」，而 W 没有获得「排位第二」。
 *
 * 示例 3：
 * 输入：votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"]
 * 输出："ZMNAGUEDSJYLBOPHRQICWFXTVK"
 * 解释：只有一个投票者，所以排名完全按照他的意愿。
 *
 * 示例 4：
 * 输入：votes = ["BCA","CAB","CBA","ABC","ACB","BAC"]
 * 输出："ABC"
 * 解释：
 * A 队获得两票「排位第一」，两票「排位第二」，两票「排位第三」。
 * B 队获得两票「排位第一」，两票「排位第二」，两票「排位第三」。
 * C 队获得两票「排位第一」，两票「排位第二」，两票「排位第三」。
 * 完全并列，所以我们需要按照字母升序排名。
 *
 * 示例 5：
 * 输入：votes = ["M","M","M","M"]
 * 输出："M"
 * 解释：只有 M 队参赛，所以它排名第一。
 *
 * 提示：
 * 1 <= votes.length <= 1000
 * 1 <= votes[i].length <= 26
 * votes[i].length == votes[j].length for 0 <= i, j < votes.length
 * votes[i][j] 是英文 大写 字母
 * votes[i] 中的所有字母都是唯一的
 * votes[0] 中出现的所有字母 同样也 出现在 votes[j] 中，其中 1 <= j < votes.length
 *
 * 链接：https://leetcode-cn.com/problems/rank-teams-by-votes
 */
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
