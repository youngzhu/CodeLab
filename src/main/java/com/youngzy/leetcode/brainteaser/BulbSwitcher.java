package com.youngzy.leetcode.brainteaser;

/**
 * 初始时有 n 个灯泡关闭。
 * 第 1 轮，你打开所有的灯泡。
 * 第 2 轮，每两个灯泡你关闭一次。
 * 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。
 * 第 i 轮，每 i 个灯泡切换一次开关。
 * 对于第 n 轮，你只切换最后一个灯泡的开关。 找出 n 轮后有多少个亮着的灯泡。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 1
 * 解释:
 * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
 * 第一轮后, 灯泡状态 [开启, 开启, 开启].
 * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
 * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
 *
 * 你应该返回 1，因为只有一个灯泡还亮着。
 *
 * 链接：https://leetcode-cn.com/problems/bulb-switcher
 */
public class BulbSwitcher {
    /**
     * 超时了。。。
     *
     * 脑筋需要转弯，不是算法可以解决的。。
     *
     * 题目要求可以理解为：每次将i的倍数的灯开关切换。
     * 我们可以发现，任何一个数总能拆解成两个数的乘积（假设编号为8的灯，会在第1、8次和2、4次被开关），
     * 从而导致开关状态不变。那么只有平方数的开关状态会被改变（自己和自己的乘积）。统计n以下的完全平方数个数即可。
     *
     * @param n
     * @return
     */
    public static int bulbSwitch(int n) {
        int[] bulbs = new int[n];
        // 0-关；1-开
        // 数组默认都是0，忽略

        for (int i = 1; i <= n; i++) {
            // 轮
            int idx = i;
            for (; idx <= n; idx += i) {
                // 切开关
                bulbs[idx - 1] ^= 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += bulbs[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        bulbSwitch(3);
    }
}
