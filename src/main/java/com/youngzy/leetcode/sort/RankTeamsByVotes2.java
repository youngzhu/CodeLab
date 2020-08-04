package com.youngzy.leetcode.sort;

import java.util.*;

/**
 * 参考了别人的写法，原来如此简单。。
 */
public class RankTeamsByVotes2 {
    public String rankTeams(String[] votes) {
        Map<Character, Team> map = buildTeam(votes);
        List<Team> list = new ArrayList<>(map.values());

        // 排名
        Collections.sort(list, new TeamComparator());

        // 排名后按顺序输出
        char[] ans = new char[list.size()];
        for (int i = 0; i < list.size(); i ++) {
            ans[i] = list.get(i).code;
        }

        return new String(ans);
    }

    private Map<Character, Team> buildTeam(String[] votes) {
        Map<Character, Team> map = new HashMap<>();

        // 利用第一轮投票，把所有team都放到map中
        // 后面就不用判断是否存在，直接get就行了
        String firstRound = votes[0];
        for (int rank = 0; rank < firstRound.length(); rank ++) {
            char code = firstRound.charAt(rank);
            Team team = new Team(code, rank);
            map.put(code, team);
        }

        if (votes.length > 1) {
            for (int i = 1; i < votes.length; i ++) {
                for (int rank = 0; rank < firstRound.length(); rank ++) {
                    char code = votes[i].charAt(rank);
                    Team team = map.get(code);
                    team.ranking[rank] ++; // 获票次数加一
                }
            }
        }

        return map;
    }

    /**
     * 辅助类
     */
    private static class Team {
        Character code;// 代号
        // 名次
        // idx 表示名次，0-25
        // 值 表示该名次被提名的次数
        // 例如，ranking[0]=2，表示第一名有2票
        int[] ranking = new int[26];

        public Team(Character code, int rank) {
            this.code = code;
            ranking[rank] = 1;
        }
    }

    private static class TeamComparator implements Comparator<Team> {

        @Override
        public int compare(Team o1, Team o2) {
            int[] ranking1 = o1.ranking;
            int[] ranking2 = o2.ranking;

            for (int i = 0; i < ranking1.length; i++) {
                // 票数多的在前，所以 2 - 1
                int dif = ranking2[i] - ranking1[i];
                if (dif != 0) {
                    // 比较票数
                    return dif;
                }

            }

            // 票数相同，则按字母顺序
            return o1.code.compareTo(o2.code);
        }
    }
}
