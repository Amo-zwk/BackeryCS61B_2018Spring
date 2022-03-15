package disc;

public class Flatten {

    //目的 : 给一个二维数组 :返回一个一维数组仅仅包含二维数组中的数字
    public static int[] flatten(int[][] x) {
        int totalLength = 0 ;
        for(int i = 0 ; i < x.length ; i ++) {
            for(int j = 0 ; j < x[i].length ; j ++) {
                totalLength++ ;
            }
        }
        int[] a = new int[totalLength] ;
        int aIndex = 0 ;
        for(int i = 0 ; i < x.length ; i ++) {
            for(int j = 0 ; j < x[i].length ; j ++) {
                a[aIndex] = x[i][j] ;
                aIndex++ ;
            }
        }
        return a ;
    }

    //skip
    //目的 : 按 n = 1 , n = n + 1 ,一直跳着来
    //比如1-10 就是 {1,3,6,10}
    public void skip() {
        IntList p = this ;
        int n = 1 ;
        while (p != null) {
            IntList next = p.next ;
            for(int i = 1 ; i <= n ; i ++) {
                if(next == null) {
                    break ;
                }
                next = next.next ;
            }
            p.next = next ;
            p = p.next ;
            n += 1 ;
        }
    }

    //去重
    public static void remove(Inlist p) {
        if(p == null) {
            return ;
        }
        IntList current = p.rest;
        IntList previous = p ;
        while (current != null) {
            //相等的话，让你指向下一个
            if(current.first == previous.first) {
                previous.rest = current.next ;
            } else {
                previous =current ;
                //previous = previous.next ; 感觉也可行
            }
            current =current.rest ;
        }
    }
    //main测试方法
    public static void main(String[] args) {
        int[][] x = new int[][]{{1, 2, 3},{}, {7, 8}};
        int res[] = flatten(x) ;
        System.out.print("{");
        for(int i = 0 ; i < res.length ; i ++) {
            System.out.print(res[i]+",");
        }
        System.out.println("null"+"}");
    }
}
