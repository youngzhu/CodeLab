package com.youngzy.leetcode.string;

/**
 * 字符串S和 T 只包含小写字符。在S中，所有字符只会出现一次。
 *
 * S 已经根据某种规则进行了排序。我们要根据S中的字符顺序对T进行排序。
 * 更具体地说，如果S中x在y之前出现，那么返回的字符串中x也应出现在y之前。
 *
 * 返回任意一种符合条件的字符串T。
 *
 * 示例:
 * 输入:
 * S = "cba"
 * T = "abcd"
 * 输出: "cbad"
 * 解释:
 * S中出现了字符 "a", "b", "c", 所以 "a", "b", "c" 的顺序应该是 "c", "b", "a".
 * 由于 "d" 没有在S中出现, 它可以放在T的任意位置. "dcba", "cdba", "cbda" 都是合法的输出。
 *
 * 注意:
 *     S的最大长度为26，其中没有重复的字符。
 *     T的最大长度为200。
 *     S和T只包含小写字符。
 *
 * 链接：https://leetcode-cn.com/problems/custom-sort-string
 */
public class CustomSortString {

    /**
     * 官解是0，我的是 16，学习一下
     *
     * 1 统计每个字母在 T 中出现的次数
     * 2 遍历S，按顺序和出现的次数（1）拼接一个新的字符串
     * 3 将 T 中有，S 无 的字母拼接在最后
     *
     * 对比一下，大概是败在了替换上。
     * 所以，替换操作要慎用！！！！
     *
     * @param S
     * @param T
     * @return
     */
    public String customSortString(String S, String T) {
        StringBuilder ret = new StringBuilder();

        // 1 统计每个字母在 T 中出现的次数
        int[] appearTimes = new int[26];
        for (char ch : T.toCharArray()) {
            appearTimes[ch - 'a'] ++;
        }

        // 2 遍历S，按顺序和出现的次数（1）拼接一个新的字符串
        for (char ch : S.toCharArray()) {
            for (int i = 0; i < appearTimes[ch - 'a']; i ++) {
                ret.append(ch);
            }

            appearTimes[ch - 'a'] = 0;
        }

        // 3 将 T 中有，S 无 的字母拼接在最后
        /*
        for(int i = 0; i < appearTimes.length; i ++) {
            for (int cnt = 0; cnt < appearTimes[i]; cnt ++) {
                ret.append((char)('a' + i));
            }
        }
        */

        // 官方用这个循环也蛮巧妙的
        for (char ch = 'a'; ch <= 'z'; ch ++) {
            for (int cnt = 0; cnt < appearTimes[ch - 'a']; cnt ++) {
                ret.append(ch);
            }
        }

        return ret.toString();
    }

    /**
     * 跟方法1 居然没啥区别。。。
     * @param S
     * @param T
     * @return
     */
    public String customSortString2(String S, String T) {
        StringBuilder ret = new StringBuilder();

        for (char ch : S.toCharArray()) {
            while (T.indexOf(ch) != -1) {
                ret.append(ch);
                T = T.replaceFirst(ch + "", "");
            }
        }

        ret.append(T);

        return ret.toString();
    }

    public String customSortString1(String S, String T) {
        /*
        每个字母在S中的顺序
        下标 i 代表字母 ch - 'a'
        值 charOrder[i] 代表顺序
         */
        int[] charOrder = new int[26];
        int order = 1;
        for (char ch : S.toCharArray()) {
            charOrder[ch - 'a'] = order ++;
        }

        /*
        按顺序给字母排座
        下标即顺序
        值就是对应的字母
        即，
        arr[0]=
         */
        String[] orderedStr = new String[26];

        for(int i = 0; i < charOrder.length; i ++) {
            if (charOrder[i] > 0) {
                char ch = (char) ('a' + i);
                orderedStr[charOrder[i] - 1] = "" + ch;
            }
        }

        StringBuilder ret = new StringBuilder();

        int i = 0;
        while (true) {
            if (orderedStr[i] == null) {
                break;
            }

            while (T.indexOf(orderedStr[i]) != -1) {
                ret.append(orderedStr[i]);
                T = T.replaceFirst(orderedStr[i], "");
            }

            i ++;
        }

        ret.append(T);

        return ret.toString();
    }
}
