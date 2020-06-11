package com.youngzy.stackskills.datastructures.stack;

import java.util.*;

/**
 * 括号是否匹配
 */
public class MatchParenthesis {

    private static final Map<Character, Character> MATCHING_PAREN =new HashMap<>();
    private static final Set<Character> OPENING_SET = new HashSet<>();

    static {
        MATCHING_PAREN.put(')', '(');
        MATCHING_PAREN.put('}', '{');
        MATCHING_PAREN.put(']', '[');

        OPENING_SET.addAll(MATCHING_PAREN.values());
    }

    public boolean isMatch(String input) {
        Stack<Character> stack = new Stack<>();

        try {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                if (OPENING_SET.contains(c)) {
                    stack.push(c);
                }

                if (MATCHING_PAREN.containsKey(c)) {
                    char topOpening = stack.pop();
                    if (topOpening != MATCHING_PAREN.get(c)) {
                        return false;
                    }
                }
            }

            return stack.isEmpty();
        } catch (Exception e) {

        }

        return false;
    }

}
