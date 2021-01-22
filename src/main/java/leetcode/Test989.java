package leetcode;

/**
 * 对于非负整数X而言，X的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果X = 1231，那么其数组形式为[1,2,3,1]。
 *
 * 给定非负整数 X 的数组形式A，返回整数X+K的数组形式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Test989 {

    public List<Integer> addToArrayForm(int[] A, int K) {
        //k转字符串取每一位  双指针加法
        String k = String.valueOf(K);
        String[] chars = k.split("");
        List<Integer> list = new ArrayList<>();
        int temp = 0;
        for (int i = A.length - 1, j = chars.length - 1; j >= 0 || i >= 0;i--,j--){
            if (i < 0){
                int a = Integer.parseInt(chars[j]);
                list.add((a + temp) / 10 > 0 ? (a + temp) - 10 :(a + temp));
                temp = (a + temp) / 10 > 0 ? 1 : 0;
                continue;
            }
            if (j < 0){
                list.add((A[i] + temp) / 10 > 0 ? (A[i] + temp) - 10 :(A[i] + temp));
                temp = (A[i] + temp) / 10 > 0 ? 1 : 0;
                continue;
            }
            int a = Integer.parseInt(chars[j]);
            list.add((a + A[i] + temp) / 10 > 0 ? (a + A[i] + temp) - 10 :(a + A[i] + temp));
            temp = (a + A[i] + temp) / 10 > 0 ? 1 : 0;
        }
        if (temp > 0){
            list.add(temp);
        }
        Collections.reverse(list);
        return list;
    }


    public static void main(String[] args) {
        Test989 test989 = new Test989();
        System.out.println(test989.addToArrayForm(new int[]{7},993));
    }

}
