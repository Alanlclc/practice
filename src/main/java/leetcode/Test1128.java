package leetcode;

import java.util.HashMap;

/**
 * 给你一个由一些多米诺骨牌组成的列表dominoes。
 *
 * 如果其中某一张多米诺骨牌可以通过旋转 0度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 *
 * 形式上，dominoes[i] = [a, b]和dominoes[j] = [c, d]等价的前提是a==c且b==d，或是a==d 且b==c。
 *
 * 在0 <= i < j < dominoes.length的前提下，找出满足dominoes[i] 和dominoes[j]等价的骨牌对 (i, j) 的数量。
 */
public class Test1128 {

    public int numEquivDominoPairs(int[][] dominoes) {

        HashMap<Integer,Integer> map = new HashMap<>(100);
        for (int[] dominoe : dominoes) {
            int a = dominoe[0];
            int b = dominoe[1];
            Integer key = a > b ? b * 10 + a : a * 10 + b;
            map.put(key, map.getOrDefault(key,0) + 1);
        }
        int count = 0;
        for (int f : map.values()) {
            count += (f * (f - 1)) / 2;
        }
        return count;
    }


}
