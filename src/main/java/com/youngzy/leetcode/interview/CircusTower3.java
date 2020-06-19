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
public class CircusTower3 {
    /**
     * 好不容易在网上找了一个算法，居然超时了。。
     * https://stackoverflow.com/questions/38194187/algorithm-for-circus-tower
     *
     * @param height
     * @param weight
     * @return
     */
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int[] answer = new int[height.length];

        Person[] persons = new Person[height.length];
        String[] details = new String[height.length];

        for (int i = 0; i < height.length; i++) {
            answer[i] = 1;
            persons[i] = new Person(height[i], weight[i]);
            details[i] = persons[i].toString();
        }
        Arrays.sort(persons);
//        for (int i = 0; i < persons.length; i++) {
//                System.out.print(persons[i]);
//        }
        System.out.println();
        int result = 1;

        for (int i = 1; i < persons.length; i ++) {
            for (int j = 0; j < i; j++) {
                if (answer[j] + 1 > answer[i] && persons[i].weight > persons[j].weight
                    && persons[i].height > persons[j].height) {

                    answer[i] = answer[j] + 1;
                    details[i] = details[j] + persons[i];
                    if (result < answer[i]) {
                        result = answer[i];
                    }
                }

            }
        }

        for (int i = 0; i < persons.length; i++) {
            if (answer[i] == result) {
                System.out.println(details[i]);
            }
        }
        return result;
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
