package leetcode.common;

import base.UnionFind;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 你准备参加一场远足活动。给你一个二维rows x columns的地图heights，其中heights[row][col]表示格子(row, col)的高度。一开始你在最左上角的格子(0, 0)，且你希望去最右下角的格子(rows-1, columns-1)（注意下标从 0 开始编号）。你每次可以往 上，下，左，右四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 *
 * 一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。
 *
 * 请你返回从左上角走到右下角的最小体力消耗值。
 */
public class Test1631 {

    public int minimumEffortPath(int[][] heights) {
        //将二维数组转换为图  并根据权值排序
        int m = heights.length;
        int n = heights[0].length;
        List<List<Integer>> list = new ArrayList<>();
        //转换的时候要注意 边界
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int id = i * n + j;
                if (i > 0){
                    List<Integer> temp1 = new ArrayList<>();
                    temp1.add(id - n);
                    temp1.add(id);
                    temp1.add(Math.abs(heights[i][j] - heights[i - 1][j]));
                    list.add(temp1);
                }
                if (j > 0){
                    List<Integer> temp2 = new ArrayList<>();
                    temp2.add(id - 1);
                    temp2.add(id);
                    temp2.add(Math.abs(heights[i][j] - heights[i][j - 1]));
                    list.add(temp2);
                }
            }
        }
        Collections.sort(list, Comparator.comparingInt(o -> o.get(2)));
        //并查集合并  当两端连通后  返回当前循环的权
        UnionFind uf = new UnionFind(m * n);
        int result = 0;
        for (List<Integer> temp: list) {
            int v = temp.get(2);
            uf.merge(temp.get(0),temp.get(1));
            if (uf.connected(0,m * n - 1)){
                result = v;
            }
        }
        return result;
    }


}
