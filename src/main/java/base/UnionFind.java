package base;

import java.util.Arrays;

/**
 * 并查集模板 数组
 */
public class UnionFind {

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
