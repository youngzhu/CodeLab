package com.youngzy.leetcode.string;

import java.util.*;

/**
 * 1452. 收藏清单
 *
 * 给你一个数组 favoriteCompanies ，其中 favoriteCompanies[i] 是第 i 名用户收藏的公司清单（下标从 0 开始）。
 * 请找出不是其他任何人收藏的公司清单的子集的收藏清单，并返回该清单下标。下标需要按升序排列。
 *
 * 示例 1：
 * 输入：favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
 * 输出：[0,1,4]
 * 解释：
 * favoriteCompanies[2]=["google","facebook"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集。
 * favoriteCompanies[3]=["google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 和 favoriteCompanies[1]=["google","microsoft"] 的子集。
 * 其余的收藏清单均不是其他任何人收藏的公司清单的子集，因此，答案为 [0,1,4] 。
 *
 * 示例 2：
 * 输入：favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
 * 输出：[0,1]
 * 解释：favoriteCompanies[2]=["facebook","google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集，因此，答案为 [0,1] 。
 *
 * 示例 3：
 * 输入：favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
 * 输出：[0,1,2,3]
 *
 * 提示：
 * 1 <= favoriteCompanies.length <= 100
 * 1 <= favoriteCompanies[i].length <= 500
 * 1 <= favoriteCompanies[i][j].length <= 20
 * favoriteCompanies[i] 中的所有字符串 各不相同 。
 * 用户收藏的公司清单也 各不相同 ，也就是说，即便我们按字母顺序排序每个清单， favoriteCompanies[i] != favoriteCompanies[j] 仍然成立。
 * 所有字符串仅包含小写英文字母。
 *
 * 链接：https://leetcode-cn.com/problems/people-whose-list-of-favorite-companies-is-not-a-subset-of-another-list
 */
public class FavoriteCompanies {

    /**
     * 把String转为Integer去比较，会不会节省空间？
     *
     *
     * @param favoriteCompanies
     * @return
     */
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> answer = new ArrayList<>();

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int index = 0; index < favoriteCompanies.size(); index++) {
            Set<Integer> set = new HashSet<>();
            for (String comp : favoriteCompanies.get(index)) {
                set.add(comp.hashCode());
            }
            map.put(index, set);
        }

        for (int i = 0; i < favoriteCompanies.size(); i++) {
            boolean isSub = false;

            for (int j = 0; j < favoriteCompanies.size(); j++) {
                if (i == j) {
                    continue;
                }

                if (map.get(j).containsAll(map.get(i))) {
                    isSub = true;
                    break;
                }

            }

            if (! isSub) {
                answer.add(i);
            }
        }

        return answer;
    }

    /**
     * 跟 1 比，把 new set 的代码提出来，不要放在循环里
     *
     * @param favoriteCompanies
     * @return
     */
    public List<Integer> peopleIndexes2(List<List<String>> favoriteCompanies) {
        List<Integer> answer = new ArrayList<>();

        Map<Integer, Set<String>> map = new HashMap<>();
        for (int index = 0; index < favoriteCompanies.size(); index++) {
            map.put(index, new HashSet<>(favoriteCompanies.get(index)));
        }

        for (int i = 0; i < favoriteCompanies.size(); i++) {
            boolean isSub = false;

            for (int j = 0; j < favoriteCompanies.size(); j++) {
                if (i == j) {
                    continue;
                }

                if (map.get(j).containsAll(map.get(i))) {
                    isSub = true;
                    break;
                }

            }

            if (! isSub) {
                answer.add(i);
            }
        }

        return answer;
    }

    public List<Integer> peopleIndexes1(List<List<String>> favoriteCompanies) {
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < favoriteCompanies.size(); i++) {
            boolean isSub = false;

            List<String> currentComp = favoriteCompanies.get(i);
            for (int j = 0; j < favoriteCompanies.size(); j++) {
                if (i == j) {
                    continue;
                }

                List<String> comp = favoriteCompanies.get(j);
                Set<String> set = new HashSet<>(comp);
                if (set.containsAll(currentComp)) {
                    isSub = true;
                    break;
                }

            }

            if (! isSub) {
                answer.add(i);
            }
        }

        return answer;
    }

    /**
     * 中间不能 remove
     * java.util.ConcurrentModificationException
     *
     * @param favoriteCompanies
     * @return
     */
    public List<Integer> peopleIndexes0(List<List<String>> favoriteCompanies) {
        List<Integer> answer = new ArrayList<>();

        for (int index = 0; index < favoriteCompanies.size(); index++) {
            List<String> currentCompanies = favoriteCompanies.get(index);

            boolean isSub = false;
            for (Integer idx : answer) {
                List<String> companies = favoriteCompanies.get(idx);
                if (companies.containsAll(currentCompanies)) {
                    isSub = true;
                }
                if (currentCompanies.containsAll(companies)) {
                    answer.remove(idx);
                }
            }

            if (! isSub) {
                answer.add(index);
            }
        }

        return answer;
    }
}
