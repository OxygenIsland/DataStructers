package com.atguigu.recursion;

public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.print(count);
    }
    //编写一个方法放置第n个皇后
    private  void check(int n){
        if(n==max){//n=8皇后已经放完
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i=0;i<max;i++){
            //先把当前这个皇后n，放到该行的第1列
            array[n]=i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){
                //不冲突，接着放第n+1个皇后，开始递归
                check(n+1);
            }
            //如果冲突，则进入循环，继续执行array[n]=i;
        }
    }

    //查看当我们放置第n个皇后时，检测该皇后是否和前面摆放的皇后冲突
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])) {
                //math.abs()返回一个数的绝对值
                //array[i] == array[n]表示判断第n个皇后是否和前面n-1个皇后在同一列
                //Math.abs(array[n])==Math.abs(array[n]-array[i])根据斜率的关系判断第n个皇后
                //是否和第i个皇后在同一斜线上
                return false;
            }
        }
        return true;
    }
    //将皇后摆放的位置输出
    private void print(){
        count++;
        for (int i = 0; i < array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.printf("\n");
    }
}
