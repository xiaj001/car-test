package com.aisino.leetcode009;

import org.junit.Test;

/**
 * @author jun.xia
 *
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 */
public class IsPalindrome {

    public boolean isPalindrome(int x) {
        if (x>=0){
            int result =0 ,temp = x;
            while (temp/10>0){
                result = (result + temp%10)*10;
                temp = temp/10;
            }
            result = result + temp;
            if (result==x) return true;
        }
        return false;
    }

    @Test
    public void test(){
        isPalindrome(121);
    }
}
