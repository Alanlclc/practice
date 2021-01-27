package leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3 种类型的边：
 *
 * 类型 1：只能由 Alice 遍历。
 * 类型 2：只能由 Bob 遍历。
 * 类型 3：Alice 和 Bob 都可以遍历。
 * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi]表示节点 ui 和 vi 之间存在类型为 typei 的双向边。请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
 */
public class Test1579 {

    //枚举 + 并查集查连通分量
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        //将点分成两类  Alice能遍历的 和 Bob能遍历的
        List<List<Integer>> aliceList = new ArrayList<>();
        List<List<Integer>> bobList = new ArrayList<>();
        List<List<Integer>> common = new ArrayList<>();
        for (int[] edge : edges) {
            List<Integer> list = new ArrayList<>();
            list.add(edge[1] - 1);
            list.add(edge[2] - 1);
            if (edge[0] == 1) {
                aliceList.add(list);
            } else if (edge[0] == 2) {
                bobList.add(list);
            } else {
                common.add(list);
            }
        }
        UnionFind ufa = new UnionFind(n);
        UnionFind ufb = new UnionFind(n);
        int ans = 0;
        for (List<Integer> list: common) {
            if (!ufa.merge(list.get(0),list.get(1))){
                ans++;
                //如果这两个节点在同一个连通分量中，那么添加这条「公共边」是无意义的；
            }else{
                ufb.merge(list.get(0),list.get(1));
            }
        }
        for (List<Integer> list: aliceList) {
            if (!ufa.merge(list.get(0),list.get(1))){
                ans++;
            }
        }
        for (List<Integer> list: bobList) {
            if (!ufb.merge(list.get(0),list.get(1))){
                ans++;
            }
        }
        if (ufa.count != 1 || ufb.count != 1) {
            return -1;
        }
        return ans;
    }



    public static void main(String[] args) {
        Test1579 test1579 = new Test1579();
        int[][] a = new int[][]{{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}};
        System.out.println(test1579.maxNumEdgesToRemove(4,a));
    }


    static class UnionFind {

        //并查集合
        public int[] parent;

        //记录连通量大小
        public int[] size;

        //节点数量
        public int n = 0;

        //连通分量数目
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
