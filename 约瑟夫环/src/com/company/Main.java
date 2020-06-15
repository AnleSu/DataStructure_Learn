package com.company;

public class Main {

//    创建单向环形链表
    public static void main(String[] args) {
	// write your code here
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
    }


}

class CircleSingleLinkedList {
    private Boy first = null;
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null; //辅助节点
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(boy);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }


        }
    }

    public void showBoy() {
        if (first == null || first.getNext() == null) {
            return;
        }

        Boy curBoy = first; //辅助节点
        while (true) {
            System.out.printf("boys no is %d \n",curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //    根据用户输入 计算出出圈顺序
    public void countBoy(int startNo, int countNo, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数有误");
            return;
        }
        Boy helper = first;
//        让helper指向环形链表最后一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
//  从startNo开始数 所以先移动startNo - 1次 first指向第startNo个节点
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 0; i < countNo - 1; i++) {
                first = first.getNext();//循环后 first 指向要出圈的
                helper = helper.getNext();
            }

            first = first.getNext();
            helper.setNext(first);
        }
    }
}

class Boy {
    private int no;

    private Boy next;
    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
