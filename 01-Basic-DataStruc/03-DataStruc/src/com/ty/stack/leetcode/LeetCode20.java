package com.ty.stack.leetcode;

import java.util.Stack;

//栈的应用- 匹配括号
public class LeetCode20 {

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack=new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char e=chars[i];
            if (e=='('||e=='{'||e=='['){ //左括号入栈
                stack.push(e);
            }else { //右括号进行匹配
                if (stack.isEmpty()){
                    return false; //如果栈为空的话，直接返回
                }
                Character pop = stack.pop();
                if (e==']'&&pop!='[') //以下设定判定终止的条件
                    return false;
                if (e=='}'&&pop!='{')
                    return false;
                if (e==')'&&pop!='(')
                    return false;
            }
        }
        return stack.isEmpty(); //最后判断栈是否为空
    }
}
