package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        CircleArray queue = new CircleArray(4); //设置4  最大有效空间是3
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加队列");
            System.out.println("g(get):获取队列");
            System.out.println("h(head):队列头部");
            System.out.println("e(exit):退出程序");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's' :
                    queue.showQueue();
                    break;
                case 'a' :
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g' :
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h' :
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' :
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");

    }
}

//数组使用一次就不能用了 没有达到复用效果
//使用数组模拟环形队列优化
//1.front含义调整：指向第一个元素  front = 0
//2.rear含义调整：指向队列最后一个元素的后一个位置   空出一个空间作为约定  rear = 0
//3.队列满 (rear + 1) % maxSize == front
//4.队列空 rear == front
//5.队列中有效的个数  (rear + maxSize - front) % maxSize
class CircleArray {
    private int maxSize;//队列最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr; //存放数据 模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满 不能加入");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空， 不能取数据"); //没必要再写return
        }

        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空");
            return;
        }

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n",i % maxSize,arr[i % maxSize]);
        }
    }

//    队列有效个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列空");
            throw new RuntimeException("队列空， 无数据");
        }

        return arr[front];
    }

}
