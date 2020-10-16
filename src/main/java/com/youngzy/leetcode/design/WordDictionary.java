package com.youngzy.leetcode.design;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * <p>
 * 实现词典类 WordDictionary ：
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。
 * <p>
 * word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *  
 * 示例：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * <p>
 * 提示：
 * 1 <= word.length <= 500
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最调用多 50000 次 addWord 和 search
 * <p>
 * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 */
interface WordDictionary {
    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word);

    /**
     * Returns if the word is in the data structure.
     * A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word);
}