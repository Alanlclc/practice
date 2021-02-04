package leetcode.common.twopoint;


/**
 * 给定一个含有n个正整数的数组和一个正整数s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 */
public class Test209 {

    public static int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        int minCount = len;
        int tempSum = 0 ;
        while (right < len){
            tempSum += nums[right++];
            while (tempSum >= s){
                minCount = Math.min(minCount,right - left);
                tempSum -= nums[left++];
            }
        }
        return minCount;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7,new int[]{2,3,1,2,4,3}));
    }

}
