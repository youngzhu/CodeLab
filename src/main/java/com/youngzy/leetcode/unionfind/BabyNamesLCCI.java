package com.youngzy.leetcode.unionfind;

import java.util.*;

/**
 * 婴儿名字
 *
 * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。
 * 有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。
 * 给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。
 * 注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
 *
 * 在结果列表中，选择字典序最小的名字作为真实名字。
 *
 * 示例：
 * 输入：
 * names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"],
 * synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
 * 输出：["John(27)","Chris(36)"]
 *
 * 提示：
 * names.length <= 100000
 *
 * 链接：https://leetcode-cn.com/problems/baby-names-lcci
 */
public class BabyNamesLCCI {
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        String[] result;

        // key:name, value:frequency
        Map<String, Integer> frequencyMap = new HashMap<>();
        // key:anotherName, value:realName
        // 由于后面的find方法，这里的key只能是anotherName，不能是 realName
        Map<String, String> rootMap = new HashMap<>();

        String name;
        Integer frequency;
        for (String data : names) {
            int idx = data.indexOf("(");
            name = data.substring(0, idx);
            frequency = Integer.valueOf(data.substring(idx + 1, data.length() - 1));

            frequencyMap.put(name, frequency);
        }

        // union
        String anotherName, realName;
        String name1, name2;
        for (String data : synonyms) {
//            data = data.substring(1, data.length() - 1);
//            String[] arr = data.split(",");
//            name1 = arr[0];
//            name2 = arr[1];

            // substring 比 split 效率高
            int idx = data.indexOf(',');
            name1 = data.substring(1, idx);
            name2 = data.substring(idx + 1, data.length() - 1);

            name1 = find(name1, rootMap);
            name2 = find(name2, rootMap);

            if (! name1.equals(name2)) {
                // 需要合并
                if (name1.compareTo(name2) < 0) {
                    anotherName = name2;
                    realName = name1;

                } else {
                    anotherName = name1;
                    realName = name2;
                }

                frequency = frequencyMap.getOrDefault(anotherName, 0)
                        + frequencyMap.getOrDefault(realName, 0);
                frequencyMap.put(realName, frequency);
                frequencyMap.remove(anotherName);

                rootMap.put(anotherName, realName);
            }

        }

        List<String> list = new ArrayList(frequencyMap.size());
        StringBuilder sb;
        // 在这里，拼接方式影响不大
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
//            String data = entry.getKey() + "(_)";
//            data = data.replace("_", entry.getValue().toString());
//
//            result.add(data);

            sb = new StringBuilder(entry.getKey());
            sb.append("(").append(entry.getValue()).append(")");
            list.add(sb.toString());
        }

        result = list.toArray(new String[]{});

        /*
        // 数组 比 列表稍逊
        result = new String[frequencyMap.size()];
        int idx = 0;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            String data = entry.getKey() + "(_)";
            data = data.replace("_", entry.getValue().toString());

            result[idx++] = data;
        }
         */

        return result;
    }

    public boolean isConnect(String name1, String name2, Map<String, String> rootMap) {
        return find(name1, rootMap).equals(find(name2, rootMap));
    }

    private String find(String name, Map<String, String> rootMap) {
        while (rootMap.containsKey(name)) {
            name = rootMap.get(name);
        }
        return name;
    }

    private class UF {
        private Map<String, Map<String, Integer>> root;

        public UF(String[] names) {
            root = new HashMap<>();

            for (String data : names) {

            }
        }
    }
}
