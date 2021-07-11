package com.youngzy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 */
public class RegularExpressionUtil {
    /**
     * 获取 [] 之间的内容
     *
     * @param exp
     * @return
     */
    public static String[] getValuesBetweenBrackets(String exp) {
        List<String> result = new ArrayList<>();

        // 包含[] : (\\[(.*?)])
//        Pattern pattern = Pattern.compile("\\[(.*?)],*"); // 原始的
        // [] 中只有 [ 是特殊字符需要转义
        Pattern pattern = Pattern.compile("\\[([^]]+)]");
        Matcher matcher = pattern.matcher(exp);
//        matcher.find(); // 我嘞个去，一定要find，不管是直接调，还是放在while里

        while (matcher.find()) {
//            System.out.println(matcher.group(1));
            result.add(matcher.group(1));
        }

        return result.toArray(result.toArray(new String[0]));
    }
}
