package leetcode;

import java.util.Arrays;

/**
 * 在由 1 x 1 方格组成的 N x N 网格grid 中，每个 1 x 1方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 *
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\"表示。）。
 */
public class Test959 {

    //并查集先合并单元格内，再和并单元格间
    public int regionsBySlashes(String[] grid) {
        int length = grid.length;
        int m = 4 * length * length;
        UnionFind unionFind = new UnionFind(m);
        for(int i = 0; i < length; i++){
            char[] chars = grid[i].toCharArray();
            for (int j = 0; j < length; j++) {
                int index = 4 * (i * length + j);
                char c = chars[j];
                if (c == '/') {
                    // 合并 0、3，合并 1、2
                    unionFind.merge(index, index + 3);
                    unionFind.merge(index + 1, index + 2);
                } else if (c == '\\') {
                    // 合并 0、1，合并 2、3
                    unionFind.merge(index, index + 1);
                    unionFind.merge(index + 2, index + 3);
                } else {
                    unionFind.merge(index, index + 1);
                    unionFind.merge(index + 1, index + 2);
                    unionFind.merge(index + 2, index + 3);
                }
                // 单元格间合并
                // 向右合并：1（当前）、3（右一列）
                if (j + 1 < length) {
                    unionFind.merge(index + 1, 4 * (i * length + j + 1) + 3);
                }
                // 向下合并：2（当前）、0（下一行）
                if (i + 1 < length) {
                    unionFind.merge(index + 2, 4 * ((i + 1) * length + j));
                }
            }
        }
        return unionFind.count;
    }

    static class UnionFind {

        //并查集合
        public int[] parent;

        //记录连通量大小
        public int[] size;

        //节点数量
        public int n = 0;

        // 当前连通分量数目
        public int count;

        //初始化并查集数组，并使每个节点父节点指向自己
        public UnionFind(int n){
            this.n = n;
            this.parent = new int[n];
            this.size = new int[n];
            this.count = n;
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        //递归寻找连通集合根节点
        public int find(int x){
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        //连接两个点，指向过程,按秩合并，避免增加树的高度
        public boolean merge(int x,int y){
            x = find(x);
            y = find(y);
            //相等,已连通，跳过
            if (x == y) {
                return false;
            }
            //按秩合并
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            //y所属的集合数量增加到x所属的集合
            size[x] += size[y];
            //连通量减1
            --count;
            return true;
        }

        //判断两个点是否属于同一集合
        public boolean connected(int x, int y) {
            x = find(x);
            y = find(y);
            return x == y;
        }


    }

}
