package leetcode.common.twopoint;

/**
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换k次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 *
 * 注意：字符串长度 和 k 不会超过104。
 */
public class Test424 {


    public int characterReplacement(String s, int k) {
        if (s == null) return 0;
        int len = s.length();
        if (len < 2) {
            return len;
        }
        char[] temp = s.toCharArray();
        int left = 0;
        int right = 0;

        //记录窗口中出现过的,相同字符最多的,字符长度
        int maxCount = 0;

        //实时维护窗口中字符出现的次数
        int[] map = new int[26];

        for (;right < len;right++){
            int index = temp[right] - 'A';
            map[index]++;
            maxCount = Math.max(maxCount, map[index]);
            //这边的判断条件用出现过的最大长度 + k
            //这一步较难理解  每次右移时只需要比较 k + 历史最大值 和 窗口长度，因为右移时所
            if (right - left + 1 > maxCount + k) {
                map[temp[left] - 'A']--;
                left++;
            }
        }
        return len - left;
    }


}
