package com.company;

import java.util.Arrays;
import java.util.Scanner;

// 大顶堆 （完全二叉树） 父节点的值大于或等于左右子节点的值
//小顶堆       父节点的值小于或等于左右子节点的值
//升序用大顶堆  降序用小顶堆


public class Main {

    public static void main(String[] args) {
	// write your code here
//        HashTab hashTab  = new HashTab(7);
//        String key = " ";
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("add");
//            System.out.println("list");
//            System.out.println("exit");
//            System.out.println("find");
//            key = scanner.next();
//            switch (key) {
//                case "add":
//                    System.out.println("enter id");
//                    int id = scanner.nextInt();
//                    System.out.println("enter name");
//                    String name = scanner.next();
//
//                    Emp emp = new Emp(id, name);
//                    hashTab.add(emp);
//                    break;
//                case "list":
//                    hashTab.list();
//                    break;
//                case "find":
//                    System.out.println("enter id");
//                    int empId = scanner.nextInt();
//                    hashTab.findEmpById(empId);
//                    break;
//
//            }
//        }
        int arr[] = {4,6,8,5,9};
        heapSort(arr);
    }

//    堆排序
    public static void heapSort(int arr[]) {
        int temp = 0;
//        将数组调整成一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }

        for (int i = arr.length - 1; i > 0 ; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }

        System.out.println(Arrays.toString(arr));
    }

    //将数组（对应一个二叉树  顺序存储）调整成大顶堆
    /*
    * i 表示非叶子节点在数组中的索引
    * length 对多少元素进行调整 （length在逐渐减少）
    *
    */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];
        for (int j = i * 2 + 1 ; j < length; j = j * 2 + 1) {
            if (j+1 < length && arr[j] < arr[j + 1]) {//左子节点小于右子节点
                j++; //指向右子节点
            }
            if (arr[j] > temp) { //子节点大于父节点
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
//        for循环结束后 以i为父节点的数的最大值放在了最顶上(局部)
        arr[i] = temp;
    }
}

//hashtab  管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //?留一个坑  分别初始化每一个列表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        //根据员工ID得到该员工应该加入到哪条链表
        int empLinkedListNO = hashFun(emp.id);
//        添加到对应链表
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    public void list() {
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmpById(int id) {
        //使用散列函数 确定到哪条链表
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d调链表中找到雇员 id = %d\n",empLinkedListNO, id);
        } else {
            System.out.println("没找到");
        }
    }

    //散列函数  取模法
    public int hashFun(int id) {
        return id % size;
    }


}

//雇员类
class Emp {
   public int id;
   public String name;
   public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//链表
class EmpLinkedList {
//    头指针 指向第一个雇员 因此head是直接指向第一个Emp
    private Emp head;//默认null

//    add emp to linkedlist
//    id自增
    public void add(Emp emp) {
        //添加第一个
        if (head == null) {
            head = emp;
            return;
        }
        //不是第一个 使用辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) break;
            curEmp = curEmp.next;
        }

        curEmp.next = emp;

    }

    //遍历
    public void list(int no) {
        if (head == null) {
            System.out.println("第 "+no+" 链表为空");
            return;
        }
        System.out.printf("第 "+no+" 链表的信息为：");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id = %d name = %s\t",curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;//后移 遍历
        }
    }

    public Emp findEmpById(int id) {
        if (head == null) {
            return null;
        }
        Emp curEmp = head;
        boolean flag = false;
        while (true) {
            if (curEmp.id == id) {
                flag = true;
                break;
            }
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;//后移 遍历
        }

        if (flag) return curEmp;
        return null;

    }


}
