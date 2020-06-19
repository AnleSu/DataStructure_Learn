package com.company;

public class Main {

    int max = 8;
//存放皇后位置的结果数组
    int[] array = new int[max];
    static int count = 0;
    static int judgetCount = 0;
    public static void main(String[] args) {
	// write your code here
        Main main = new Main();
        main.check(0);
        System.out.printf("一共有%d种解法", count);
        System.out.printf("一共判断%d次",judgetCount);//一共有92种解法一共判断15720次
    }

//    放置第n个皇后
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
//            先把这个皇后 放入该行第一列
            array[n] = i;
            if (judge(n)) {
                check(n + 1);
            } else { //冲突 继续执行array[n] = i  因为i++会继续遍历

            }
            judgetCount++;
        }
    }

//    当放置第n个皇后 检查和前面已经摆放的皇后冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
//          array[i] == array[n] 是否在同一列
//          Math.abs(n - i) == Math.abs(array[n] - array[i]  是否在同一斜线  Math.abs()求绝对值
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }

        }
        return true;
    }

//    输出结果
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
