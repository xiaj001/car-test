package com.aisino.leetcode01;


import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.List;

/**
 *
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 *
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 *
 * 示例:
 *      给定 nums = [2, 7, 11, 15], target = 9
 *
 *      因为 nums[0] + nums[1] = 2 + 7 = 9
 *      所以返回 [0, 1]
 *
 *
 *
 */
public class TowNumberSum {

    public int[] twoSum(int[] nums, int target) {
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                if (nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    @Test
    public void test(){
        int[] nums = {2, 7, 11, 15};
        int  target = 9;
        int[] res = twoSum(nums, target);
        List<Integer> list = Ints.asList(res);
        System.err.println(list);
    }

}
