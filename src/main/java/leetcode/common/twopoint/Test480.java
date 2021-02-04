package leetcode.common.twopoint;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 *
 * 例如：
 *
 * [2,3,4]，中位数是3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 */
public class Test480 {

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] re = new double[nums.length - k + 1];
        int[] window = new int[k];
        if (k >= 0) System.arraycopy(nums, 0, window, 0, k);
        Arrays.sort(window);
        re[0] = getMid(window);
        for (int i = 0; i < nums.length - k; i++) {
            //二分搜索
            int index = Arrays.binarySearch(window, nums[i]);
            window[index] = nums[i + k];
            //排序
            //向后冒泡
            while (index < window.length - 1 && window[index] > window[index + 1]) {
                swap(window, index, index + 1);
                index++;
            }
            //向前冒泡
            while (index > 0 && window[index] < window[index - 1]) {
                swap(window, index, index - 1);
                index--;
            }
            re[i + 1] = getMid(window);
        }
        return re;
    }

    //交换
    private void swap(int[] window, int i, int j) {
        int temp = window[i];
        window[i] = window[j];
        window[j] = temp;
    }

    //最简单的二分查找
    private int search(int[] window, int target) {
        int start = 0;
        int end = window.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (window[mid] > target) {
                end = mid - 1;
            } else if (window[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public double getMid(int[] window){
        int len = window.length;
        if (window.length % 2 == 0){
            return window[len/2] / 2.0 + window[len/2 - 1]/ 2.0;
        }else {
            return window[len/2];
        }
    }







}
