package com.atguigu.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack items = new ArrayStack(4);
        String key = "";
        boolean loop = true;//判断是否退出菜单
        Scanner scanner = new Scanner(System.in);//监视键盘输入
        while (loop){
            System.out.printf("\ns:表示显示栈");
            System.out.printf("\ne:表示退出程序");
            System.out.printf("\npush:表示添加数据到栈");
            System.out.printf("\npop:表示从栈中弹出数据");
            System.out.printf("\n请输入");
            key=scanner.next();
            switch (key){
                case "s":
                    items.show();
                    break;
                case "e":
                    scanner.close();//关闭扫描
                    loop = false;
                    break;
                case "push":
                    System.out.printf("请输入一个数字");
                    items.push(scanner.nextInt());
                    break;
                case "pop":
                    try {
                        System.out.printf("\n弹出数据：%d",items.pop());
                    }catch (Exception e){
                        System.out.printf(e.getMessage());//打印异常信息
                    }
                    break;

                default:
                    break;
            }

        }
        System.out.printf("程序退出");
    }
}
//定义一个ArrayStack表示栈
class ArrayStack{
    private int maxSize;//栈的大小
    private int[ ] stack;//数组，数组模拟栈，数据就放在该数组中
    private int top = -1;//top表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];//初始化数组才能往里面放数据
    }
    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈-push
    public void push(int value){
        //先判断栈是否满
        if (isFull()){
            return;
        }
        top++;
        stack[top]=value;

    }
    //出栈
    public int pop(){
        if (isEmpty()) {
            throw new RuntimeException("栈空");//运行异常，不捕获也可以抛出，return不需要有，异常就表示终止程序
        }
        int val = stack[top];
        top--;
        return val;
    }
    //遍历栈
    public void show(){
        if (isEmpty()){
            System.out.printf("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0; i--) {
            System.out.printf("\nstack[%d]=%d",i,stack[i]);
        }
        return;
    }
}
