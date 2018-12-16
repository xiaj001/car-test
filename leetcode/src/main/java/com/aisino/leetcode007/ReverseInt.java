package com.aisino.leetcode007;

/**
 * @author jun.xia
 *
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 *  示例 2:
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 */
public class ReverseInt {
    public int reverse(int x) {
        if (x<0){
            return -doReverse(-x);
        }
        return doReverse(x);
    }

    public int doReverse(int x){
        if (x>Integer.MAX_VALUE){
            return 0;
        }
        int result =0;
        while (x/10>0){
            result=(result+x%10)*10;
            x=x/10;
        }
        result=result+x;
        return result;
    }

    public static void main(String[] args) {
        ReverseInt reverseInt = new ReverseInt();
        int result = reverseInt.reverse(1534236462);
        System.err.println(Integer.MAX_VALUE);
    }

}
