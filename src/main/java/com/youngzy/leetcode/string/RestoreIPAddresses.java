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
    public List<String> restoreIpAddresses(String s) {
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
