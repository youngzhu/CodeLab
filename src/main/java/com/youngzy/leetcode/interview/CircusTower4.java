package com.youngzy.leetcode.interview;


import java.util.Arrays;

/**
 * 面试题 17.08. 马戏团人塔
 *
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。
 * 出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。
 * 已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 *
 * 示例：
 * 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
 * 输出：6
 * 解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
 *
 * 提示：height.length == weight.length <= 10000
 *
 * 链接：https://leetcode-cn.com/problems/circus-tower-lcci
 */
public class CircusTower4 {
    /**
     * 还有个C++用递归的
     * https://stackoverflow.com/questions/39062202/adding-memoization-dynamic-programming
     *
     * 算法可以，但还是超时。。。
     *
     * @param height
     * @param weight
     * @return
     */
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int[] answer = new int[height.length];

        Person[] persons = new Person[height.length];

        for (int i = 0; i < height.length; i++) {
            answer[i] = 1;
            persons[i] = new Person(height[i], weight[i]);
        }
        Arrays.sort(persons);
        dp(persons, 0, persons.length - 1, answer);

//        Arrays.sort(answer);
//        return answer[answer.length - 1];
        int result = 0;
        for (int i = 0; i < answer.length; i ++) {
            if (result < answer[i]) {
                result = answer[i];
            }
        }
        return result;
    }

    private int dp(Person[] persons, int start, int end, int[] answer) {
        if (start == persons.length - 1) {
            return answer[start];
        }

        dp(persons, start + 1, persons.length - 1, answer);

        int idx = start + 1;
        while (idx <= end) {
            if (answer[start] < answer[idx] + 1
                    && persons[start].height < persons[idx].height
                    && persons[start].weight < persons[idx].weight) {
                answer[start] = answer[idx] + 1;
                // 不能break，只能一个个地去比较
//                break;
            }

            // 就算后面的（idx~end）都满足，还是小于已有值answer[start]
            // 说明已经是最优了
            // 对效率好像没影响。。
            if (answer[start] > answer[idx] + (end - idx)) {
                break;
            }
            idx ++;
        }

        return answer[start];
    }

    private static class Person implements Comparable<Person> {
        int height, weight;

        public Person(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + height +
                    ", " + weight + ") ";
        }

        @Override
        public int compareTo(Person o) {
            if (this.height < o.height) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
