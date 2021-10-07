package com.me.leetcode;

import java.util.Stack;

/**
 * @author zs
 * @date 2021/10/7.
 * 有效的括号
 * 栈
 * https://github.com/azl397985856/leetcode/blob/master/problems/20.valid-parentheses.md
 */
public class lc_020 {
    public static boolean isValid(String s) {
        if (s.length() < 2) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '{') stack.push('}');
            else if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "(){}}{";
        boolean valid = isValid(s);
        System.out.println(valid);
    }
}
