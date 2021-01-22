package leetcode;

import java.util.Arrays;

public class Test628 {

    private Integer result;


    //暴力破解  O(n^3)
//    public int maximumProduct(int[] nums) {
//        for (int i = 0; i < nums.length - 2; i++){
//            for (int j = i + 1; j < nums.length - 1; j++){
//                for (int k = j + 1;k < nums.length ; k++){
//                    int temp = nums[i] * nums[j] * nums[k];
//                    if (result == null || result <= temp){
//                        result = temp;
//                    }
//                }
//            }
//        }
//        return result;
//    }

    //排序 遍历
//    public int maximumProduct(int[] nums) {
//        Arrays.sort(nums);
//        int n = nums.length;
//        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 3] * nums[n - 2] * nums[n - 1]);
//    }




    //线性扫描 只要求出数组中最大的三个数以及最小的两个数，因此我们可以不用排序，用线性扫描直接得出这五个数。
    public int maximumProduct(int[] nums) {
        // 最小的和第二小的
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }



}
