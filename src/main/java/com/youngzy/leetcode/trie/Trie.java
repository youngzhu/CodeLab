package com.youngzy.leetcode.trie;

/**
 * 208. 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 *
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 */
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
public class Trie {

    private boolean isEnd = false; // 单词是否结束
    private Trie[] next = new Trie[26]; // 下一个字母

    private Trie pointer;

    /** Initialize your data structure here. */
    public Trie() {
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        pointer = this;

        for (char c : word.toCharArray()) {
            if (pointer.next[c - 'a'] == null) {

                pointer.next[c - 'a'] = new Trie();
            }
            pointer = pointer.next[c - 'a'];
        }

        pointer.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (! startsWith(word)) {
            return false;
        }

        return pointer.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        pointer = this;

        for (char c : prefix.toCharArray()) {
            if (pointer.next[c - 'a'] == null) {
                return false;
            }
            pointer = pointer.next[c - 'a'];
        }

        return true;
    }
}
