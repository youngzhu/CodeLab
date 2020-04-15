package com.youngzy.leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。
 * 但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 *     I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 *     X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 *     C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 *
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * 示例 1:
 * 输入: "III"
 * 输出: 3
 *
 * 示例 2:
 * 输入: "IV"
 * 输出: 4
 *
 * 示例 5:
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 */
public class RomanToInteger {

    /**
     * 暂存前一个数字，遍历后面的数字
     * 如果前一个小于当前的，就减，否则加
     *
     * 跟2比较，时间上稍快，空间上减少明显
     *
     * @param romanNumeral
     * @return
     */
    public int romanToInt(String romanNumeral) {
        int ans = 0;

        int preIntVal = getInt(romanNumeral.charAt(0));
        int curIntVal;
        for (int i = 1; i < romanNumeral.length(); i++) {
            curIntVal = getInt(romanNumeral.charAt(i));

            if (preIntVal < curIntVal) {
                ans -= preIntVal;
            } else {
                ans += preIntVal;
            }

            preIntVal = curIntVal;
        }

        // 加上最后的值
        ans += preIntVal;

        return ans;
    }

    /**
     * 听说用switch更快
     *
     * 时间差不多快了一倍，空间没区别
     *
     * @param romanNumeral
     * @return
     */
    public int romanToInt2(String romanNumeral) {
        int positive = 0;
        int negative = 0;

        // 由于用到下标 i+1
        // 所以，最大的下标是 s.length-1-1
        int i = 0;
        while (i <= romanNumeral.length() - 2) {
            char c1 = romanNumeral.charAt(i);
            char c2 = romanNumeral.charAt(i + 1);

            int intVal1 = getInt(c1);
            int intVal2 = getInt(c2);

            if (intVal1 < intVal2) {
                // 负数，要减
                negative += intVal1;
                positive += intVal2;
                i = i + 1;
            } else {
                // 正数，直接加
                positive += intVal1;
            }

            i++;
        }

        // 处理最后一个字母
        if (i == romanNumeral.length() - 1) {
            char c = romanNumeral.charAt(i);
            int intVal = getInt(c);
            positive += intVal;
        }

        return positive - negative;
    }

    private int getInt(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }


    /**
     * 效率还是不行啊，跟上一个差不多，咋回事？？
     *
     * 将正数和负数（要减的数）分别统计，最后累计
     *
     * 发生相减的情况：
     * 1. 必定是2个字母
     * 2. 前一个字母小于后一个的数值
     *
     * @param romanNumeral
     * @return
     */
    public int romanToInt1(String romanNumeral) {
        Map<Character, Integer> romanVSInt = new HashMap<Character, Integer>(){
            {
                put('I', 1);
                put('V', 5);
                put('X', 10);
                put('L', 50);
                put('C', 100);
                put('D', 500);
                put('M', 1000);
            }
        };

        int positive = 0;
        int negative = 0;

        // 由于用到下标 i+1
        // 所以，最大的下标是 s.length-1-1
        int i = 0;
        while (i <= romanNumeral.length() - 2) {
            char c1 = romanNumeral.charAt(i);
            char c2 = romanNumeral.charAt(i + 1);

            if (romanVSInt.get(c1) < romanVSInt.get(c2)) {
                // 负数，要减
                negative += romanVSInt.get(c1);
                positive += romanVSInt.get(c2);
                i = i + 1;
            } else {
                // 正数，直接加
                positive += romanVSInt.get(c1);
            }

            i++;
        }

        // 处理最后一个字母
        if (i == romanNumeral.length() - 1) {
            char c = romanNumeral.charAt(i);
            positive += romanVSInt.get(c);
        }

        return positive - negative;
    }

    /**
     * 很久之前写过的方法，效率不行
     *
     * 跟网友们相比，结构太复杂了。。。
     *
     * @param romanNumeral
     * @return
     */
    public int romanToInt0(String romanNumeral) {
        int arabicNumeral = 0;

        Stack<RomanNumeral> stack = new Stack<RomanNumeral>();

        RomanNumeral preRomanNum = null, curRomanNum;
        for (int i = 0; i < romanNumeral.length(); i++) {
            String romanLetter = romanNumeral.substring(i, i + 1);

            curRomanNum = RomanNumeral.valueOf(romanLetter);

            if (! stack.empty()) {
                preRomanNum = stack.peek();
            }

            if (preRomanNum != null) {
                // 当前字母代表的数字比前一个大
                // 要使用减法
                if (preRomanNum.ordinal() > curRomanNum.ordinal()) {
                    arabicNumeral += curRomanNum.intValue() - stack.pop().intValue();

                    continue;
                }
            }

            stack.push(curRomanNum);
        }

        while (! stack.empty()) {
            arabicNumeral += stack.pop().intValue();
        }

        return arabicNumeral;
    }

    enum RomanNumeral {
        M(1000, 3, 0),
        D(500, 1, 0),
        C(100, 3, 1),
        L(50, 1, 0),
        X(10, 3, 1),
        V(5, 1, 0),
        I(1, 3, 1);

        private int intValue;
        private int maxRepeatTimes;
        private int canBeSubtracted; // 1-Y;0-N

        private RomanNumeral(int intValue, int maxRepeatTimes, int canBeSubtracted) {
            this.intValue = intValue;
            this.maxRepeatTimes = maxRepeatTimes;
            this.canBeSubtracted = canBeSubtracted;
        }

        public int intValue() {
            return this.intValue;
        }

        public int maxRepeatTimes() {
            return this.maxRepeatTimes;
        }

        public int canBeSubtracted() {
            return this.canBeSubtracted;
        }

    }
}
