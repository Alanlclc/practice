package leetcode.offer;

/**
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 */

public class Test0105 {

    public boolean oneEditAway(String first, String second) {
        char[] c1 = first.toCharArray();
        char[] c2 = second.toCharArray();
        if (Math.abs(c1.length - c2.length) > 1) return false;
        int result = 0;
        for (int i = 0; i < Math.min(c1.length,c2.length); i++) {
            if (c1.length == c2.length){
                if (c1[i] != c2[i] ){
                    result++;
                }
            }else if (c1.length > c2.length){
                if (c1[i + result] != c2[i] ){
                    i--;
                    result++;
                }
            }else {
                if (c2[i + result] != c1[i] ){
                    i--;
                    result++;
                }
            }
            if (result > 1) {
                break;
            }
        }
        return result < 2;
    }


    public static void main(String[] args) {
        Test0105 test0105 = new Test0105();
        System.out.println(test0105.oneEditAway("teacher","bleacher"));
    }


}
