/*为了更好地组织类，Java 提供了包机制，用于区别类名的命名空间。可以理解为c#的名称空间

包的作用
1、把功能相似或相关的类或接口组织在同一个包中，方便类的查找和使用。

2、如同文件夹一样，包也采用了树形目录的存储方式。同一个包中的类名字是不同的，不同的包中的类的名字是可以相同的，当同时调用两个不同包中相同类名的类时，应该加上包名加以区别。因此，包可以避免名字冲突。

3、包也限定了访问权限，拥有包访问权限的类才能访问某个包中的类。

Java 使用包（package）这种机制是为了防止命名冲突，访问控制，提供搜索和定位类（class）、接口、枚举（enumerations）和注释（annotation）等。*/
package com.atguigu.queue;

import java.awt.print.Printable;
import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //测试
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);//创建 Scanner 对象的基本语法,从键盘接收数据
        //System类代表系统，系统级的很多属性和控制方法都放置在该类的内部，system中包含了in、out和
        //err三个成员变量，分别代表标准输入流（键盘输入）、标准输出流（显示器）和标准错误输出流（显示器）
        boolean loop = true;
        while(loop){
            System.out.println("s(show):显示队列");//println函数输出后换行
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("p(peek):查看头数据");
            key = scanner.next().charAt(0);
			/*next():

				1、一定要读取到有效字符后才可以结束输入。
				2、对输入有效字符之前遇到的空白，next() 方法会自动将其去掉。
				3、只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
				next() 不能得到带有空格的字符串。

			  nextLine()：
	 			1、以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符。
				2、可以获得空白。

			   charAt() 方法用于返回指定索引处的字符。索引范围为从 0 到 length() - 1。*/
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int item=queue.getQueue();
                        System.out.printf("取出的数据：%d\n", item);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        System.out.print(queue.peek());
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.print(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.print("程序退出");

    }

}
//使用数组模拟队列-编写一个ArrayQueue的类
class ArrayQueue{
    private int maxSize;//表示数组的最大容量
    //private : 在同一类内可见。使用对象：变量、方法。 注意：不能修饰类（外部类）
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//声明一个数组存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){//public： Java语言中访问限制最宽的修饰符，一般称之为“公共的”。被其修饰的类、属性以及方法不仅可以跨类访问，而且允许跨包（package）访问。
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        //new方法创建一个新的类的实例
        front=-1;//指向队列头部，分析出front是指向队列头的前一个位置。
        rear=-1;//指向队列尾的数据（即队列最后一个数据）
    }
    public boolean isfull() {
        return rear == maxSize-1;
    }
    public boolean isEmpty() {
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n){
        if(isfull()){
            System.out.print("队列满，不能加入数据");
            return;
        }
        rear++;
        arr[rear]=n;
    }
    //获取队列数据，出队列
    public int getQueue(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("队列空，不能输出数据");
            //return不用写，throw了一个异常后，会马上返回
        }
        front++;
        return arr[front];
    }
    public void showQueue(){
        if(isEmpty()){
            System.out.print("队列已空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d", i,arr[i]);
        }
    }
    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能输出数据");
        }
        return arr[front+1];
    }


}