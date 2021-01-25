package base;

import java.util.Arrays;

/**
 * 并查集模板 数组
 */
public class UnionFind {

    public int[] parent;

    public UnionFind(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    public void merge(int x, int y) {
        parent[find(x)] = find(y);
    }


}
