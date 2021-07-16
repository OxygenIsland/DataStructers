package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {//冒泡排序的时间复杂度O(n^2)
        int[] array = {3, 9, -1, 10, -2};
        bubblesort(array);
        int[] arr= new int[80000];
        for (int i = 0; i < 80000;i++){
            arr[i]=(int)(Math.random()*8000000);//产生一组随机数
        }
        Date date1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String dateStr = sdf.format(date1);
        System.out.println("排序后的时间是"+dateStr);

        bubblesort(arr);
        Date date2 = new Date();

        String dateStr2 = sdf.format(date2);
        System.out.println("排序前的时间是"+dateStr2);
    }
    public static void bubblesort(int[] array){

        //第一趟排序，就是将最大的数排在最后
        int temp =0;//临时变量
        boolean flag = false;
        for(int j=1;j<array.length; j++){
            for(int i = 0; i < array.length-j; i++){
                if(array[i]>array[i+1]){//如果前面的数比后面的数大
                    flag = true;
                    temp = array[i+1];
                    array[i+1]=array[i];
                    array[i] = temp;
                }

            }
            if (!flag) {
                break;
            }else {
                flag=false;
            }
        }

        //System.out.printf("排序后的数组%s", Arrays.toString(array));
    }
}

