package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {
    /*
    *   给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    *   你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
    *
    *   示例:给定 nums = [2, 7, 11, 15], target = 9
    *   因为 nums[0] + nums[1] = 2 + 7 = 9
    *   所以返回 [0, 1]
    * */
    public static void main(String[] args) {
	// write your code here
        int[] nums = {2, 7, 11, 15};
        int[] result = twoSum(nums, 9);
        System.out.println(result[0] + result[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                result[0] = i;
                result[1] = map.get(temp);
                return result;
            } else {
                map.put(nums[i],i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
