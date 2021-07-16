package com.atguigu.linkedlist;

import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试，先创建节点
        HeroNode heroNode1 = new HeroNode(1,"宋江","及时雨");
        HeroNode heroNode2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode heroNode3 = new HeroNode(3,"吴用","智多星");
        HeroNode heroNode4 = new HeroNode(4,"林冲","豹子头");
        SingleLinkedList item = new SingleLinkedList();
        item.add2(heroNode4);
        item.add2(heroNode3);
        item.add2(heroNode2);
        item.add2(heroNode1);

        //测试修改节点的代码
        HeroNode heroNode = new HeroNode(2,"小卢","玉麒麟~~");
        item.update(heroNode);

        //测试删除节点的代码
        item.delete(2);
        item.show();

        //测试单链表的节点数
        System.out.println("=======链表的节点数=======\n"+ SingleLinkedList.getLength(item.getHead()));//静态方法可以通过类名调用

        //测试 找倒数第k个元素
        System.out.println("=======寻找倒数第K个元素========：");
        item.getDaoshu(item.getHead(),3);

//        //测试翻转链表
//        System.out.println("=======翻转链表========：");
//        SingleLinkedList.getRverse(item.getHead());
//        item.show();

        //测试翻转打印
        System.out.println("=======翻转打印链表========：");
        SingleLinkedList.reversePrint(item.getHead());
    }
}
//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");//被private修饰的成员只能在本类中被访问

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路：当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        while (true) {
            //遍历链表找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后节点，则将temp后移
            temp = temp.next;
        } //当退出while循环时，temp指向链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    public void add2(HeroNode heroNode) {
        //第二种方式在添加英雄时，根据排名将英雄插入到指定位置（如果有这个排名，则添加失败，并给出提示）
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//添加的英雄编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后
                break;//
            }
            if (temp.next.no > heroNode.no) {//位置找到了，就在temp的后边插入
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号依然存在
                flag = true;
                break;
            }
            temp = temp.next;//后移遍历链表

        }
        //判断flag的值
        if (flag) {//不能添加，说明编号存在
            System.out.printf("编号 %d 已经存在，不能添加到指定位置\n", heroNode.no);
            return;
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改节点信息，根据no编号来修改，即no编号不能改。
    public void update(HeroNode heroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.printf("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号，定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;//链表已经遍历完毕
            }
            if (temp.no == heroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            //没有找到
            System.out.printf("没有找到编号%d的节点，不能修改\n", heroNode.no);
        }
    }

    //删除链表节点
    public void delete(int no) {
        //判断是否为空
        if (head.next == null) {
            System.out.printf("链表为空");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next.no == no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }

    }

    //获取单链表节点的个数(如果是带头节点的链表则不计)
    public static int getLength(HeroNode head) {
        //“static方法就是没有this的方法。在static方法内部不能调用非静态方法，反过来是可以的。
        // 方便在没有创建任何对象的前提下，仅仅通过类本身来调用static方法。这实际上正是static方法的主要用途。”
        //被static关键字修饰的方法或者变量不需要依赖于对象来进行访问，只要类被加载了，就可以通过类名去进行访问。
        //在静态方法中不能访问类的非静态成员变量和非静态成员方法，因为非静态成员方法/变量都是必须依赖具体的对象才能够被调用。
        //但是在非静态成员方法中是可以访问静态成员方法/变量的
        if (head.next == null) {
            System.out.printf("链表为空");
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    //面试题：查找单链表中倒数第K个节点
    public void getDaoshu(HeroNode head, int k) {
        //链表为空则返回null
        if (head.next == null) {
            return ;
        }
        //第一次遍历得到链表长度
        int len = getLength(head);
        if (len - k < 0 || k<0) {
            System.out.printf("不存在该节点");
            return ;
        }
        HeroNode cur = head;
        boolean flag = false;
        for (int i=0;i<len-k+1;i++){
            cur = cur.next;
        }
        System.out.println(cur);
    }

    //面试题：翻转链表
    public static void getRverse(HeroNode head){
        //如果当前链表为空或者只有一个节点则无需翻转
        if (head.next == null||head.next.next==null) {
            System.out.println("链表无需翻转");
            return ;
        }
        HeroNode cur = head.next;//辅助变量（指针），帮助我们遍历原来的链表
        HeroNode next = null;//指向当前节点【cur】的下一个节点
        HeroNode reversehesd = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，将其取出放在新的链表reversehead的最前端
        while (cur!=null){
            next = cur.next;//先暂时保存当前节点的下一个节点，后面使用
            cur.next=reversehesd.next;//将cur的下一个节点指向新的列表的最前端
            reversehesd.next=cur;//将cur连接到新的链表上
            cur=next;//让cur后移
        }
        //将head.next指向reversehesd.next，实现单链表的翻转
        head.next=reversehesd.next;
    }

    //面试题从尾到头打印单链表
    //利用栈这个数据结构，将各个节点压入栈中，然后利用栈 先进后出 的特点实现逆序打印的效果
    public static void reversePrint(HeroNode head){
        //如果当前链表为空无打印
        if (head.next == null) {
            return ;
        }
        Stack<HeroNode> stack1 = new Stack<HeroNode>();
        //<>表示一个泛型，表示该栈只能存储 HeroNode类型的数据
        HeroNode cur = head.next;
        while (cur!=null){
            stack1.push(cur);
            cur=cur.next;
        }
        while (stack1.size()>0){
            System.out.println(stack1.pop());
        }
        return;
    }

    public void show() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表已空");
            return;
        }
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移，避免构成死循环
            temp = temp.next;
        }

    }
}


//定义HeroNode，每个HeroNode 对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点。该语句表示next这个变量类型为HeroNode这个类的实例
    //  可以通过类名+方法名调用的方法叫做静态方法，属于类级别方法。类方法可在不实例化对象的前提下【直接调用】。
    //  一般类的静态方法和静态变量都是通过static关键字修饰的。而且静态方法体里不能应用任何实例变量和实例方法。
    //  调用这个方法和new一个对象效果是一样的。不过只是把实例的创建封装在方法里,通过类名加方法来调用而已，
    //  让实例化代码简洁点外，无任何差别。
    
    //构造器
    public HeroNode(int hNo,String hName,String hNickname){
        this.no = hNo;
        this.nickname = hNickname;
        this.name = hName;
    }
    //为了显示方便，重新写toString方法

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
