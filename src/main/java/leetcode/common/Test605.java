package leetcode.common;

public class Test605 {

    //贪心算法
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i+=1) {
            if (flowerbed[i] == 0 &&  (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1] == 0)){
                flowerbed[i] = 1;
                n--;
                if (n<=0) return true;
            }
        }
        return n<=0;
    }


}
