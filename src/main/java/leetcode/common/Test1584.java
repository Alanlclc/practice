package leetcode.common;


/**
 *
 *给你一个points数组，表示 2D 平面上的一些点，其中points[i] = [xi, yi]。
 *
 * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
 *
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有一条简单路径时，才认为所有点都已连接。
 */

import java.util.*;

public class Test1584 {



    public int minCostConnectPoints(int[][] points) {
        int vertexes=points.length;
        if(vertexes<2) return 0;
        int checkPoint=0;
        int cost=0;
        int[] lowcost=new int[vertexes];
        Arrays.fill(lowcost,Integer.MAX_VALUE);
        lowcost[0]=0;
        //循环n-1次
        for(int i=0;i<vertexes-1;i++){
            //计算其他结点到生成树的距离
            int minDist=Integer.MAX_VALUE;
            int temp=checkPoint;
            for(int v=0;v<vertexes;v++){
                //lowcost[v]==0则表示已经加入到生成树中了
                if(lowcost[v]>0){
                    //计算其他点到生成树的距离
                    lowcost[v]=Math.min(lowcost[v],manhaton(points,v,checkPoint));
                    //选择当前最短的距离作为新的检查点
                    if(lowcost[v]<minDist){
                        minDist=lowcost[v];
                        temp=v;
                    }
                }
            }
            //更新检查点
            checkPoint=temp;
            //将新的检查点放入最小生成树
            lowcost[checkPoint]=0;
            //更新总费用
            cost+=minDist;
        }
        return cost;
    }
    private int manhaton(int[][] points,int x,int y){
        return Math.abs(points[x][0]-points[y][0])+Math.abs(points[x][1]-points[y][1]);
    }


//    //prim算法
//    public int minCostConnectPoints(int[][] points) {
//        //已决定的点集
//        int[][] v = new int[points.length][2];
//        //点集距离合计
//        Integer temp = new Integer(0);
//        //点集距离数组
//        List<Integer> list = new ArrayList<>();
//        //已经放入的点集合下标
//        Set<Integer> indexList = new HashSet<>();
//        //从第一个开始  把自己放入
//        indexList.add(0);
//        v[0] = points[0];
//        while (list.size() != points.length - 1){
//            Map<Integer,Integer> map = new TreeMap<>();
//            for (int i = 0; i < points.length; i++){
//                if (indexList.contains(i)){
//                    continue;
//                }
//                for (int j = 0; j < v.length;j++) {
//                    if (indexList.contains(j)){
//                        int b = Math.abs(points[i][0] - v[j][0]) + Math.abs(points[i][1] - v[j][1]);
//                        map.put(b, i);
//                    }
//                }
//            }
//            //取map中最小的b对应的i和j  新增点和下标
//            Set<Integer> integers = map.keySet();
//            for (Integer e : integers) {
//                Integer i = map.get(e);
//                indexList.add(i);
//                v[i] = points[i];
//                temp += e;
//                list.add(e);
//                break;
//            }
//        }
//        return temp;
//    }


    public static void main(String[] args) {
        Test1584 test1584 = new Test1584();
//        System.out.println(test1584.minCostConnectPoints(new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}}));
        System.out.println(test1584.minCostConnectPoints(new int[][]{{3,12},{-2,5},{-4,1}}));

    }



}
