package com.youngzy.leetcode.unionfind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 等式方程的可满足性
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
 * 并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 *
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
 *
 * 示例 1：
 * 输入：["a==b","b!=a"]
 * 输出：false
 * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 *
 * 示例 2：
 * 输出：["b==a","a==b"]
 * 输入：true
 * 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
 *
 * 示例 3：
 * 输入：["a==b","b==c","a==c"]
 * 输出：true
 *
 * 示例 4：
 * 输入：["a==b","b!=c","c==a"]
 * 输出：false
 *
 * 示例 5：
 * 输入：["c==c","b==d","x!=z"]
 * 输出：true
 *
 * 提示：
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] 和 equations[i][3] 是小写字母
 * equations[i][1] 要么是 '='，要么是 '!'
 * equations[i][2] 是 '='
 *
 * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
 */
public class EquationsPossible {

    /**
     * 试试 LinkList
     *  结果：没变化
     *
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);

        // 遍历方程，找出等式方程和不等式方程
        // 并将 等式方程做关联
        List<int[]> unequations = new LinkedList<>();

        for (String eq : equations) {
            char[] chars = eq.toCharArray();
            int x = chars[0] - 'a', y = chars[3] - 'a';

            if (chars[1] == '=') {
                if (! uf.isConnect(x, y)) {
                    uf.union(x, y);
                }
            } else {
                // 不等式
                unequations.add(new int[]{x, y});
            }
        }

        // 遍历不等式
        // 查找是否有关联的项
        // 如果有，则表示前后不一致，返回 false
        for (int[] node : unequations) {
            if (uf.isConnect(node[0], node[1])) {
                return false;
            }
        }

        return true;
    }

    /**
     * 遍历2遍数组，比新建一个列表存储不等式 要快，空间消耗差不多
     * 1ms VS 3ms
     *
     * @param equations
     * @return
     */
    public boolean equationsPossible2(String[] equations) {
        UnionFind uf = new UnionFind(26);

        for (String eq : equations) {
            char[] chars = eq.toCharArray();
            int x = chars[0] - 'a', y = chars[3] - 'a';

            if (chars[1] == '=') {
                if (! uf.isConnect(x, y)) {
                    uf.union(x, y);
                }
            }
        }

        for (String eq : equations) {
            char[] chars = eq.toCharArray();
            int x = chars[0] - 'a', y = chars[3] - 'a';

            if (chars[1] == '!') {
                if (uf.isConnect(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean equationsPossible1(String[] equations) {
        UnionFind uf = new UnionFind(26);

        // 遍历方程，找出等式方程和不等式方程
        // 并将 等式方程做关联
        List<int[]> unequations = new ArrayList<>();

        for (String eq : equations) {
            char[] chars = eq.toCharArray();
            int x = chars[0] - 'a', y = chars[3] - 'a';

            if (chars[1] == '=') {
                if (! uf.isConnect(x, y)) {
                    uf.union(x, y);
                }
            } else {
                // 不等式
                unequations.add(new int[]{x, y});
            }
        }

        // 遍历不等式
        // 查找是否有关联的项
        // 如果有，则表示前后不一致，返回 false
        for (int[] node : unequations) {
            if (uf.isConnect(node[0], node[1])) {
                return false;
            }
        }

        return true;
    }
}
