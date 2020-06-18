package com.company;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        逆波兰表达式
//        String suffixExpression = "30 4 + 5 * 6 -";
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println("rpnList is" + rpnList);

        String[] strList = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        List<String> list = new ArrayList<String>();
        for (String str: strList) {
            list.add(str);
        }
        int res = calculate(list);
        System.out.println("计算结果是"+res);

        String expression = "1+((2+3)*4)-5";
        List<String> inList = toInfixExpressionList(expression);
        System.out.println("中缀"+inList);

        List<String> suffixList = parseSuffixExpList(inList);
        System.out.println("后缀"+suffixList);

        int suffixRes = calculate(suffixList);
        System.out.println("计算结果是"+suffixRes);

    }

//    将逆波兰表达式 放入数组中
    public static List<String> getListString(String suffixExpression) {
         String[] split = suffixExpression.split(" ");
         List<String> list = new ArrayList<String>();
         for (String ele: split) {
             list.add(ele);
         }
         return list;
    }

    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<String>();
        int num2 = 1;
                int num1 = 0;
                int res = 0;
        for (String item:ls) {
////            正则判断是数
//            if (item.matches("\\d+")) {//匹配多位数
//                stack.push(item);
//            } else {
//                int num2 = Integer.parseInt(stack.pop());
//                int num1 = Integer.parseInt(stack.pop());
//                int res = 0;
//                if (item.equals("+")) {
//                    res = num1 + num2;
//                } else if (item.equals("-")) {
//                    res = num1 - num2;
//                } else if (item.equals("*")) {
//                    res = num1 * num2;
//                } else if (item.equals("/")) {
//                    res = num1 / num2;
//                } else {
//                    throw new RuntimeException("运算符有误");
//                }
//                stack.push(res + "");//整数转字符串
//            }

            if (item.equals("+")) {
               num2 = Integer.parseInt(stack.pop());
               num1 = Integer.parseInt(stack.pop());
                res = num1 + num2;
                stack.push(res + "");//整数转字符串
            } else if (item.equals("-")) {
                num2 = Integer.parseInt(stack.pop());
                num1 = Integer.parseInt(stack.pop());
                res = num1 - num2;
                stack.push(res + "");//整数转字符串
            } else if (item.equals("*")) {
                num2 = Integer.parseInt(stack.pop());
                num1 = Integer.parseInt(stack.pop());
                res = num1 * num2;
                stack.push(res + "");//整数转字符串
            } else if (item.equals("/")) {
                num2 = Integer.parseInt(stack.pop());
                num1 = Integer.parseInt(stack.pop());
                res = num1 / num2;
                stack.push(res + "");//整数转字符串
            } else {
                stack.push(item);
            }

        }
        return Integer.parseInt(stack.pop());
    }

//    中缀表达式 转后缀表达式
//    1.中缀表达式 转对应数组
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<String>();
        int i = 0;
        String str;//多位数拼接
        char c;
        do {
          if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {//非数字
              ls.add(""+c);
              i++;
          } else {
              //数字 考虑多位数问题
              str = "";
              while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                  str += c;
                  i++;
              }
              ls.add(str);
          }
        }while (i < s.length());
        return ls;
    }

//    中缀表达式 转 逆波兰表达式
    public static List<String> parseSuffixExpList(List<String> ls) {
        Stack<String> s1 = new Stack<String>();
//        Stack<String> s2 = new Stack<String>();  s2在整个过程中没有pop操作 最后还需要逆序输出 直接用ArrayList
        List<String> s2 = new ArrayList<String>();

        for (String item:ls) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());//s1的数据弹出 加入s2  直到遇到 （
                }
                s1.pop();//再把 （ 弹出 消除一对括号
            } else {
//                item 优先级 <= 栈顶优先级  s1的数据弹出 加入s2
                while (s1.size() != 0 && Operation.getValue(s1.peek()) > Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);

            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }
}

//判断操作符优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
        }
        return result;
    }
}
