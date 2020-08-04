package com.youngzy.leetcode.sort;

import java.util.*;

/**
 * 好不容易写出来了，还是败在了测试案例上
 * 太长了，懒得找失败原因了
 */
public class RankTeamsByVotes1 {
    public String rankTeams(String[] votes) {
        int maxNo = votes[0].length(); // 最大名次，也即人数，从0开始
        char[] result = new char[maxNo];

        HashSet<Team> done = new HashSet<>(maxNo); // 已排序的字母集合
        // 每个名次的投票结果
        Set<Team> eachNo[] = getEachVote(votes);

        // 剩余未被排序的
        Set<Team> remain = new HashSet<>();

        Stack<Team> paralleling = new Stack<>(); // 并列

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
                int idx = no - 1;
                while (! paralleling.isEmpty()) {
                    // 并列，按字母顺序输出
                    result[idx --] = paralleling.pop().code;
                }

                List<Team> list = new ArrayList(eachNo[no]);
                done.add(list.get(0));
                remain.remove(list.get(0));
                result[no++] = list.get(0).code;
            } else {
                // 有并列
                Set<Team> xx = eachNo[no];
                List<Team> list = new ArrayList<>(xx);
                Collections.sort(list);

                paralleling = new Stack<>(); // 并列
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

        int idx = result.length - 1;
        while (! paralleling.isEmpty()) {
            // 并列，按字母顺序输出
            result[idx --] = paralleling.pop().code;
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
