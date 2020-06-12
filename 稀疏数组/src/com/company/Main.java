package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        二维数组 （稀疏数组 可以压缩二维数组）
//        约瑟夫问题  单向环形链表

//        线性结构   数据元素 一对一    顺序存储（地址连续）和链式存储（地址可以不连续 通过指针）
//        数组 队列 链表 栈

//        非线性结构 二维数组 多维数组 广义表 树  图


//        稀疏数组 1.遍历原始二维数组 得到有效数据的个数 sum  2.根据sum创建sparseArr int[sum+1][3]  3.二维数组的有效数据存入sparseArr
//        五子棋程序 存盘退出和续上盘  使用二维数组记录棋盘  很多默认值都是0  可以使用稀疏数组压缩
//        稀疏数组   行不确定 列有三列  row  col  val
//        第一行记录 原数组的           行  列     共有多少非0值
//        以后一次记录每一个非0值        行  列     值

//        能够把稀疏数组恢复成二维数组
//        1.读取sparseArr的第一行 根据第一行的数据 创建元素二维数组 chessArr = int[11][11]
//        2.读取sparseArr后几行的数据 并赋给原始二维数组

//        创建原始数组 11 * 11  0： 表示没有子 1：表示黑子 2： 表示白子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        for (int[] row : chessArr1) {
            for (int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);

        int spaseArr[][] = new int[sum+1][3];
        spaseArr[0][0] = 11;
        spaseArr[0][1] = 11;
        spaseArr[0][2] = sum;

        int count = 1;//记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    spaseArr[count][0] = i;
                    spaseArr[count][1] = j;
                    spaseArr[count][2] = chessArr1[i][j];
                    count++;
                }
            }
        }

        System.out.println();
        System.out.println("稀疏数组如下：");
        for (int i = 0; i < spaseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",spaseArr[i][0],spaseArr[i][1],spaseArr[i][2]);
        }

//        稀疏数组恢复二维数组

        int chessArr2[][] = new int[spaseArr[0][0]][spaseArr[0][1]];


        for (int i = 1; i < spaseArr.length; i++) {
            chessArr2[spaseArr[i][0]][spaseArr[i][1]] = spaseArr[i][2];

        }
        System.out.println();
        System.out.println("恢复后的二维数组如下：");
        for (int[] row : chessArr2) {
            for (int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
