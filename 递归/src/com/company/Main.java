package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
//     递归：   自己调用自己
        test(4);//通过打印问题 理解递归的调用机制
//        当程序执行到一个方法时  就会开辟一个独立的空间：栈   递归的底层还是通过栈实现的
//        方法中的局部变量是独立的 不互相影响 比如n
//        如果使用的参数是引用类型的数据（比如数组）  则共享该数据
//        递归要向退出递归的条件走 不然就会无限递归 出现栈溢出
//        当一个方法执行完毕或者return 就会返回 回到调用他的地方 并把结果带回  直到方法执行完毕
//
//        二维数组 模拟迷宫
//        int[][] map = new int[8][7];
////        1：表示墙
////        上下的墙
//        for (int i = 0; i < 7; i++) {
//            map[0][i] = 1;
//            map[7][i] = 1;
//        }
////        左右的墙
//        for (int i = 0; i < 8; i++) {
//            map[i][0] = 1;
//            map[i][6] = 1;
//        }
////      挡板
//        map[3][1] = 1;
//        map[3][2] = 1;
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 7; j++) {
//                System.out.print(map[i][j] + "  ");
//            }
//            System.out.println();
//        }
//
////        递归回溯找路
//        setWay2(map, 1,1);
////        输出新地图 通路是2
//        System.out.println("输出新地图");
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 7; j++) {
//                System.out.print(map[i][j] + "  ");
//            }
//            System.out.println();
//        }


        int res = addDigits(138);
        System.out.println(res);
    }
//    map 地图    [i ,j] 表示开始位置index  返回是否有路
//    从 【1，1】 到 【6，5】的通路
//    map[i][j] = 0 没走过  1 表示墙 2 表示通路  3 走过 走不通
//    确定一个策略  下 右 上 左  走不通再回溯
//    求最短路径 跟设置的策略有关
    public static  boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2; //假定该点可以走通
                if (setWay(map,i + 1,j)) {
                    return true;
                } else if (setWay(map, i ,j + 1)) {
                    return true;
                } else if (setWay(map, i -1 ,j )) {
                    return true;
                }else if (setWay(map, i ,j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;//走不通
                    return false;
                }
            } else {//map[i][j] != 0 可能等于 1  2  3
                return false;
            }
        }
    }

//    上 右 下 左
    public static  boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2; //假定该点可以走通
                if (setWay2(map,i - 1,j)) {
                    return true;
                } else if (setWay2(map, i ,j + 1)) {
                    return true;
                } else if (setWay2(map, i + 1 ,j )) {
                    return true;
                }else if (setWay2(map, i ,j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;//走不通
                    return false;
                }
            } else {//map[i][j] != 0 可能等于 1  2  3
                return false;
            }
        }
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
//        加 else 只会打印 n = 2
//        else {
//            System.out.println("n = " + n);
//        }
        System.out.println("n = " + n);
    }

//    阶乘
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }

//    258. 各位相加
    public static int addDigits(int num) {
//        while (num >= 10) {
//            num = num / 10 + num % 10;
//        }
//        return num;
//
//
//        return (num - 1) % 9 + 1;

        if (num < 10) return num;//递归结束条件

        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num = num / 10;
        }
        return addDigits(sum);
    }



}
