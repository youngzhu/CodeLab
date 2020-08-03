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
public class RankTeamsByVotes {
    public String rankTeams(String[] votes) {
        int maxNo = votes[0].length(); // 最大名次，也即人数，从0开始
        char[] result = new char[maxNo];

        HashSet<Team> done = new HashSet<>(maxNo); // 已排序的字母集合
        // 每个名次的投票结果
        Set<Team> eachNo[] = getEachVote(votes);

        // 剩余未被排序的
        Set<Team> remain = new HashSet<>();

        int no = 0;
        while (no < maxNo) {
            System.out.println(no);
            if (remain.size() == 1) {
                List<Team> list = new ArrayList(remain);
                done.add(list.get(0));
                result[no++] = list.get(0).code;

                remain.clear();
                continue;
            }

            remain.addAll(eachNo[no]);
            // 将已排序的过滤掉
            for (Team team : done) {
                eachNo[no].remove(team);
                remain.remove(team);
            }
            if (eachNo[no].size() == 1) {
                List<Team> list = new ArrayList(eachNo[no]);
                done.add(list.get(0));
                remain.remove(list.get(0));
                result[no++] = list.get(0).code;
            } else {
                // 有并列
                Set<Team> xx = eachNo[no];
                List<Team> list = new ArrayList<>(xx);
                Collections.sort(list);

                Stack<Team> paralleling = new Stack<>(); // 并列
                for (Team team : list) {
                    if (paralleling.isEmpty()) {
                        paralleling.add(team);
                    } else {
                        if (team.compareTo(paralleling.peek()) == 0) {
                            paralleling.add(team);
                        } else {
                            break;
                        }
                    }

                }

                while (paralleling.size() != 0) {
                    if (paralleling.size() == 1) {
                        done.add(paralleling.get(0));
                        result[no++] = paralleling.get(0).code;

                        remain.remove(paralleling.get(0));
                        paralleling.pop();
                        break;
                    }

                    // 查看下个名次
                    Stack<Team> nextParalleling = new Stack<>();
                    int offset = 1;
                    while (no + offset < maxNo
                            && nextParalleling.isEmpty()) {

                        list = new ArrayList<>();
                        for (Team c : paralleling) {
                            if (eachNo[no + offset].contains(c)) {
                                list.add(c);
                            }
                        }

                        Collections.sort(list);

                        for (Team team : list) {
                            if (nextParalleling.isEmpty()) {
                                nextParalleling.add(team);
                            } else {
                                if (team.compareTo(nextParalleling.peek()) == 0) {
                                    nextParalleling.add(team);
                                } else {
                                    break;
                                }
                            }

                        }

                        remain.addAll(eachNo[no + offset]);
                        offset++;
                    }

                    if (nextParalleling.size() == 1) {
                        Team team = nextParalleling.pop();

                        done.add(team);
                        paralleling.remove(team);
                        remain.remove(team);
                        result[no++] = team.code;
                    } else {
                        no++;
                        break;
                    }

                }

            }

        }

        return new String(result);
    }

    /**
     * 获得每个名次的投票结果
     *
     * @param votes
     * @return
     */
    private Set<Team>[] getEachVote(String[] votes) {
        int maxNo = votes[0].length(); // 最大名次，也即人数，从0开始
        Set<Team> set[] = new Set[maxNo];

        // 初始化
//        for (int i = 0; i < maxNo; i++) {
//            set[i] = new HashSet<>();
//        }

        for (int no = 0; no < maxNo; no++) {
            int[] arr = new int[26];
            for (String vote : votes) {
                char c = vote.charAt(no);
                arr[c - 'A'] ++;
            }

            Set<Team> teams = new HashSet<>();
            for (char c = 'A'; c <= 'Z'; c++) {
                int times = arr[c - 'A'];
                if (times > 0) {
                    Team team = new Team(c, times);
                    teams.add(team);
                }
            }

            set[no] = teams;
        }

        return set;
    }

    /**
     * 辅助类
     */
    private static class Team implements Comparable {
        Character code;// 代号
        Integer times; // 某一名次被提名的次数

        public Team(Character code, int times) {
            this.code = code;
            this.times = times;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Team team = (Team) o;
            return code.equals(team.code);
        }

        @Override
        public int hashCode() {
            return code.hashCode();
        }

        @Override
        public int compareTo(Object o) {
            if (this == o) return 0;
            if (o == null || getClass() != o.getClass()) return 1;
            Team team = (Team) o;
            return - times.compareTo(team.times);
        }
    }
}
