package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        内部排序  需要排序的数据都加载到内存中
//        外部排序  比如数据量很大 需要借助外部存储

//        统计方法：1.事后统计法（耗时， 依赖硬件） 2.事前估算法（时间复杂度）
//        时间复杂度
//        时间频度T(n)   执行语句越多，时间花费越多
//        for循环100次 T(n) = n + 1  循环了100次最后还要判断一次n>100

//      n = a 的 x次方   x = log a N  log以a为底n的对数

        int arr[] = {3,9,-1,10,-2};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
     }

// 冒泡排序  时间复杂度O(n²)
    public static void bubbleSort(int arr[]) {
        int temp;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) { //下一趟就少遍历一个 因为每一趟遍历都把最大的数字放在了最后 下一趟排序不需要再 判断了
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (flag == false) {//一趟排序中没发生交换 说明已经是有序数组了 就不需要在循环了
                break;
            } else {
                flag = false;//重置flag 进行下次判断
            }

        }
    }

//    选择排序  每一次从剩余的数中选出最小的数 放在最前面 第一轮最小的数放在第一个位置
//                                               第二轮从第二个数开始 最小的数据放在第二个位置
    public static void selectSort(int[] arr) {


        for (int j = 0; j < arr.length - 1; j++) {
            int minIndex = j;
            int min = arr[j];
            for (int i = 1 + j; i < arr.length; i++) {
                if (min > arr[i]) {
                    min = arr[i];
                    minIndex = i;
                }
            }

            if (minIndex != j) {//交换 把最小的数放最前边
                arr[minIndex] = arr[j];
                arr[j] = min;
            }

        }


    }

//    插入排序


}
