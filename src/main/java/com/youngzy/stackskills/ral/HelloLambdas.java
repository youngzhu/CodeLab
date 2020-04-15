package com.youngzy.stackskills.ral;

import java.util.*;

public class HelloLambdas {
    /**
     * 给一组名字进行排序
     *
     * 要求："Liu De-hua" 始终排在第一
     *
     * @param args
     */
    public static void main(String[] args) {

        // 1. 初始化数据
        // 姓名
        List<String> nameList = new ArrayList<>();
        nameList.add("Jonathan Lee");
        nameList.add("Tony Leung");
        nameList.add("Tom Cruise");
        nameList.add("Liu De-hua"); // 为了排序更明显。Andy，a 很容易排在前面
        nameList.add("Wu Bai");
        nameList.add("Nicholas Tse Ting-fung");
        nameList.add("Asam");

        // 地区或国家
        Map<String, String> areaOrCountryMap = new HashMap<>();
        areaOrCountryMap.put("Jonathan Lee", "TW");
        areaOrCountryMap.put("Tony Leung", "HK");
        areaOrCountryMap.put("Tom Cruise", "USA");
        areaOrCountryMap.put("Liu De-hua", "HK");
        areaOrCountryMap.put("Wu Bai", "TW");
        areaOrCountryMap.put("Nicholas Tse Ting-fung", "HK");
        areaOrCountryMap.put("Asam", "HK");

        // solution1
        System.out.println("solution 1..");
        imperativeSolution(nameList, areaOrCountryMap);

        // solution2
        System.out.println("\nsolution 2..");
        functionalSolution(nameList, areaOrCountryMap);
    }

    private static void functionalSolution(List<String> nameList, Map<String, String> areaOrCountryMap) {
        // 排序
        // lambda语法：(param1, param2) -> {logic of the lambda here}
        Collections.sort(nameList, (o1, o2) -> {
            if (o1.equals("Liu De-hua") && !o2.equals("Liu De-hua")) {
                return -1;
            } else if (o2.equals("Liu De-hua") && !o1.equals("Liu De-hua")) {
                return 1;
            }
            return o1.compareTo(o2);
        });

        // 筛选和打印
        nameList
                // 将 list 转换为 steam 对象
                .stream()
                // 过滤 香港 地区的明星
                .filter(name -> areaOrCountryMap.get(name).equals("HK"))
                // map 处理数据
                .map(hkStar -> hkStar + "(HK)")
                // for each 打印数据
                .forEach(hkStar -> System.out.println(hkStar));

    }

    private static void imperativeSolution(List<String> nameList, Map<String, String> areaOrCountry) {
        // 排序
        Collections.sort(nameList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.equals("Liu De-hua") && !o2.equals("Liu De-hua")) {
                    return -1;
                } else if (o2.equals("Liu De-hua") && !o1.equals("Liu De-hua")) {
                    return 1;
                }
                return o1.compareTo(o2);
            }
        });

        // 筛选出香港的明星并打印
        for(String name : nameList) {
            if (areaOrCountry.get(name).equals("HK")) {
                System.out.println(name + "(HK)");
            }
        }
    }
}
