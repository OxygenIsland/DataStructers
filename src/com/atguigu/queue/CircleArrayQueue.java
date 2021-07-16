package com.atguigu.queue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //测试
        CircleArray queue = new CircleArray(4);//设置为4，队列的有效数据最大是3
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
class CircleArray{
    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    // front指向队列的第一个元素，即arr[front]代表第一个元素，front的初始值为0
    private int rear;//队列尾
    //rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定，rear的初始值为0
    private int[] arr;//声明一个数组存放数据，模拟队列

    //编写函数的构造器
    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front=0;
        rear=0;
    }
    public boolean isfull() {
        return (rear+1)%maxSize == front;
    }
    public boolean isEmpty() {
        return rear == front;
    }
    public void addQueue(int n){
        if(isfull()){
            System.out.print("队列满，不能加入数据");
            return;
        }
        arr[rear]=n;
        //将rear后移，这里必须考虑取模
        rear = (rear+1)%maxSize;

    }
    public int getQueue(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("队列空，不能输出数据");
            //return不用写，throw了一个异常后，会马上返回
        }
        int value = arr[front];//把front对应的值保留到一个临时变量
        front=(front+1)%maxSize;//将front后移，考虑取模
        return value;
    }
    public void showQueue(){
        if(isEmpty()){
            System.out.print("队列已空");
            return;
        }
        //从front开始遍历，遍历多少个元素？
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d", i%maxSize,arr[i%maxSize]);
        }
    }

    //求出当前队列的有效数据的个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能输出数据");
        }
        return arr[front];
    }

}