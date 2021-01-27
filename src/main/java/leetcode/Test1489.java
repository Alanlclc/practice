package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给你一个 n个点的带权无向连通图，节点编号为 0到 n-1，同时还有一个数组 edges，其中 edges[i] = [fromi, toi, weighti]表示在fromi和toi节点之间有一条带权无向边。最小生成树(MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
 *
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
 *
 * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
 */
public class Test1489 {


    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

        //初始化需要查找最小生成树权值的集合,
        int m = edges.length;
        int[][] newEdges = new int[m][4];
        for (int i = 0; i < m; ++i) {
            System.arraycopy(edges[i], 0, newEdges[i], 0, 3);
            newEdges[i][3] = i;
        }
        //Kruskal 算法
        //权值排序
        Arrays.sort(newEdges, Comparator.comparingInt(u -> u[2]));
        //初始化并查集
        UnionFind ufStd = new UnionFind(n);
        //获得最小生成树权值
        int value = 0;
        for (int i = 0; i < m; ++i) {
            if (ufStd.merge(newEdges[i][0], newEdges[i][1])) {
                value += newEdges[i][2];
            }
        }

        //初始化结果
        List<List<Integer>> result = new ArrayList<>(2);
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());

        //枚举判断是否是关键边和非关键边
        for (int i = 0; i < m; ++i) {
            // 判断是否是关键边
            UnionFind uf = new UnionFind(n);
            int v = 0;
            for (int j = 0; j < m; ++j) {
                //剔除自身算最小生成树权值
                if (i != j && uf.merge(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            //如果并查集连通量不为1即无连通或者权值变大  则该边为关键边
            if (uf.count != 1 || v > value) {
                result.get(0).add(newEdges[i][3]);
                continue;
            }

            // 判断是否是伪关键边 关键逻辑是直接将该点相连   然后计算生成树权值是否变化  进行判断
            uf = new UnionFind(n);
            uf.merge(newEdges[i][0], newEdges[i][1]);
            v = newEdges[i][2];
            for (int j = 0; j < m; ++j) {
                //剔除自身算最小生成树权值
                if (i != j && uf.merge(newEdges[j][0], newEdges[j][1])) {
                    v += newEdges[j][2];
                }
            }
            //如果生成值不变，则为非关键边
            if (v == value) {
                result.get(1).add(newEdges[i][3]);
            }
        }
        return result;

    }






    static class UnionFind{

        //并查集合
        int[] parent;

        //记录连通量大小
        int[] size;

        //节点数量
        int n = 0;

        // 当前连通分量数目
        int count;

        //初始化并查集数组，并使每个节点父节点指向自己
        UnionFind(int n){
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

        //连接两个点
        public boolean merge(int x,int y){
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            }
            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
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
