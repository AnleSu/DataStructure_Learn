package com.company;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        String exper = "30+2*6-2";
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);

        int index = 0;
        int num1 = 0,num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = ""; //处理多位数
        while (true) {
            ch = exper.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
//                    当前操作符优先级低于前一个操作符的优先级 则出栈先计算一次 结果入栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                }
            }else  {
//                通过字符串拼接处理多位数  判断后一位是操作符就入栈 不是就继续扫描字符串 下一个扫描到的数字 和上一个数字拼接
                keepNum += ch;

                if (index == exper.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //    此处注意要处理 多位数   不能发现一个数就入栈 可能是多位数
                    if (operStack.isOper(exper.substring(index+1,index+2).charAt(0))) {//后一位是操作符 就入栈
                        //                ASSIC码转换
//                        numStack.push(ch - 48);
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }


            }
            index++;
            if (index >= exper.length()) {
                break;
            }
        }

//        字符串扫描完毕  两栈遍历计算
        while (true) {
//            符号栈为空 则计算完毕
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }

        System.out.printf("表达式计算结果 = %d",numStack.pop());

	// write your code here
//        ArrayStack stack = new ArrayStack(4);
//        String key = "";
//        boolean loop = true;
//        Scanner scanner = new Scanner(System.in);
//        while (loop) {
//            System.out.println("show");
//            System.out.println("exit");
//            System.out.println("push");
//            System.out.println("pop");
//            key = scanner.next();
//            switch (key) {
//                case "show":
//                    stack.list();
//                    break;
//                case "push":
//                    System.out.println("place enter a num");
//                    int value = scanner.nextInt();
//                    stack.push(value);
//                    break;
//                case "pop":
//                    try {
//                        int res = stack.pop();
//                        System.out.printf("pop data is %d",res);
//                    } catch (Exception e) {
//
//                    }
//                    break;
//                case "exit":
//                    scanner.close();
//                    loop = false;
//                    break;
//            }
//        }
    }
}


class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;//栈顶

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空 无数据");

        }
        int value = stack[top];
        top--;
        return value;
    }

//    遍历 从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空 无数据");
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.println(stack[i]);
        }
    }

//    运算符优先级判断  优先级使用数字表示 数字越大优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
//    计算
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;

    }

    public int peek() {
        return stack[top];
    }

}

class MinStack {
    Stack<Integer> A, B;
    /** initialize your data structure here. */
    public MinStack() {
        A = new Stack<>();
        B = new Stack<>();
    }

    public void push(int x) {
        A.push(x);
//        注意此处 >=  非严格降序 有等于的情况
        if (B.empty() || B.peek() >= x) {
            B.push(x);
        }
    }
//由于 Stack 中存储的是 int 的包装类 Integer ，因此需要使用 equals() 代替 == ，以比较对象的值。
    public void pop() {
        if (A.peek().equals(B.peek())) {
            B.pop();
        }
        A.pop();

    }

    public int top() {
        return A.peek();
    }

    public int min() {
        return B.peek();
    }
}
