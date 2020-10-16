package com.youngzy.leetcode.design;

public class WordDictionary0 implements WordDictionary {

    TrieNode root = new TrieNode(), pointer;
    public void addWord(String word) {
        pointer = root;
        for (char c : word.toCharArray()) {
            if (pointer.next[c - 'a'] == null) {
                pointer.next[c - 'a'] = new TrieNode();
            }
            pointer = pointer.next[c - 'a'];
        }

        pointer.isEnd = true;
    }

    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, TrieNode root) {
        // 问题在这里
        // 递归调用不能共享变量
        // pointer = root; 这样是不行的
        TrieNode pointer = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                // 这种方法就算错的，弃
                // 改改还是可以用的，放弃的太早了。。。
                // 不看别人的答案都不知道。。
                int count = 26;
                while (count > 0) {
//                    char tmp = (char)('a' + count - 1);
//                    System.out.println(tmp);
                    if (pointer.next[count - 1] != null) {
                        boolean match = search(word.substring(i + 1), pointer.next[count - 1]);
                        if (match) {
                            return true;
                        }
                    }
                    count --;
                }

                return false;
            }
            if (pointer.next[c - 'a'] == null) {
                return false;
            }
            pointer = pointer.next[c - 'a'];

        }
        return pointer.isEnd;
    }

    private class TrieNode {
        boolean isEnd;
        TrieNode[] next = new TrieNode[26];
    }
}
