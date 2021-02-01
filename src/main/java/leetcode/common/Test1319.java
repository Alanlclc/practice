package leetcode.common;


/**
 * 用以太网线缆将n台计算机连接成一个网络，计算机的编号从0到n-1。线缆用connections表示，其中connections[i] = [a, b]连接了计算机a和b。
 *
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
 */
public class Test1319 {


    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] conn : connections) {
            uf.merge(conn[0], conn[1]);
        }
        return --uf.count;
    }

    static class UnionFind {

        public int[] parent;

        public int[] size;

        public int count;

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; ++i) {
                size[i] = 1;
                parent[i] = i;
            }
        }

        public int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        //合并过程中  需要根据多余的边递减count
        public void merge(int x, int y) {
            int a = find(x);
            int b = find(y);
            if (a != b){
                //按秩合并  节点量小的合并到节点量大的
                if (size[a] >= size[b]) {
                    parent[b] = a;
                    size[a] += size[b];
                } else {
                    parent[a] = b;
                    size[b] += size[a];
                }
                count--;
            }
        }


    }


}
