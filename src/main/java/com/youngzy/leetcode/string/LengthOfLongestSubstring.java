package com.youngzy.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
public class LengthOfLongestSubstring {

    /**
     * 在方法2的基础上，验证网友的一个设想：提前结束循环
     *
     * 从 i 到末尾的长度小于等于现在的answer，即可退出循环：
     * 即： len - 1 - i + 1 <= answer
     *
     * 验证结果：基本无差别
     *
     */
    public int lengthOfLongestSubstring(String s) {
        int answer = 0; // 返回结果

        // 用来存放字符对应的最大下标
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j ++) {

            if (s.length() - i <= answer) {
                break;
            }

            if (map.containsKey(s.charAt(j))) {
                // 重复字符
                // i = j' + 1
                // 不能开倒车，所以取max，如 abab
                i = Math.max(i, map.get(s.charAt(j))  + 1);
            }

            // 试过把这句放在循环的第一句，不行
            // 那表示每次取[i, j - 1]的长度（j为重复字符，不计算在内），"au"将其反驳了
            // 所以还是要计算[i , j) 的长度，其中 i 是移动后的
            answer = Math.max(answer, j - i + 1);

            map.put(s.charAt(j), j);

        }

        return answer;
    }

    /**
     * 本来想把answer的求值放循环第一行的，失败！！！
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        int ans = 0;
        int answer = 0; // 返回结果

        // 用来存放字符对应的最大下标
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j ++) {
            answer = Math.max(answer, j - i);
            ans = Math.max(ans, answer);

            if (map.containsKey(s.charAt(j))) {
                // 重复字符
                // i = j' + 1
                // 不能开倒车，所以取max，如 abab
                i = Math.max(i, map.get(s.charAt(j))  + 1);
                answer = 1;
            } else {
                answer = answer + 1;
            }

            map.put(s.charAt(j), j);

        }

        return ans;
    }

    /**
     * 方法1的升级版
     *
     * 方法1是将窗口的左边界逐个移动
     * 本方法是跳着移：s[j] 在窗口[i,j)中有重复字符s[j']，i = j' + 1
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int answer = 0; // 返回结果

        // 用来存放字符对应的最大下标
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j ++) {

            if (map.containsKey(s.charAt(j))) {
                // 重复字符
                // i = j' + 1
                // 不能开倒车，所以取max，如 abab
                i = Math.max(i, map.get(s.charAt(j))  + 1);
            }

            // 试过把这句放在循环的第一句，不行
            // 那表示每次取[i, j - 1]的长度（j为重复字符，不计算在内），"au"将其反驳了
            // 所以还是要计算[i , j) 的长度，其中 i 是移动后的
            answer = Math.max(answer, j - i + 1);

            map.put(s.charAt(j), j);

        }

        return answer;
    }

    /**
     * 使用HashSet作为滑动窗口
     * [start, end)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        int answer = 0; // 返回结果

        int len = s.length(), start = 0, end = 0;

        Set<Character> set = new HashSet<Character>();
        while (end < len){
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end++));

                answer = Math.max(answer, end - start);
            } else {
                // 将窗口最左边的字符删除
                // 并将窗口的左下标 start 右移一位，
                set.remove(s.charAt(start ++));
            }
        }

        return answer;
    }

    /**
     * copy 的
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring0(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

}
