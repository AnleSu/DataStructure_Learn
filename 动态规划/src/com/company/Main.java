package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Solution solution = new Solution();
//        int fibRes = solution.fib3(5);

//        int coinRes = solution.coinChange(new  int[]{1,2,5}, 10);

        int maxSubRes = solution.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(maxSubRes);
    }
}


class Solution {
//    暴力递归
    int fib(int n) {
        if (n == 1 || n == 2) return 1;
        return fib(n-1) + fib(n-2);
    }

//    重叠子问题 比如n = 20 f(20) = f(19) + f(18)   f(19) = f(18) + f(17)
//    f(18)被重复计算 且f(18)下的递归数巨大 重算一次消耗巨大的时间
//    带《备忘录》的递归
    int[] memo ;
    int fib1(int n) {
        if (n < 1) return 0;
        memo = new int[n + 1];//n从1开始 所以数组长度要n+1  设置为n会越界
        return helper(n);

    }

    int helper(int n) {
        if (n == 1 || n == 2) return 1;
        if (memo[n] != 0) return memo[n];
        memo[n] = helper(n-1) + helper(n-2);
        return memo[n];
    }

//    迭代解法
    int fib2(int n) {
        int[] dp = new int[n+1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

//    通过状态转移方程 可以看出只需要关心当前状态前面的两个状态就行 可以进一步优化空间复杂度
    int fib3(int N) {
        int sum = 0, a= 1, b= 1;
        if (N == 1 || N == 2) return 1;
        for (int i = 3; i <= N ; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

//1137. 第 N 个泰波那契数
//    泰波那契序列 Tn 定义如下： 
//
//T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
//
//给你整数 n，请返回第 n 个泰波那契数 Tn 的值。

    int tribonacci(int n) {
        int sum = 0, a = 0, b= 1, c= 1;
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        for (int i = 3; i <= n  ; i++) {
            sum = a + b + c;
            a = b;
            b = c;
            c = sum;
        }
        return sum;
    }


//322. 零钱兑换
//    给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

    int coinChange(int[] coins, int amount) {

        if (amount < 0) return -1;
        return coinChangeHelper(coins, amount, new int[amount + 1]);
    }

    int coinChangeHelper(int[] coins, int amount, int[] count) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (count[amount - 1] != 0) return count[amount - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChangeHelper(coins, amount - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[amount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[amount - 1];
    }


//    最长递增子序列（Longest Increasing Subsequence，简写 LIS）注意子序列 是可以不连续
//300. 最长上升子序列
//  给定一个无序的整数数组，找到其中最长上升子序列的长度。
    int lengthOfLIS(int[] nums) {
//        dp中存的是 与nums对应 每一个数字 所在位置 的最长上升子序列的长度 所以最终结果就是求dp中的最大值
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
//                到第i个数字  只要找到前面i - 1个数字中 比nums[i]小的数字中 长度最长的 然后加上第i个数字本身这一个长度
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }



// 53. 最大子序和  注意此处是要连续子数组
//  给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);//每遍历到下一个数字 都比较一下 是否把他加入到前面最大和中
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;

    }



//416. 分割等和子集
//   给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
    boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num:nums) sum += num;

        if (sum % 2 != 0) return false;
        int target = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n][target + 1];

        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n - 1][target];
    }
}
