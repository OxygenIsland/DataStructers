package com.atguigu.linkedlist;

public class Josephu {
    public static void main(String[] args) {
        System.out.println("=====生成并显示环形链表=====");
        CircleSingleLinkedList item = new CircleSingleLinkedList();
        item.addBoy(5);
        item.ShowList();
        System.out.println("\n=====小孩出圈顺序=====");
        item.countBoy(1,2,5);
    }
}
//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);
    //添加小孩节点，构建成一个环形链表
    public void addBoy(int nums){
        //对nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for循环来创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy =new Boy(i);
            //如果是第一个小孩
            if (i==1){
                first=boy;
                first.setNext(first);//构成环状
                curBoy=first;

            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;

            }
        }
    }
    public void ShowList(){
        if (first== null) {
            System.out.println("没有元素");
            return;
        }
        //因为first不能动，因此我们任然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (curBoy.getNext() != first) {
            System.out.printf("\n小孩的编号:%d",curBoy.getNo());
            curBoy=curBoy.getNext();//使得curBoy后移
        }
        System.out.printf("\n小孩的编号:%d",curBoy.getNo());
    }
    //根据用户输入，计算出小孩出圈的顺序

    public void countBoy(int startNo,int countNum,int nums){
        //数据校验
        if (first==null||startNo>nums||startNo<1){
            System.out.printf("\n参数错误");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;//应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext()==first){
                break;
            }
            helper=helper.getNext();
        }
        //当小孩报数前，先让first和helper移动K-1次
        for (int i = 1; i < startNo-1; i++) {
            first=first.getNext();
            helper = helper.getNext();
        }
        //小孩报数时，先让helper和first同时移动countNum-1次，然后出圈，直到圈中只有一个节点
        while (true) {
            if (helper==first){
                break;
            }
            for (int j = 0; j < countNum-1;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //此时first指向的节点就是出圈小孩的节点
            System.out.printf("\n小孩编号：%d",first.getNo());
            first=first.getNext();
            helper.setNext(first);

        }
        System.out.printf("\n小孩编号：%d",first.getNo());
    }
}
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点
    public Boy(int no){
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}