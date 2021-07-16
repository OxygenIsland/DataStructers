package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        //（3+4）*5-6 =>3 4 + 5 * 6 -
        //4*5-8+60+8/2=> 4 5 * 8 - 60 + 8 2 / +
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1.先将“3 4 + 5 * 6 -”放到ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList 配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.printf("表达式%s=%d",suffixExpression,cal(list));

    }
    //将一个逆波兰表达式，依次将数据和表达式放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");//根据空格来分割，split返回一个字符串数组
        List<String> list = new ArrayList<String>();
        for(String ele :split){
            list.add(ele);
        }
        return list;
    }
    //完成对表达式的计算
    public static int cal(List<String> list){
        //只需要创建一个栈即可
        Stack<String> stack = new Stack<String>();
        //遍历栈
        for (String ele : list) {
            if(ele.matches("\\d+")){//匹配的是一位或者多位数
                stack.push(ele);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (ele){
                    case "+":
                        res = num1+num2;
                        break;
                    case "-" :
                        res = num1-num2;
                        break;
                    case "*" :
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default :
                        throw new RuntimeException("输入有误");
                }
                stack.push(Integer.toString(res));//stack.push("" + res)
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
