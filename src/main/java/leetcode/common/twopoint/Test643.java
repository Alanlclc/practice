package leetcode.common.twopoint;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 */
public class Test643 {


    public static double findMaxAverage(int[] nums, int k) {
        int left = 0;
        int right = k - 1;
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += nums[i];
        }
        int sum = maxSum;
        while(right < nums.length){
            if (left > 0){
                sum = sum + nums[right] - nums[left - 1];
                maxSum = Math.max(maxSum , sum);
            }
            left++;
            right++;
        }
        return (double )maxSum / k;
    }

    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3},1));
    }

}
