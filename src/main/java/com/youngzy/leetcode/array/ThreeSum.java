package com.youngzy.leetcode.array;

import java.util.*;

/**
 * 哈，初版测试通过
 *
 * 60 ms, 在所有 Java 提交中击败了12.55%的用户
 * 41.8 MB, 在所有 Java 提交中击败了99.40%的用户
 */
public class ThreeSum implements IThreeSum {
    /**
     * 将数分为三组：0，正数，负数
     * 结果集有四种情况：
     * + - 0
     * - - +
     * + + -
     * 0 0 0
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) {
            return ans;
        }

        List<Integer> zeros = new ArrayList<>();
        // key - num
        // value - 出现的次数
        Map<Integer, Integer> positiveMap = new HashMap<>();
        Map<Integer, Integer> negativeMap = new HashMap<>();

        for (int i : nums) {
            int occurrences;

            if (i > 0) {
                occurrences = positiveMap.getOrDefault(i, 0);
                positiveMap.put(i, occurrences + 1);
            } else if (i < 0) {
                occurrences = negativeMap.getOrDefault(i, 0);
                negativeMap.put(i, occurrences + 1);
            } else {
                zeros.add(i);
            }
        }

        // 1 + - 0
        // 用set消除重复
        Set<Integer> positiveSet = positiveMap.keySet();
        Set<Integer> negativeSet = negativeMap.keySet();
        for (int pos : positiveSet) {
            int neg = -pos;
            if (negativeSet.contains(neg) && zeros.size() > 0) {
                ans.add(Arrays.asList(new Integer[]{pos, neg, 0}));
            }
        }

        // 2 - - +
        // 2.1 两负数一样
        // 2.2 两负数不一样
        oneTwo(ans, positiveSet, negativeMap, negativeSet);


        // 3 + + -
        oneTwo(ans, negativeSet, positiveMap, positiveSet);

        // 4 0 0 0
        if (zeros.size() >= 3) {
            ans.add(Arrays.asList(new Integer[]{0, 0, 0}));
        }

        return ans;
    }

    /**
     * 一负两正 或 一正两负
     */
    private void oneTwo(List<List<Integer>> ans, Set<Integer> oneSet, Map<Integer, Integer> twoMap, Set<Integer> twoSet) {
        // 找出出现2次以上的数
        List<Integer> dupList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : twoMap.entrySet()) {
            if (entry.getValue() > 1) {
                dupList.add(entry.getKey());
            }
        }
        // 相同符号，两数相等
        for (int twoNum : dupList) {
            int oneNum = - twoNum * 2;
            if (oneSet.contains(oneNum)) {
                ans.add(Arrays.asList(new Integer[]{twoNum, twoNum, oneNum}));
            }
        }

        // 相同符号，两数不等
        // 计算出相同符号数字的和
        Integer[] negativeArr = twoSet.toArray(new Integer[0]);
        Integer[][] negativeSum = new Integer[negativeArr.length][];
        for (int i = 0; i < negativeArr.length; i++) {
            for (int j = i + 1; j < negativeArr.length; j++) {
//                negativeSum[i][j] = negativeArr[i] + negativeArr[j];
                int pos = - (negativeArr[i] + negativeArr[j]);
                if (oneSet.contains(pos))
                    ans.add(Arrays.asList(new Integer[]{negativeArr[i], negativeArr[j], pos}));
            }
        }
    }
}
