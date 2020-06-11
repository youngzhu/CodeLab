package com.youngzy.leetcode.unionfind;

import java.util.*;

/**
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，
 * 其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该帐户的邮箱地址。
 *
 * 现在，我们想合并这些帐户。
 * 如果两个帐户都有一些共同的邮件地址，则两个帐户必定属于同一个人。
 * 请注意，即使两个帐户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
 * 一个人最初可以拥有任意数量的帐户，但其所有帐户都具有相同的名称。
 *
 * 合并帐户后，按以下格式返回帐户：
 * 每个帐户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。accounts 本身可以以任意顺序返回。
 *
 * 例子 1:
 * Input:
 * accounts = [
 *          ["John", "johnsmith@mail.com", "john00@mail.com"],
 *          ["John", "johnnybravo@mail.com"],
 *          ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 *          ["Mary", "mary@mail.com"]
 *          ]
 * Output:
 * [
 * ["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 * ["John", "johnnybravo@mail.com"],
 * ["Mary", "mary@mail.com"]
 * ]
 *
 * Explanation:
 *   第一个和第三个 John 是同一个人，因为他们有共同的电子邮件 "johnsmith@mail.com"。
 *   第二个 John 和 Mary 是不同的人，因为他们的电子邮件地址没有被其他帐户使用。
 *   我们可以以任何顺序返回这些列表，例如答案[['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
 *   ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']]仍然会被接受。
 *
 * 注意：
 * accounts的长度将在[1，1000]的范围内。
 * accounts[i]的长度将在[1，10]的范围内。
 * accounts[i][j]的长度将在[1，30]的范围内。
 *
 * 链接：https://leetcode-cn.com/problems/accounts-merge
 */
public class AccountsMerge0 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>(accounts.size());

        // key - email
        // value - accounts 的 下标，对应姓名
        Map<String, Integer> rootMap = new HashMap<>();

        // 不用name做key，是因为有同名不同人的情况
        Map<Integer, Set<String>> emailMap = new HashMap<>(accounts.size());

        List<Integer> mergedIdx = new ArrayList<>(accounts.size());

        int idx = 0;
        Set<String> emailSet;
        List<String> nameList = new ArrayList<>(accounts.size());
        for (; idx < accounts.size(); idx++) {
            emailSet = new HashSet<>();

            List<String> data = accounts.get(idx);
            nameList.add(data.get(0));
            for (int i = 1; i < data.size(); i ++) {
                // email 从第二个开始
                emailSet.add(data.get(i));
            }

            emailMap.put(idx, emailSet);
        }

        // 查找与合并
        Integer key, anotherKey, newKey;
        Integer rootKey1, rootKey2;
        Set<String> value, anotherValue, newValue;
        int count = 0;
        for (Map.Entry<Integer, Set<String>> entry : emailMap.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();

            // 将同一组的root更新一致
            rootKey1 = find(key, value, rootMap);
            if (rootKey1 != key) {
                updateRoot(rootMap, rootKey1, value);
            }

            if (mergedIdx.contains(key)) {
                continue;
            }

            for (Map.Entry<Integer, Set<String>> anotherEntry : emailMap.entrySet()) {
                anotherKey = anotherEntry.getKey();
                anotherValue = anotherEntry.getValue();

                count++;

                // 不是自己，且未合并过
                if (key != anotherKey
                        && ! mergedIdx.contains(anotherKey)) {

                    // 将同一组的root更新一致
                    rootKey2 = find(anotherKey, anotherValue, rootMap);
                    if (rootKey2 != anotherKey) {
                        updateRoot(rootMap, rootKey2, anotherValue);
                    }

                    for (String email : value) {
                        if (anotherValue.contains(email)) {
                            // 同一账户，合并
                            // 查找email最小的idx值，即根
                            // 因为要将email合并到最小的idx上
                            newKey = Math.min(rootKey1, rootKey2);
                            newValue = emailMap.get(newKey);
                            newValue.addAll(value);
                            newValue.addAll(anotherValue);
                            emailMap.put(newKey, newValue);

                            if (newKey.intValue() != key.intValue()) {
                                mergedIdx.add(key);
                            }
                            if (newKey.intValue() != anotherKey.intValue()) {
                                mergedIdx.add(anotherKey);
                            }

                            // 更新email的根
                            updateRoot(rootMap, newKey, newValue);

                            break;
                        }
                    }
                }

            }

        }

        // 返回处理
        List<String> rowData, emailList;
        String name;
        for (Map.Entry<Integer, Set<String>> entry : emailMap.entrySet()) {
            if (mergedIdx.contains(entry.getKey())) {
                continue;
            }

            rowData = new ArrayList<>();
            name = nameList.get(entry.getKey());
            rowData.add(name);
            emailList = new ArrayList<>(entry.getValue());
            Collections.sort(emailList);
            rowData.addAll(emailList);

            result.add(rowData);
        }

        return result;
    }

    private void updateRoot(Map<String, Integer> rootMap, Integer root, Set<String> emailSet) {
        for (String email : emailSet) {
//            if (rootMap.getOrDefault(email, Integer.MAX_VALUE) > root) {
                rootMap.put(email, root);
//            }
        }
    }

    private Integer find(Integer root, Set<String> emailSet, Map<String, Integer> rootMap) {

        for (String email : emailSet) {
            root = Math.min(root, rootMap.getOrDefault(email, root));
        }

        return root;
    }
}
