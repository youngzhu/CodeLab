package com.youngzy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 */
public class RestoreIPAddresses {

    // 每个ip段的最大和最小长度
    private int SEGMENT_LEN_MAX = 3;
    private int SEGMENT_LEN_MIN = 1;
    private int DEPTH = 4; // IP段数量（深度）

    /**
     * 回溯法
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();

        // 小于最小长度或者 大于最大长度
        if (s.length() < SEGMENT_LEN_MIN * DEPTH || s.length() > SEGMENT_LEN_MAX * DEPTH) {
            return ans;
        }

        // 存放每一段IP，如["10", "10", "0", "1"]
        String[] segmentArr = new String[DEPTH]; //
        backtrack(ans, s, 1, 0, segmentArr);

        return ans;
    }

    /**
     * 回溯
     */
    private void backtrack(List<String> ans, String s, int depth, int startIdx, String[] segmentArr) {
        String segment; // ip 段
        String ip; // 完整的ip
        for (int len = 1; len <= SEGMENT_LEN_MAX; len++) {
            // 剩下长度
            int overlen = s.length() - (startIdx + len);
            // 剩下的长度小于最小长度或大于 最大长度，跳过
            if (overlen < SEGMENT_LEN_MIN * (DEPTH - depth)
                    || overlen > SEGMENT_LEN_MAX * (DEPTH - depth)) {
                continue;
            }

//            System.out.println(startIdx + ", " + len);
            segment = s.substring(startIdx, startIdx + len);

            if (isValidSegment(segment)) {
                segmentArr[depth - 1] = segment;
                if (depth == DEPTH) {
                    ip = String.join(".", segmentArr);
                    ans.add(ip);
                } else {
                    backtrack(ans, s, depth + 1, startIdx + len, segmentArr);
                }
            }

        }
    }

    /**
     * 是否是一个有效的IP段
     * 1. 数字不以0开头，如08
     * 2. <= 255
     *
     * @param segment
     * @return
     */
    private boolean isValidSegment(String segment) {
        int ipNum = Integer.valueOf(segment);

        // s + "", 跟 valueOf 效率大不一样
        if (segment.length() == String.valueOf(ipNum).length()
                && ipNum <= 255) {
            return true;
        }
        return false;
    }

    public List<String> restoreIpAddresses1(String s) {
        List<String> list = new ArrayList<>();

        String a, b, c, d;
        // 每一段的长度，最小为 1
        int alen, blen, clen, dlen;
        int aval, bval, cval, dval; // 每段的十进制数值
        StringBuilder ip;

        if (s.length() > 12) {
            return list;
        }

        for (alen = 1; alen <= 3; alen++) {
            for (blen = 1; blen <= 3; blen++) {
                for (clen = 1; clen <= 3; clen ++) {
                    for (dlen = 1; dlen <= 3; dlen ++) {
                        // 减的这种，效率更低
                        // 不知道为什么。。
//                    for (dlen = 1; dlen <= s.length() - alen - blen - clen; dlen ++) {
                        if (alen + blen + clen + dlen == s.length()) {
                            a = s.substring(0, alen);
                            b = s.substring(alen, alen + blen);
                            c = s.substring(alen + blen, alen + blen + clen);
                            d = s.substring(alen + blen + clen, s.length());

                            aval = Integer.valueOf(a);
                            bval = Integer.valueOf(b);
                            cval = Integer.valueOf(c);
                            dval = Integer.valueOf(d);

                            // 数字不能0开头
                            // 例如，010 是不对的，只能是 10
                            if (!a.equals(String.valueOf(aval))
                                    || !b.equals(String.valueOf(bval))
                                    || !c.equals(String.valueOf(cval))
                                    || !d.equals(String.valueOf(dval))
                                ) {
                                continue;
                            }

                            if (aval <= 255 && bval <= 255 && cval <= 255 && dval <= 255) {
                                ip = new StringBuilder(a);
                                ip.append(".").append(b)
                                    .append(".").append(c)
                                    .append(".").append(d);

                                list.add(ip.toString());

                            }

                        }

                    }
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
        RestoreIPAddresses r = new RestoreIPAddresses();

        r.restoreIpAddresses("25525511135");
    }
}
