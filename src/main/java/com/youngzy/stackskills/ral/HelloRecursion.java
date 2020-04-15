package com.youngzy.stackskills.ral;

public class HelloRecursion {
    /**
     * 将一个字符串反转
     *
     * @param str
     * @return
     */
    public String reverseString(String str) {
//        return iterativeSolution(str);
        return recursiveSolution(str);
    }

    /**
     * 常规解法 - 迭代（循环）
     * 第1个和倒数第1个交换
     * 第2个和倒数第2个交换
     * ...
     * 交换的次数=字符串长度的一半
     *
     * @param str
     * @return
     */
    private String iterativeSolution(String str) {
        if (str.length() < 2) {
            return str;
        }

        char[] charArr = str.toCharArray();
        char swap;
        int left = 0, right = str.length() - 1;
        while (left < right) {
            swap = charArr[left];
            charArr[left] = charArr[right];
            charArr[right] = swap;

            left ++;
            right --;
        }

        return new String(charArr);
    }

    /**
     * 递归解法
     *
     * 将字符串拆成二部分看：
     * 第一部分，就是第1个字符
     * 第二部分，就是剩下的字符
     *
     * 假设第二部分是已经完成反转的字符，那接下来要做的就是把这两部分反转，
     * 即，把第一部分字符放到第二部分的后面
     *
     * @param str
     * @return
     */
    public String recursiveSolution(String str) {
        if (str.length() < 2) {
            return str;
        }

        final String part1 = str.substring(0, 1);
        final String part2 = str.substring(1);
        return recursiveSolution(part2) + part1;
    }
}
